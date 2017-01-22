package edu.sodetzpurdue.goals_trak;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddGoalActivity extends AppCompatActivity implements View.OnClickListener{
    Button timeButton;
    CheckBox checkBox;
    Spinner dropdown, notificationsDrop;
    static final int DIALOGE_ID = 0;
    int hour_x;
    int minute_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        dropdown = (Spinner)findViewById(R.id.spinner);
        String[] dropdownList = {"Hours", "Days", "Weeks", "Months", "Dollars", "Repititions", "Other"};
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
        showTime();
    }

    public void showTime(){
        timeButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        showDialog(DIALOGE_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOGE_ID){
            return new TimePickerDialog(AddGoalActivity.this, kTimePickerListener, hour_x, minute_x, false);
        }
        return null;

    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute = minute_x;

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
