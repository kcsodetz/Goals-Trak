package edu.sodetzpurdue.goals_trak;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ken Sodetz on 1/21/2017.
 */

public class GoalsManager implements Parcelable{
    private String goal, qualifier, frequency, ampm;
    private int durationNum, hour, min;
    private double percentage, runningTotal;
    private boolean isComplete;
    public GoalsManager(String goal, int durationNum, String qualifier, String frequency, int hour, int min, String ampm){
        this.goal = goal;
        this.durationNum = durationNum;
        this.qualifier = qualifier;
        this.frequency = frequency;
        this.hour = hour;
        this.min = min;
        this.ampm = ampm;
        runningTotal = 0;
        isComplete = false;
    }

    //public GoalsManager(){}

    public String getGoal(){
        return goal;
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


    private boolean sendNotifications(){
        if (hour == -1 || min == -1)
            return false;
        return true;
    }

    public void calculatePercentages(double add){
        runningTotal+=add;
        percentage = (runningTotal/durationNum)*100;
        if (percentage >= 100){
            isComplete = true;
        }
    }

    public boolean checkIfComplete(){
        return isComplete;
    }

    //can ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { //// TODO: 1/22/2017 finish methods
        dest.writeInt(hour);
        dest.writeInt(min);
        dest.writeInt(durationNum);
        dest.writeString(goal);
        dest.writeString(qualifier);
        dest.writeString(frequency);
        dest.writeString(ampm);
    }

    public static final Parcelable.Creator<GoalsManager> CREATOR
            = new Parcelable.Creator<GoalsManager>() {
        public GoalsManager createFromParcel(Parcel in) {
            return new GoalsManager(in);
        }

        public GoalsManager[] newArray(int size) {
            return new GoalsManager[size];
        }

    };

    private GoalsManager(Parcel in){
        hour = in.readInt();
        min = in.readInt();
        durationNum = in.readInt();
        goal = in.readString();
        qualifier = in.readString();
        frequency = in.readString();
        ampm = in.readString();
    }
}
