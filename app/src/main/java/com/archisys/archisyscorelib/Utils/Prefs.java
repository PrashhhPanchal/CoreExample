package com.archisys.archisyscorelib.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.archisys.archisyscorelib.Base.BaseApplication;

public class Prefs {

    private static SharedPreferences sharedPreference = null;

    public static final String PREFS_FILENAME = "FindMyTrade";
    public static final String USERPIC = "userPic";
    public static final String USERACCEPT = "AcceptKey";
    public static final String FullName = "stock_fullname";
    public static final String Email = "stock_email";
    public static final String Authorization= "stock_Authorization";
    public static final String MOBILE_NUMBER = "stockbook_mobile_number";
    public static final String ISFirst = "FirstTime";
    public static final String OTPPREFNUMBER = "OTPPREFNUMBER"; // 0 -> already login , 1-> from LOgin OTP , 2-> from Registration OTP
    public static final String UUID = "UUID";

    public static final String DEVICE_PREF = "Device_Pref";
    public static final String TOKEN = "Token";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public static final String FCMToken = "FCMToken";

    @SuppressWarnings("static-access")
    private static void openPrefs(Context context) {
        sharedPreference = context.getSharedPreferences(Prefs.PREFS_FILENAME,
                context.MODE_PRIVATE);
    }

    public static void clearall(Context context) {
        boolean isFirst = Prefs.getValueBoolen(context, IS_FIRST_TIME_LAUNCH, true);
        String deviceHeader = Prefs.getDeviceHeader();
        Prefs.openPrefs(context);
        Editor prefsEdit = Prefs.sharedPreference.edit();
        prefsEdit.clear();
        prefsEdit.commit();
        Prefs.setValueBoolen(context, IS_FIRST_TIME_LAUNCH, isFirst);
        //Prefs.setDeviceHeader(deviceHeader);
    }

    public static void setValue(Context context, String key, String value) {

        Prefs.openPrefs(context);

        Editor prefsEdit = Prefs.sharedPreference.edit();

        prefsEdit.putString(key, value);
        prefsEdit.commit();

        prefsEdit = null;
        Prefs.sharedPreference = null;
    }
    public static void setValueInt(Context context, String key, int value) {

        Prefs.openPrefs(context);

        Editor prefsEdit = Prefs.sharedPreference.edit();

        prefsEdit.putInt(key, value);
        prefsEdit.commit();

        prefsEdit = null;
        Prefs.sharedPreference = null;
    }

    public static boolean hasPrefs(Context context, String key) {

        Prefs.openPrefs(context);

        boolean prefsreturn=Prefs.sharedPreference.contains(key);


        Prefs.sharedPreference = null;
        return prefsreturn;
    }

    public static int gettheme(Context context, String key, int value) {

        Prefs.openPrefs(context);

        int result = Prefs.sharedPreference.getInt(key, value);

        Prefs.sharedPreference = null;

        return result;
    }

    public static String getValue(Context context, String key, String value) {

        Prefs.openPrefs(context);

        if (Prefs.sharedPreference.contains(key)) {
            String result = Prefs.sharedPreference.getString(key, value);
            Prefs.sharedPreference = null;

            return result;
        }

        return null;
    }

    public static int getValueInt(Context context, String key, int value) {

        Prefs.openPrefs(context);

        if (Prefs.sharedPreference.contains(key)) {
            int result = Prefs.sharedPreference.getInt(key, value);
            Prefs.sharedPreference = null;

            return result;
        }

        return 0;
    }


    public static void setValueBoolen(Context context, String key, Boolean ble) {
        Prefs.openPrefs(context);

        Editor prefsEdit = Prefs.sharedPreference.edit();

        prefsEdit.putBoolean(key, ble);
        prefsEdit.commit();

        prefsEdit = null;
        Prefs.sharedPreference = null;
    }

    public static Boolean getValueBoolen(Context context, String key, Boolean ble) {
        Prefs.openPrefs(context);

        Boolean result = Prefs.sharedPreference.getBoolean(key, ble);

        Prefs.sharedPreference = null;

        return result;
    }

    public static void setFirstTimeLaunch(boolean isFirstTime) {

        Prefs.openPrefs(BaseApplication.getCurrentContext());
        Editor editor = Prefs.sharedPreference.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public static boolean isFirstTimeLaunch() {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        return Prefs.sharedPreference.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }




    //----------------------------------------

    public static String getFCMToken()
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        String result = Prefs.sharedPreference.getString(FCMToken, "");
        Prefs.sharedPreference = null;
        return result;
    }

    public static void setFCMToken(String value)
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        Editor prefsEdit = Prefs.sharedPreference.edit();
        prefsEdit.putString(FCMToken, value);
        prefsEdit.commit();
        Log.d("FCM", value);
        prefsEdit = null;
        Prefs.sharedPreference = null;
    }

    public static void setDeviceHeader(String value)
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        Editor prefsEdit = Prefs.sharedPreference.edit();
        prefsEdit.putString(DEVICE_PREF, value);
        prefsEdit.commit();
        prefsEdit = null;
        Prefs.sharedPreference = null;
    }

    public static String getDeviceHeader()
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        String result = Prefs.sharedPreference.getString(DEVICE_PREF, "");
        Prefs.sharedPreference = null;
        return result;
    }

    public static void setToken(String value)
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        Editor prefsEdit = Prefs.sharedPreference.edit();
        prefsEdit.putString(TOKEN, value);
        prefsEdit.commit();
        prefsEdit = null;
        Prefs.sharedPreference = null;
    }

    public static String getToken()
    {
        Prefs.openPrefs(BaseApplication.getCurrentContext());
        String result = Prefs.sharedPreference.getString(TOKEN, "");
        Prefs.sharedPreference = null;
        return result;
    }


}
