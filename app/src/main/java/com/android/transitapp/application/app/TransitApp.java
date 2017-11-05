package com.android.transitapp.application.app;

import android.app.Application;

/**
 * Created by Mohamed Elgendy.
 */

public class TransitApp extends Application {

    private static TransitApp instance;

    public static TransitApp getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Something went horribly wrong!!, no application attached!");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}

