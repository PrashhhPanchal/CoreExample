package com.archisys.archisyscorelib.Utils.Listener;

public class PermissionResult {

    IPermissionResultListener iPermissionResultListener;

    public PermissionResult()
    {

    }

    public PermissionResult(IPermissionResultListener iPermissionResultListenerPass)
    {
        this.iPermissionResultListener = iPermissionResultListenerPass;
        iPermissionResultListener.OnPermissionResult();
    }
}
