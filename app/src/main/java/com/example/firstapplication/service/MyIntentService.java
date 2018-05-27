package com.example.firstapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.firstapplication.common.notification.NotificationHelper;
import com.example.firstapplication.common.notification.NotificationUploadHelper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final int notificationId = 1;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        NotificationUploadHelper notificationHelper = new NotificationUploadHelper(this);
        NotificationCompat.Builder mBuilder = notificationHelper.getNotification("①ダウンロード");

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
        mBuilder.setTimeoutAfter(10000);
        notificationHelper.notify(notificationId, mBuilder);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "ダウンロードを開始します。", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "ダウンロードが完了しました。", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
