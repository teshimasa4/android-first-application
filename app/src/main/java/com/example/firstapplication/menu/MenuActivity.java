package com.example.firstapplication.menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstapplication.FirstApplication;
import com.example.firstapplication.R;
import com.example.firstapplication.login.LoginActivity;
import com.example.firstapplication.service.MyIntentService;
import com.example.firstapplication.service.MyIntentService2;

public class MenuActivity extends AppCompatActivity {

    // UI references.
    private Button mOrderRegisterButton;
    private Button mOrderConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mOrderRegisterButton = (Button) findViewById(R.id.order_register_button);
        mOrderRegisterButton.setEnabled(((FirstApplication) this.getApplication()).buttonEnabled);
        mOrderRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        mOrderConfirmButton = (Button) findViewById(R.id.order_confirm_button);
        mOrderConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(MenuActivity.this).registerReceiver(broadcastReceiver, new IntentFilter(MyIntentService.MYINTENTSERVICE_MESSENGER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(MenuActivity.this).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntentService.MYINTENTSERVICE_MESSENGER)) {
                boolean enabled = intent.getBooleanExtra("enabled", false);
                mOrderRegisterButton.setEnabled(enabled);
            }
        }
    };
}
