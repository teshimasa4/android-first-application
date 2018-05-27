package com.example.firstapplication.common.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    private NotificationManager manager;

    public NotificationHelper(Context base, String channelId, String channelName) {
        super(base);

        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
        getManager().createNotificationChannel(channel);
    }

    NotificationManager getManager() {
        if (manager == null) {
            manager = getSystemService(NotificationManager.class);
        }
        return manager;
    }

    public NotificationCompat.Builder getNotification(String channelId) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId);
        mBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        return mBuilder;
    }

    public void notify(int id, NotificationCompat.Builder builder) {
        getManager().notify(id, builder.build());
    }
}
