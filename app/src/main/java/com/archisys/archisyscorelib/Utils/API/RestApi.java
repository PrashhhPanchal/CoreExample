package com.archisys.archisyscorelib.Utils.API;



import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET(Api.AppFirstStart)
    Call<ApiClientResponseModel<String>> AppFirstStart();


}
