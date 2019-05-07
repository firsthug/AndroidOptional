package com.example.proiectandroid.Database;

import android.app.Application;

import androidx.room.Room;

public class ApplicationController extends Application {


    private static ApplicationController mInstance;

    private static AppDatabase mAppDatabase;

    public static ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        // Get a database instance to work with
        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "ProjectDB").build();
    }

    public static AppDatabase getAppDatabase(){
        return mAppDatabase;
    }
}

