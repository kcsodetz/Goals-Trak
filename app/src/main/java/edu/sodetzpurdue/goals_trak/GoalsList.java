package edu.sodetzpurdue.goals_trak;


import android.app.Application;

import java.util.HashMap;

/**
 * Created by Ken Sodetz on 1/22/2017.
 */

public class GoalsList extends Application{
    public GoalsList(){};
    private HashMap<String, GoalsManager> hashMap;

    public HashMap<String, GoalsManager> getHashMap(){
        return hashMap;
    }



}
