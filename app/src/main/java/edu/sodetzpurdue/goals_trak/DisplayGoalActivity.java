package edu.sodetzpurdue.goals_trak;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayGoalActivity extends AppCompatActivity {
    TextView runningTotalText, durationNumText, frequencyText, percentageText, congratsText;
    Button addBtn, backBtn, delBtn;
    EditText addEdit;

    Double addedNum;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_goal);
        //needs methods below
        Bundle bundle = getIntent().getExtras();
        //final GoalsManager goalsmanager = (GoalsManager) bundle.getSerializable("key");
        Intent intent = getIntent();
        final GoalsManager goalsmanager = ((GoalsTrak)getApplication()).getGoalsManager(intent.getExtras().getString("goalName"));
        System.out.println(intent.getExtras().getString("goalName"));
        setTitle(goalsmanager.getGoal());
        //setTitle("Temp");
        goalsmanager.calculatePercentages(0);
        runningTotalText = (TextView)findViewById(R.id.runningTotalText);
        durationNumText = (TextView)findViewById(R.id.durationNumText);
        frequencyText = (TextView)findViewById(R.id.frequencyText);
        percentageText = (TextView)findViewById(R.id.percentageText);
        String temp = Double.toString(goalsmanager.getRunningTotal());
        runningTotalText.setText(temp);
        temp = Integer.toString(goalsmanager.getDurationNum());
        durationNumText.setText(temp);
        frequencyText.setText(goalsmanager.getQualifier());
        temp = Double.toString(goalsmanager.getPercentage());
        percentageText.setText(temp);
        congratsText = (TextView)findViewById(R.id.congratsText);
        addBtn = (Button)findViewById(R.id.addToRunningButton);
        backBtn = (Button)findViewById(R.id.backToList);
        delBtn = (Button)findViewById(R.id.deleteBtn);
        delBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ((GoalsTrak)getApplication()).remObj(goalsmanager);
                        changeView(v);
                    }
                }
        );
        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addEdit = (EditText)findViewById(R.id.addToRunningEdit);
                        String temp = addEdit.getText().toString();
                        addEdit.setText("");
                        if (temp.equals(""))
                            toastInvalid(1);
                        try {
                            addedNum = Double.parseDouble(temp);
                            goalsmanager.calculatePercentages(addedNum);
                        } catch (Exception e){
                            System.out.println("ERROR");
                        }
                        updateScreenNumbers(goalsmanager, goalsmanager.checkIfComplete());
                        //((GoalsTrak)getApplication()).addObject(goalsmanager);
                    }
                }
        );
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_action:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return true;
        }
    }

    public void changeView(View view){
        Intent intent = new Intent(this, GoalsListActivity.class);
        //((GoalsTrak)getApplication()).addObject(goalsManager);
        startActivity(intent);
    }

    public void toastInvalid(int code){
        if (code == 1)
            Toast.makeText(this, "Must enter a number", Toast.LENGTH_SHORT).show();
    }

    public void updateScreenNumbers(GoalsManager goalsmanager, Boolean isComplete){
        runningTotalText = (TextView)findViewById(R.id.runningTotalText);
        durationNumText = (TextView)findViewById(R.id.durationNumText);
        frequencyText = (TextView)findViewById(R.id.frequencyText);
        percentageText = (TextView)findViewById(R.id.percentageText);
        String temp = Double.toString(goalsmanager.getRunningTotal());
        runningTotalText.setText(temp);
        temp = Integer.toString(goalsmanager.getDurationNum());
        durationNumText.setText(temp);
        frequencyText.setText(goalsmanager.getQualifier());
        temp = Double.toString(goalsmanager.getPercentage());
        percentageText.setText(temp);
        if (isComplete) {
            percentageText.setText(R.string.one_hundred);
            congratsText.setText(R.string.congrats_msg);
            addEdit.setHint(" ");
            addBtn.setEnabled(false);
            addEdit.setEnabled(false);

        }
    }




}
