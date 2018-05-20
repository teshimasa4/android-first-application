package com.example.firstapplication;

import android.app.Application;
import android.content.Context;

public class FirstApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        FirstApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return FirstApplication.context;
    }
}
