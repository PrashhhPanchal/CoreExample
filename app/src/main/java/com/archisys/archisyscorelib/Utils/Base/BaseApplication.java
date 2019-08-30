package com.archisys.archisyscorelib.Utils.Base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

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

        Resources res =getResources();
        Configuration newConfig = new Configuration(res.getConfiguration());
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new RoundedBitmapDisplayer(1000)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .build();
        ImageLoader.getInstance().init(config);
        updateScalingConfiguation(newConfig);

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("FindMyTrade.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = null;
        try {
            realm= Realm.getDefaultInstance();
            realm.setDefaultConfiguration(realmConfig);
            realm = Realm.getInstance(realmConfig);
            Log.i("Realm is located at:", realm.getConfiguration().getRealmDirectory().getAbsolutePath());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

//        updateScalingConfiguation(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    private void updateScalingConfiguation(Configuration newConfig){
        if(!allowScalling) {
            if (newConfig.fontScale != 1.0f) {
                newConfig.fontScale = 1.0f;
                getResources().updateConfiguration(newConfig, getResources().getDisplayMetrics());

//                onCreate();

//            super.onConfigurationChanged(newConfig);
            }
        }
    }



}
