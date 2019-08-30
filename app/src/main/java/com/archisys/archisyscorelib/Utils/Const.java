package com.archisys.archisyscorelib.Utils;

import android.Manifest;

/**
 * Created by archisys on 5/9/17.
 */

public class Const {

    public static boolean isInternetConnected = true;
    public static final boolean isShowing = true;

    //Permissions
    public static final String Permission_Coarse_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String Permission_Fine_Location = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String Permission_Internet = Manifest.permission.INTERNET;
    public static final String Permission_Read_Phone_state = Manifest.permission.READ_PHONE_STATE;
    public static final String Permission_Access_Network_state = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String Permission_Camera = Manifest.permission.CAMERA;
    public static final String Permission_Read_Contact = Manifest.permission.READ_CONTACTS;
    public static final String Permission_Read_External_Storage = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String Permission_Write_External_Storage = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static final String[] permissions = {Const.Permission_Coarse_Location,
            Const.Permission_Fine_Location,
            Const.Permission_Access_Network_state,
            Const.Permission_Camera,
            Const.Permission_Access_Network_state,
            Const.Permission_Internet,
            Const.Permission_Read_Phone_state,
            Const.Permission_Read_Contact,
            Const.Permission_Read_External_Storage,
            Const.Permission_Write_External_Storage};




}
