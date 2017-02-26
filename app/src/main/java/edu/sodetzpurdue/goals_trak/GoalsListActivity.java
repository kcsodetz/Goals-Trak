package edu.sodetzpurdue.goals_trak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoalsListActivity extends AppCompatActivity {

    //Initialize variables
    ListView listView;
    ArrayList<String> arrayList;
    HashMap<String, GoalsManager> map;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //read and update data from local storage
        ((GoalsTrak)getApplication()).readHashMap();

        //Initialize a local instance of the Hashmap
        map = ((GoalsTrak)getApplication()).getHashMap();
        arrayList = buildList(map);
        setContentView(R.layout.activity_goals_list);
        setTitle(getString(R.string.goalsInProgress));
        listView = (ListView)findViewById(R.id.listView);

        //Initialize array list for the listView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        //make click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            //list view item click handler
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg){
                ((GoalsTrak)getApplication()).saveHashMap(); //save state
                String key = (String) adapter.getAdapter().getItem(position);
                changeView(view, key);
            }
        });
    }

    //saves current state of the HashMap
    protected void onSaveInstanceState(Bundle savedInstanceState){
        ((GoalsTrak)getApplication()).saveHashMap();
        System.out.println("saved");
    }

    //builds the ArrayList and places the appropriate keys in the list view
    public ArrayList<String> buildList(HashMap<String, GoalsManager> map){
        ArrayList<String> mapArrayList = new ArrayList<>();
        for (Map.Entry<String,GoalsManager> entry : map.entrySet()) {
            String key = entry.getKey();
            mapArrayList.add(key);
        }
        return mapArrayList;
    }

    //Create the home button
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Handles the home button click
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_action:
                startActivity(new Intent(this, MainActivity.class));
                ((GoalsTrak)getApplication()).saveHashMap(); //save state
                return true;
            default:
                return true;
        }
    }

    //change view method to edit the selected goal
    public void changeView(View view, String goalName){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        intent.putExtra("goalName", goalName);
        ((GoalsTrak)getApplication()).saveHashMap();
        startActivity(intent);
    }

    //handles the floating action button clicked
    public void pressedFAB(View view){
        Intent intent = new Intent(this, AddGoalActivity.class);
        ((GoalsTrak)getApplication()).saveHashMap();
        startActivity(intent);
    }
}
