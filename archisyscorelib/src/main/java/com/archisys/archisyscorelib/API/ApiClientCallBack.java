package com.archisys.archisyscorelib.API;

/**
 * Created by prakash on 13/6/16.
 */
//this interface used for responce of json in string from asynctask for web service.
public interface ApiClientCallBack<TRes>
{
    void Response(ApiClientResponseModel<TRes> response);
}
