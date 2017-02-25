package edu.sodetzpurdue.goals_trak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {


    //@Override
    QuoteManager quoteManager = new QuoteManager();
    TextView quoteView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteView=(TextView)findViewById(R.id.quoteView);
        quoteView.setText(quoteManager.getQuote());
    }

    public void changeView(View view){
        Intent intent = new Intent(this, AddGoalActivity.class);
        startActivity(intent);
    }

    public void changeViewActive(View view){
        Intent intent = new Intent(this, GoalsListActivity.class);
        startActivity(intent);
    }



}
