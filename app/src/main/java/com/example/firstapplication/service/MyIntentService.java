package com.example.firstapplication.service;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.firstapplication.common.NotificationHelper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int notificationId = 1;

        NotificationHelper notificationHelper = new NotificationHelper(this);
        NotificationCompat.Builder mBuilder = notificationHelper.getNotification();
        mBuilder.setContentTitle("Picture Download");
        mBuilder.setContentText("Download in progress");

        mBuilder.setProgress(5, 0, false);
        notificationHelper.notify(notificationId, mBuilder);

        for(int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                mBuilder.setProgress(5, i + 1, false);
                notificationHelper.notify(notificationId, mBuilder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        mBuilder.setContentText("Download complete");
        mBuilder.setProgress(0,0,false);
//        mBuilder.setTimeoutAfter(3000);
        notificationHelper.notify(notificationId, mBuilder);
    }
}
