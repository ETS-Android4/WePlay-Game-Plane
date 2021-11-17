package com.weplay.game;

import android.app.Application;

import com.weplay.WePlay;

public class GameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WePlay.init(this);
    }
}