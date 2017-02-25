package edu.sodetzpurdue.goals_trak;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ken Sodetz on 1/22/2017.
 */

public class GoalsTrak extends Application{

    private String filename = "HashMap";
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

    public void saveHashMap(){
        System.out.println("Attempting to save");
        try{
            File file = new File(getFilesDir(), filename);
            if(file.exists()) {
                file.delete();
                System.out.println("Deleted");
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(goalsMap);
            oos.flush();
            oos.close();
            System.out.println("Saved");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void readHashMap() {
        System.out.println("Attempting to read");
        File file = new File(getFilesDir(), filename);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            goalsMap = (HashMap<String, GoalsManager>) ois.readObject();
            ois.close();
            System.out.println("Read");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
