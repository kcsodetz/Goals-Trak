package edu.sodetzpurdue.goals_trak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GoalsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);
        setTitle("Goals in Progress");

    }
}
