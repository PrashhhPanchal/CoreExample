package com.archisys.archisyscorelib.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.archisys.archisyscorelib.Listener.NetworkChangedListener;

public class InternetConnectorReceiver extends BroadcastReceiver {

    public NetworkChangedListener networkChangedListener;

    public InternetConnectorReceiver(){

    }
    public InternetConnectorReceiver(NetworkChangedListener networkChangedListener){
        this.networkChangedListener=networkChangedListener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
    Log.d("onReceive","onReceive InternetConnectorReceiver");

        if(networkChangedListener!=null){
            networkChangedListener.NetworkChanged();
        }
    }

}
