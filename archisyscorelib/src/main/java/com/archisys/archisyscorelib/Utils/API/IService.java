package com.archisys.archisyscorelib.Utils.API;


/*
*
* This service class used for call the all api or dummy data by specific interface
*
* */
public class IService {

    //this interface only used for Authentication.
    public interface IAuthService {

        void AppFirstStart(ApiClientCallBack apiClientCallBack);

    }
}
