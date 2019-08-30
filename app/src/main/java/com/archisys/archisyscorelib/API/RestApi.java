package com.archisys.archisyscorelib.API;



import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestApi {

    @GET(Api.AppFirstStart)
    Call<ApiClientResponseModel<String>> AppFirstStart();


}
