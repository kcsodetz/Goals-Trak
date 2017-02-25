package edu.sodetzpurdue.goals_trak;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

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
        setTitle("Goals in Progress");
        listView = (ListView)findViewById(R.id.listView);

        //Initialze array list for the listView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg){
                //Intent intent = new Intent(this, DisplayGoalActivity.class);
                ((GoalsTrak)getApplication()).saveHashMap(); //save state
                String key = (String) adapter.getAdapter().getItem(position);
                changeView(view, key);
            }
        });


    }

    /*private void saveHashMap(){
        System.out.println("Attempting to save");
        try{
            map = ((GoalsTrak)getApplication()).getHashMap();
            File file = new File(getFilesDir(), filename);
            if(file.exists()) {
                file.delete();
                System.out.println("Deleted");
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(map);
            oos.flush();
            oos.close();
            System.out.println("Saved");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readHashMap(){
        System.out.println("Attempting to read");
        File file = new File(getFilesDir(), filename);
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ((GoalsTrak)getApplication()).setHash((HashMap<String, GoalsManager>)ois.readObject());
            ois.close();
            System.out.println("Read");
            buildList(((GoalsTrak)getApplication()).getHashMap());
            System.out.println("Built");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    protected void onSaveInstanceState(Bundle savedInstanceState){
        ((GoalsTrak)getApplication()).saveHashMap();
        System.out.println("saved");
    }

    public ArrayList<String> buildList(HashMap<String, GoalsManager> map){ //placing the appropriate keys in the list view
        ArrayList<String> mapArrayList = new ArrayList<>();
        for (Map.Entry<String,GoalsManager> entry : map.entrySet()) {
            String key = entry.getKey();
            mapArrayList.add(key);
        }
        return mapArrayList;
    }


    /*public void populateList(HashMap<String, GoalsManager> map){
        DataAdapter adapter = new DataAdapter(map);

        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(map.containsKey("hi"));
        for (Map.Entry<String,GoalsManager> entry : map.entrySet()) {
            String key = entry.getKey();
            //String value = entry.getValue();
            // do stuff
            System.out.println(key);
        }
        listView.setAdapter(adapter);
    }*/
    
    public boolean onCreateOptionsMenu(Menu menu){ //Create the action button
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { //for the action button
        switch (item.getItemId()) {
            case R.id.home_action:
                startActivity(new Intent(this, MainActivity.class));
                ((GoalsTrak)getApplication()).saveHashMap(); //save state
                return true;
            default:
                return true;
        }
    }

    public void changeView(View view, String goalName){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        intent.putExtra("goalName", goalName);
        ((GoalsTrak)getApplication()).saveHashMap();
        startActivity(intent);
    }

    public void pressedFAB(View view){
        Intent intent = new Intent(this, AddGoalActivity.class);
        ((GoalsTrak)getApplication()).saveHashMap();
        startActivity(intent);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

   /*public void changeActivity(View view, ){
        Intent intent = new Intent(this, DisplayGoalActivity.class);
        GoalsManager goalsmanager =
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
    }*/

}
