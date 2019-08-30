package com.archisys.archisyscorelib.Listener;

import android.content.Intent;

public interface IActivityResultListener {

    public void OnActivityResults(int requestcode, int resultcode, Intent data);
}
