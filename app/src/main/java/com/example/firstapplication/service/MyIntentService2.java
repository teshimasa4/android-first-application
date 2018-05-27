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
public class MyIntentService2 extends IntentService {

    private static final int notificationId = 2;

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        NotificationDownloadHelper notificationHelper = new NotificationDownloadHelper(this, "Data Download2");

        int max = 10;
        notificationHelper.notifyStart(notificationId, "Download2 in progress", max);

        for(int i = 0; i < max; i++) {
            try {
                Thread.sleep(1000);
                notificationHelper.notifyProcessing(notificationId, max, i + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notificationHelper.notifyComplete(notificationId, "Download2 complete", max);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "ダウンロード②を開始します。", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "ダウンロード②が完了しました。", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
