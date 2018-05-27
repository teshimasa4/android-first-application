package com.example.firstapplication.common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "download";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "データのダウンロード状況", NotificationManager.IMPORTANCE_LOW);
        getManager().createNotificationChannel(channel);
    }


    private NotificationManager getManager() {
        if (manager == null) {
            manager = getSystemService(NotificationManager.class);
        }
        return manager;
    }

    public NotificationCompat.Builder getNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        return mBuilder;
    }

    public void notify(int id, NotificationCompat.Builder builder) {
        getManager().notify(id, builder.build());
    }

    private boolean isOreoOrLater() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O;
    }
}
