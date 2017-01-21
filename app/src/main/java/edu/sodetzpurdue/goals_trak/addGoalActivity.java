package edu.sodetzpurdue.goals_trak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] dropdownList = {"Hours", "Days", "Weeks", "Months", "Dollars", "Repititions", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dropdownList);
        dropdown.setAdapter(adapter);
    }
}
