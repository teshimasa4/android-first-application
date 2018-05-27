package com.example.firstapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.example.firstapplication.common.notification.NotificationDownloadHelper;

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

        NotificationDownloadHelper notificationHelper = new NotificationDownloadHelper(this, "Data Download");

        int max = 5;
        notificationHelper.notifyStart(notificationId, "Download in progress", max);

        for(int i = 0; i < max; i++) {
            try {
                Thread.sleep(1000);
                notificationHelper.notifyProcessing(notificationId, max, i + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notificationHelper.notifyComplete(notificationId, "Download complete", max);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "ダウンロードを開始します。", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "ダウンロードが完了しました。", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
