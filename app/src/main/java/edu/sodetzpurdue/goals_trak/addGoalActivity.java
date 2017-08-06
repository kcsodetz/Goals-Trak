package edu.sodetzpurdue.goals_trak;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * The AddGoalActivity class manages the user input for the goal being created
 * @author Ken Sodetz
 * @since 1/21/2017
 */

public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * Initialized local variables
     */
    private final int NO_GOAL = 1;
    private final int NO_FREQUENCY = 2;
    private final int NO_TIME = 3;
    public static final int DIALOG_ID = 0;
    private final String DEFAULT = "DEFAULT";
    int hour_x = 12;
    int minute_x = 0;
    Button timeButton, doneBtn;
    CheckBox checkBox;
    Spinner dropdown, notificationsDrop;
    String timeString = "Set Time";
    String temp = DEFAULT;
    private EditText name, userNumber;
    String goalName = DEFAULT;
    int amount = -1;
    String frequencySpinner = DEFAULT;
    String dayWeekMonthSpinner = DEFAULT;
    String ampm = DEFAULT;

    @Override
    /**
     * The onCreate method
     * @param savedInstanceState is the saved instance of type bundle
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // needs line below or else it throws a NPE
        setContentView(R.layout.activity_add_goal);
        dropdown = (Spinner)findViewById(R.id.spinner);
        final String[] dropdownList = {"Hours", "Days", "Weeks", "Months", "Dollars", "Repetitions", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownList);
        dropdown.setAdapter(adapter);
        notificationsDrop = (Spinner)findViewById(R.id.timeSpinner);
        String[] timeList = {"Every Day", "Every Week", "Every Month"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeList);
        notificationsDrop.setAdapter(adapter1);
        timeButton = (Button)findViewById(R.id.selectTimeButton);
        timeButton.setEnabled(false);
        notificationsDrop.setEnabled(false);
        checkBox = (CheckBox) findViewById(R.id.notificationsCheckbox);
        checkBox.setOnClickListener(this);
        doneBtn = (Button)findViewById(R.id.doneButton);
        doneBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        name = (EditText)findViewById(R.id.enterGoalTextEdit);
                        userNumber = (EditText)findViewById(R.id.numEdit);
                        goalName = name.getText().toString();
                        temp = userNumber.getText().toString();
                        frequencySpinner = dropdown.getSelectedItem().toString();
                        dayWeekMonthSpinner = notificationsDrop.getSelectedItem().toString();
                        if (goalName.equals("")){
                            emptyEditTextToast(NO_GOAL);
                        }
                        else if (temp.equals("")){
                           emptyEditTextToast(NO_FREQUENCY);
                        }
                        if(checkBox.isChecked() && (timeString.equals("Set Time"))){
                            emptyEditTextToast(NO_TIME);
                        }
                        if(!checkBox.isChecked() && (hour_x != -1 || minute_x != -1))
                            timeButton.setText(R.string.setTime);
                        try {
                            amount = Integer.parseInt(temp);
                        } catch (Exception e){
                            System.out.println("Empty");
                        }
                        System.out.println(goalName);
                        System.out.println(amount);
                        System.out.println(frequencySpinner);
                        System.out.println(dayWeekMonthSpinner);
                        if (!goalName.equals("") && !temp.equals("") && (!timeString.equals("Set Time") || !checkBox.isChecked())){
                            // TODO: 1/21/2017 implement appropriate methods
                            GoalsManager goalsManager = createGoalsManagerObject();
                            System.out.println(goalsManager.getGoal());
                            changeActivity(goalsManager.getGoal());
                        }
                    }
                }
        );

        showTime();
    }

    /**
     * The onCreateOptionsMenu creates the menu
     * @param menu is the menu created, of type Menu
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * The onOptionsItemSelected method gets the selected item from the spinner
     * @param item if the menu item selected, of type MenuItem
     * @return true
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_action:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return true;
        }
    }

    /**
     * The createGoalsManagerObject method creates a local instance of a GoalsManager object
     * @return goalsManager
     */
    public GoalsManager createGoalsManagerObject(){
        GoalsManager goalsManager = new GoalsManager(goalName, amount, frequencySpinner, dayWeekMonthSpinner, hour_x, minute_x, ampm);
        ((GoalsTrak)getApplication()).addObj(goalsManager);
        return goalsManager;
    }

    /**
     * The emptyEditTextToast method handles all calls to toast an empty edit text
     * @param num is the numeric code to send a particular toast, of type final int
     */
    public void emptyEditTextToast(int num){
        switch (num){
            case NO_GOAL:
                Toast.makeText(this, "You did not enter a Goal", Toast.LENGTH_SHORT).show();
                break;
            case NO_FREQUENCY:
                Toast.makeText(this, "You did not enter a frequency/amount", Toast.LENGTH_SHORT).show();
                break;
            case NO_TIME:
                Toast.makeText(this, "You did not enter a time for notifications", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     *
     * @param goalName is the name of the current goal, of type string
     */
    public void changeActivity(String goalName){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        intent.putExtra("goalName", goalName);
        startActivity(intent);

    }

    /**
     * The showTime method sets the shows the current default time for the time picker
     */
    public void showTime(){
        timeButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    /**
     * The onCreateDialog helps create a time picker for the AddGoalActivity
     * @param id is the id number of the time, of type int
     * @return TimePickerDialog
     */
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID){
            return new TimePickerDialog(AddGoalActivity.this, kTimePickerListener, hour_x, minute_x, false);
        }
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            ampm = "AM";
            if (hourOfDay > 12) {
                hour_x = hourOfDay - 12;
                ampm = "PM";
            }
            if(hourOfDay == 12)
                ampm = "PM";
            if(hourOfDay == 0)
                hour_x += 12;
            if(minute < 10)
                timeString = hour_x+":0"+minute_x+ampm;
            else
                timeString = hour_x+":"+minute_x+ampm;

            timeButton.setText(timeString);
        }
    };

    @Override
    /**
     * The onClick method handles the event of a click in the activity
     * @param v is the current view
     */
    public void onClick(View v) {
        CheckBox t = (CheckBox) v;
        if (t.isChecked()){
            timeButton.setEnabled(true);
            notificationsDrop.setEnabled(true);
        }
        else {
            timeButton.setEnabled(false);
            notificationsDrop.setEnabled(false);
        }
    }
}