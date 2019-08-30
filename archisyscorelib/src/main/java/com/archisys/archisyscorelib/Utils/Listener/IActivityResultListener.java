package com.archisys.archisyscorelib.Utils.Listener;

import android.content.Intent;

public interface IActivityResultListener {

    public void OnActivityResults(int requestcode, int resultcode, Intent data);
}
