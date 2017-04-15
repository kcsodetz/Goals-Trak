package edu.sodetzpurdue.goals_trak;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Ken Sodetz on 4/14/2017.
 */

public class NotificationsManager {

    private int hour, minute;
    private String ampm, frequency;
    public NotificationsManager(int hour, int minute, String ampm, String frequency){
        this.hour = hour;
        this.minute = minute;
        this.ampm = ampm;
        this.frequency = frequency;
    }

    private Context context;
    private NotificationCompat.Builder builder =
            new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_add_black_24dp).setContentTitle("Test Notification").setContentText("This is a comprehensive test");

    private Intent resultIntent = new Intent(context, MainActivity.class);

    // Because clicking the notification opens a new ("special") activity, there's
    // no need to create an artificial back stack.
    private PendingIntent resultPendingIntent =
            PendingIntent.getActivity(
                    context,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );





    public void pushNofitication(){
        // Sets an ID for the notification
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        /*NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);*/
// Builds the notification and issues it.
        //mNotifyMgr.notify(mNotificationId, builder.build());
    }



}
