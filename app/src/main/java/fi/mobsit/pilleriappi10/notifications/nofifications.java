package fi.mobsit.pilleriappi10.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import fi.mobsit.pilleriappi10.R;

/** @author Tomi Jumppanen */

public class nofifications {

    private static final  String SHARED_PREFS = "Settings";//setting variable's value to value that is our preference file name
    private static final String NOTIFICATIONS = "notifications";//setting variable's value to value that is our preference tag's correct valueä
    Boolean notifications;
    Context context;
    private String CHANNEL_ID;

    public nofifications(Context context){
        this.context = context;
    }

    public Boolean toBool() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        this.notifications = sharedPreferences.getBoolean(NOTIFICATIONS, false);
        return notifications;
    }
}
