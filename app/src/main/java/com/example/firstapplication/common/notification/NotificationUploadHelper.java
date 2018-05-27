package com.example.firstapplication.common.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class NotificationUploadHelper extends NotificationHelper {

    private static final String CHANNEL_ID = "download";
    private static final String CHANNEL_Name = "ダウンロード";

    public NotificationUploadHelper(Context base) {
        super(base, CHANNEL_ID, CHANNEL_Name);
    }

    public NotificationCompat.Builder getNotification(String contentTitle) {
        return super.getNotification(CHANNEL_ID).setContentTitle(contentTitle);
    }
}
