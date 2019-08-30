package com.archisys.archisyscorelib.API;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AuthServiceImpl implements IService.IAuthService {

    @Override
    public void AppFirstStart(ApiClientCallBack apiClientCallBack) {

        //RestApiManager.getAPI().AppFirstStart().enqueue(new ApiRequestCallBack<>(apiClientCallBack));

        RestApiManager.getAPI().AppFirstStart().enqueue(new ApiRequestCallBack<String>(apiClientCallBack));
    }

}
