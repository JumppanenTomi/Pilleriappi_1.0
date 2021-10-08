package fi.mobsit.pilleriappi10.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import fi.mobsit.pilleriappi10.R;

/** @author Tomi Jumppanen */

public class nofificationManager {

    private static final  String SHARED_PREFS = "Settings";//setting variable's value to value that is our preference file name
    private static final String NOTIFICATIONS = "notifications";//setting variable's value to value that is our preference tag's correct valueä
    Boolean notifications;
    Context context;
    private String CHANNEL_ID;

    public nofificationManager(Context context){
        this.context = context;
    }

    public Boolean toBool() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);//initializing sharedpreferences to correct xml-file and setting it to private so other instances cant access it
        this.notifications = sharedPreferences.getBoolean(NOTIFICATIONS, false);
        return notifications;
    }

    public void createNotificationChannel() {
        this.CHANNEL_ID = "remindMedicine";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.notification_channel_name);
            String description = context.getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = this.context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void newNotification(String CHANNEL_ID, Integer NOTIFICATION_ID){
        this.CHANNEL_ID = CHANNEL_ID;
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, this.CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text_content))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{4000,4000,4000,4000,4000});
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
