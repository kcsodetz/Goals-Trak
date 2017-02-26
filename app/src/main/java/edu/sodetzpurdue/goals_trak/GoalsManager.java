package edu.sodetzpurdue.goals_trak;

import java.io.Serializable;

/**
 * Created by Ken Sodetz on 1/21/2017.
 */

@SuppressWarnings("WeakerAccess")
public class GoalsManager implements Serializable{

    //initialized variables
    public static final long serialVersionUID = 1L;
    private String goalName, qualifier, frequency, ampm;
    private int durationNum, hour, min;
    private double percentage, runningTotal;
    private boolean isComplete;

    //GoalsManager constructor
    public GoalsManager(String goalName, int durationNum, String qualifier, String frequency, int hour, int min, String ampm){
        this.goalName = goalName;
        this.durationNum = durationNum;
        this.qualifier = qualifier;
        this.frequency = frequency;
        this.hour = hour;
        this.min = min;
        this.ampm = ampm;
        runningTotal = 0;
        isComplete = false;
    }

    //getter methods for variables
    public String getGoal(){
        return goalName;
    }
    public String getQualifier(){
        return qualifier;
    }
    public String getFrequency(){
        return frequency;
    }
    public String getAmpm(){
        return ampm;
    }
    public int getDurationNum(){
        return durationNum;
    }
    public int getHour(){
        return hour;
    }
    public int getMin(){
        return min;
    }
    public double getPercentage(){
        return percentage;
    }
    public double getRunningTotal(){
        return runningTotal;
    }

    //check if notifications were selected
    private boolean sendNotifications(){ // TODO: 1/22/2017 finish method
        if (!(hour == -1 || min == -1)) return true;
        else return false;
    }

    //calculate the percentage completed
    void calculatePercentages(double add){
        runningTotal+=add;
        percentage = (runningTotal/durationNum)*100;
        if (percentage >= 100){
            isComplete = true;
        }
    }

    //check if goal is at 100%
    boolean checkIfComplete(){
        return isComplete;
    }
}
