package mubstimor.android.kwikorder.application;

import android.app.Application;
import android.content.Context;

public class KwikApp extends Application {
    private static KwikApp instance;

    public static KwikApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}