package fi.mobsit.pilleriappi10.notifications;

import android.content.Context;
import android.content.SharedPreferences;

import fi.mobsit.pilleriappi10.MainActivity;

public class nofificationsChecker {

    private static final  String SHARED_PREFS = "Settings";//setting variable's value to value that is our preference file name
    private static final String NOTIFICATIONS = "notifications";//setting variable's value to value that is our preference tag's correct value

    public nofificationsChecker(){

    }

     public Boolean mayIsendNotification(Context Context){
        SharedPreferences sharedPreferences = Context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        boolean notifications = sharedPreferences.getBoolean(NOTIFICATIONS, false);
        return notifications;
    }
}
