package edu.sodetzpurdue.goals_trak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GoalsListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<GoalsManager> arrayList = ((GoalsTrak)getApplication()).getArrayList();
        setContentView(R.layout.activity_goals_list);
        setTitle("Goals in Progress");
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<GoalsManager> arrayAdapter = new ArrayAdapter<GoalsManager>(
                this, android.R.layout.simple_list_item_1, arrayList
        );
        listView.setAdapter(arrayAdapter);
    }
}
