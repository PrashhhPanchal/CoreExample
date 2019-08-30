package com.archisys.archisyscorelib.Base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BaseApplication extends MultiDexApplication {

    static Context _CurrentContext;
    public static Context getCurrentContext(){
        return _CurrentContext;
    }

    public BaseApplication(){
        _CurrentContext = this;
    }

    private boolean allowScalling=false;

    public boolean isAllowScalling() {
        return allowScalling;
    }

    public void setAllowScalling(boolean allowScalling) {
        this.allowScalling = allowScalling;
    }

    // Gloabl declaration of variable to use in whole app

    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused
    }



    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

//        updateScalingConfiguation(newConfig);
        super.onConfigurationChanged(newConfig);
    }

}
