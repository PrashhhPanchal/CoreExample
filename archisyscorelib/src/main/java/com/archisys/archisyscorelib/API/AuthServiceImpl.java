package com.archisys.archisyscorelib.API;


public class AuthServiceImpl implements IService.IAuthService {

    @Override
    public void AppFirstStart(ApiClientCallBack apiClientCallBack) {

        //RestApiManager.getAPI().AppFirstStart().enqueue(new ApiRequestCallBack<>(apiClientCallBack));

        RestApiManager.getAPI().AppFirstStart().enqueue(new ApiRequestCallBack<String>(apiClientCallBack));
    }

}
