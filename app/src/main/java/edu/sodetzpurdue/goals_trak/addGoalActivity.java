package edu.sodetzpurdue.goals_trak;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener{
    //variable declarations
    Button timeButton, doneBtn;
    CheckBox checkBox;
    Spinner dropdown, notificationsDrop;
    String timeString = "Select Time";
    String temp = "DEFAULT";
    private EditText name, userNumber;
    String goalName = "DEFAULT";
    int amount = -1;
    String frequencySpinner = "DEFAULT";
    String dayWeekMonthSpinner = "DEFAULT";
    String ampm = "DEFAULT";
    public static final int DIALOG_ID = 0;
    int hour_x = -1;
    int minute_x = -1;

    @Override
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
                            emptyEditTextToast(1);
                        }
                        if (temp.equals("")){
                           emptyEditTextToast(2);
                        }
                        if(checkBox.isChecked() && (hour_x == -1 || minute_x == -1)){
                            emptyEditTextToast(3);
                        }
                        if(!checkBox.isChecked() && (hour_x != -1 || minute_x != -1))
                            timeButton.setText("SELECT TIME");
                        try {
                            amount = Integer.parseInt(temp);
                        } catch (Exception e){
                            System.out.println("Empty");
                        }
                        System.out.println(goalName);
                        System.out.println(amount);
                        System.out.println(frequencySpinner);
                        System.out.println(dayWeekMonthSpinner);
                        if (!goalName.equals("") && !temp.equals("")){
                            //// TODO: 1/21/2017 implement appropriate methods
                            createGoalsManagerObject();
                            changeActivity(v);
                        }
                    }
                }
        );

        showTime();
    }

    public void createGoalsManagerObject(){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        GoalsManager goalsmanager = new GoalsManager(goalName, amount, frequencySpinner, dayWeekMonthSpinner, hour_x, minute_x, ampm);
        //intent.putExtra("goalsmanager", goalsmanager);
    }



    public void emptyEditTextToast(int num){
        if (num == 1)
            Toast.makeText(this, "You did not enter a Goal", Toast.LENGTH_SHORT).show();
        if (num == 2)
            Toast.makeText(this, "You did not enter a frequency/amount", Toast.LENGTH_SHORT).show();
        if (num == 3)
            Toast.makeText(this, "You did not enter a time for notifications", Toast.LENGTH_SHORT).show();
    }

    public void changeActivity(View view){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        GoalsManager goalsmanager = new GoalsManager(goalName, amount, frequencySpinner, dayWeekMonthSpinner, hour_x, minute_x, ampm);
        intent.putExtra("key", goalsmanager);
        startActivityForResult(intent, 12345);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        GoalsManager goalsmanager = new GoalsManager(goalName, amount, frequencySpinner, dayWeekMonthSpinner, hour_x, minute_x, ampm);
        if (requestCode == 12345){
            if (resultCode == RESULT_OK){
                GoalsManager goalsManager = (GoalsManager)intent.getSerializableExtra("key");
            }
        }
    }


    /*public void onDonePress(View view){
        goalName = name.getText().toString();
        temp = userNumber.getText().toString();
        if (goalName.equals("")){
            Toast.makeText(this, "You did not enter a Goal", Toast.LENGTH_SHORT).show();
        }
        if (temp.equals("")){
            Toast.makeText(this, "You did not enter a frequency/amount", Toast.LENGTH_SHORT).show();
        }
        try {
            amount = Integer.parseInt(temp);
        } catch (Exception e){
            System.out.println("Empty");
        }
        System.out.println(goalName);
    }
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
            if (hourOfDay == 0)
                hour_x = 12;
            timeString = hour_x+":"+minute_x+ampm;
            if(minute == 0)
                timeString = hour_x+":00"+ampm;
            timeButton.setText(timeString);
        }
    };
    //@Override
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
