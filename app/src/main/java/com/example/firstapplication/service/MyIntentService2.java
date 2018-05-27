package com.example.firstapplication.service;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String channelId = "default2"; // 通知チャンネルのIDにする任意の文字列
        int notificationId = 2;

        NotificationChannel channel = new NotificationChannel(channelId, "データのダウンロード状況2", NotificationManager.IMPORTANCE_HIGH);

        NotificationManager mNotifyManager = getSystemService(NotificationManager.class);
        mNotifyManager.createNotificationChannel(channel);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId);
        mBuilder.setContentTitle("Picture2 Download");
        mBuilder.setContentText("Download in progress");
        mBuilder.setSmallIcon(android.R.drawable.ic_notification_overlay);
        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);

        mBuilder.setProgress(5, 0, false);
        NotificationManagerCompat.from(this).notify(2, mBuilder.build());

        for(int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);
                mBuilder.setProgress(5, i + 1, false);
                NotificationManagerCompat.from(this).notify(2, mBuilder.build());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        mBuilder.setContentText("Download complete");
        mBuilder.setProgress(0,0,false);
//        mBuilder.setTimeoutAfter(3000);
        NotificationManagerCompat.from(this).notify(2, mBuilder.build());
    }
}
