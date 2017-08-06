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
 * @author Ken Sodetz
 * @since 1/22/2017
 */

public class GoalsTrak extends Application{

    //Name of file to save the  goalsMap hash map
    private String filename = "HashMap";
    //creates hash map for storing goalsManager objects
    private HashMap<String, GoalsManager> goalsMap = new HashMap<>();
    //get the hash map
    public HashMap<String, GoalsManager> getHashMap(){
        return goalsMap;
    }



    /**
     *
     * @param goalsManager
     */
    public void addObj(GoalsManager goalsManager){
        goalsMap.put(goalsManager.getGoal(), goalsManager);
    }

    //removes a goalsManager object from the hash mao
    public void remObj(GoalsManager goalsManager){
        goalsMap.remove(goalsManager.getGoal());
    }

    //returns the specific goalsManager object by name


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
}
