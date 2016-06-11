package com.yosrahibrahim.instamonitor;

import android.app.Application;
import android.content.Context;

import instamonitor.yosrahibrahim.com.instamonitorlib.database.InstaMonitorDatabaseManager;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 */
public class InstaMonitorApp extends Application {

    private static InstaMonitorApp instance;

    /**
     *
     * @return InstaMonitorApp instance
     */
    public static synchronized InstaMonitorApp getInstance(){

        if (instance == null) {
            instance = new InstaMonitorApp();
        }
        return instance;
    }

    /**
     *
     * initialize db tables
     */
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initDatabase(getApplicationContext());
    }

    private void initDatabase(Context context){
        InstaMonitorDatabaseManager.getInstance().initializeTables(context);
    }
}
