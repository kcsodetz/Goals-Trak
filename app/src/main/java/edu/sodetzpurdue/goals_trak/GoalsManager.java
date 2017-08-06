package edu.sodetzpurdue.goals_trak;
import java.io.Serializable;

/**
 * The GoalsManager Class manages all goals for the application
 * @author Ken Sodetz
 * @since 1/21/2017
 */

@SuppressWarnings("WeakerAccess")
public class GoalsManager implements Serializable{

    /**
     * Initialized local variables
     */
    public static final long serialVersionUID = 1L;
    private String goalName, qualifier, frequency, ampm;
    private int durationNum, hour, min;
    private double percentage, runningTotal;
    private boolean isComplete;

    /**
     * GoalsManager constructor
     * @param goalName is the name of the goal, of type string
     * @param durationNum is the overall goal, of type int
     * @param qualifier is the type of goal, of type string
     * @param frequency is how many times the user will be notified, of type string
     * @param hour is what hour to send the notification, of type int
     * @param min is what minute to send the notification, of type int
     * @param ampm is what half of the day to send the notification, opf type string
     */
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

    /**
     * Getter method for goalName
     * @return goalName
     */
    public String getGoal(){
        return goalName;
    }

    /**
     * Getter method for qualifier
     * @return qualifier
     */
    public String getQualifier(){
        return qualifier;
    }

    /**
     * Getter method for frequency
     * @return frequency
     */
    public String getFrequency(){
        return frequency;
    }

    /**
     * Getter method for ampm
     * @return ampm
     */
    public String getAmpm(){
        return ampm;
    }

    /**
     * Getter method for durationNum
     * @return durationNum
     */
    public int getDurationNum(){
        return durationNum;
    }

    /**
     * Getter method for hour
     * @return hour
     */
    public int getHour(){return hour;}

    /**
     * Getter method for min
     * @return min
     */
    public int getMin(){
        return min;
    }

    /**
     * Getter method for percentage
     * @return percentage
     */
    public double getPercentage(){
        return percentage;
    }

    /**
     * Getter method for runningTotal
     * @return runningTotal
     */
    public double getRunningTotal(){
        return runningTotal;
    }

    /**
     * The notificationsActive method checks if "Send notifications" was selected
     * @return true or false
     */
    private boolean notificationsActive(){ // TODO: 1/22/2017 finish method
        if (!(hour == -1 || min == -1)) return true;
        else return false;
    }

    /**
     * The calculatePercentage method calculates the percentages given by the add parameter
     * @param add is given by the DisplayGoalActivity class
     */
    void calculatePercentages(double add){
        runningTotal+=add;
        percentage = (runningTotal/durationNum)*100;
        if (percentage >= 100){
            isComplete = true;
        }
    }

    /**
     * The checkIfComplete method returns true if the goal is at or above 100 percent
     * @return true or false
     */
    boolean checkIfComplete(){
        return isComplete;
    }
}
