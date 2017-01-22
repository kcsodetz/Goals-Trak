package edu.sodetzpurdue.goals_trak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_goal);
        //// TODO: 1/22/2017 finish methods
        Intent intent = getIntent();
        GoalsManager goalsmanager = intent.getParcelableExtra("Passer");

    }


}
