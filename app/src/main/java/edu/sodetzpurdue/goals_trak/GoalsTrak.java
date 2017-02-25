package edu.sodetzpurdue.goals_trak;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ken Sodetz on 1/22/2017.
 */

public class GoalsTrak extends Application{


    private HashMap<String, GoalsManager> goalsMap = new HashMap<>();

    public void setHash(HashMap<String, GoalsManager> map){
        this.goalsMap = map;
    }

    public HashMap<String, GoalsManager> getHashMap(){
        return goalsMap;
    }

    public void addObj(GoalsManager goalsManager){
        goalsMap.put(goalsManager.getGoal(), goalsManager);
    }

    public void remObj(GoalsManager goalsManager){
        goalsMap.remove(goalsManager.getGoal());
    }

    public GoalsManager getGoalsManager(String goalName){
        return goalsMap.get(goalName);
    }

    //private ArrayList<GoalsManager> arrayList = new ArrayList<>();

    /*public ArrayList<GoalsManager> getArrayList(){
        return arrayList;
    }*/

    /*public void addObject(GoalsManager goalsManager){
        arrayList.add(goalsManager);
    }*/

    /*public void removeObject(GoalsManager goalsManager){
        arrayList.remove(goalsManager);
    }*/

    /*public void printList(){
        System.out.println(arrayList);
    }*/
}
