package edu.sodetzpurdue.goals_trak;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


//outdated class, no longer in use
public class CreateNewGoal extends AppCompatActivity {
    QuoteManager quoteManager = new QuoteManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView quoteView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        quoteView=(TextView)findViewById(R.id.quoteView2);
        quoteView.setText(quoteManager.getQuote());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.new_goal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeView(view);
            }
        });
    }

    public void changeView(View view){
        Intent intent = new Intent(this, AddGoalActivity.class);
        startActivity(intent);
    }

}
