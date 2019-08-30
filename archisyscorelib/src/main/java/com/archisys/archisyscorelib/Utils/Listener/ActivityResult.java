package com.archisys.archisyscorelib.Utils.Listener;

import android.content.Intent;

public class ActivityResult {

    IActivityResultListener iActivityResultListener;

    public ActivityResult()
    {

    }

    public ActivityResult(IActivityResultListener iActivityResultListeners, int requestcode, int resultcode, Intent data)
    {
        this.iActivityResultListener = iActivityResultListeners;
        iActivityResultListener.OnActivityResults(requestcode, resultcode, data);
    }
}
