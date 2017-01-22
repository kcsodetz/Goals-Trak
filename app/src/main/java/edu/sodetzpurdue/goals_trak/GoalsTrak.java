package edu.sodetzpurdue.goals_trak;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ken Sodetz on 1/22/2017.
 */

public class GoalsTrak extends Application{
    private ArrayList<GoalsManager> arrayList = new ArrayList<>();

    public ArrayList<GoalsManager> getArrayList(){
        return arrayList;
    }

    public void addObject(GoalsManager goalsManager){
        arrayList.add(goalsManager);
    }

    public void removeObject(GoalsManager goalsManager){
        arrayList.remove(goalsManager);
    }

    public void printList(){
        System.out.println(arrayList);
    }
}
