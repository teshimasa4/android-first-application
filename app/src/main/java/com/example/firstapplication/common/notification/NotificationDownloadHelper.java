package com.example.firstapplication.common.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationCompat;

public class NotificationDownloadHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "download";
    private static final String CHANNEL_NAME = "サーバデータのダウンロード";

    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    public NotificationDownloadHelper(Context base, String contentTitle) {
        super(base);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
        manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(contentTitle);
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
    }

    public void notifyStart(int id, String contentText, int max) {
        builder.setContentText(contentText);
        builder.setProgress(max, 0, false);
        manager.notify(id, builder.build());
    }

    public void notifyProcessing(int id, int max, int progress) {
        builder.setProgress(max, progress, false);
        manager.notify(id, builder.build());
    }

    public void notifyComplete(int id, String contentText, int max) {
        builder.setContentText(contentText);
        builder.setProgress(max, 0, false);
        builder.setTimeoutAfter(10000);
        manager.notify(id, builder.build());
    }
}
