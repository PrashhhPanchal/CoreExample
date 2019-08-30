package com.archisys.archisyscorelib.Utils.API;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequestCallBack<T> implements Callback<ApiClientResponseModel<T>> {

    ApiClientCallBack _apiClientCallBack;
    public ApiRequestCallBack(ApiClientCallBack apiClientCallBack){
        _apiClientCallBack = apiClientCallBack;
    }

    @Override
    public void onResponse(Call<ApiClientResponseModel<T>> call, Response<ApiClientResponseModel<T>> response) {
        Log.d("Time", String.valueOf(System.currentTimeMillis()));
        Log.i("API URL", call.request().url().toString());
        try {
            if (response.body() != null)
            {
                Log.i("API Response", new ObjectMapper().writeValueAsString(response.body().getData()));
            }
            else
            {
                //Log.i("API Response", new ObjectMapper().writeValueAsString(response.errorBody()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(_apiClientCallBack!=null) {
            _apiClientCallBack.Response(processResponse(response));
        }
    }

    @Override
    public void onFailure(Call<ApiClientResponseModel<T>> call, Throwable t) {
        Log.i("API URL", call.request().url().toString());
        if (t.getLocalizedMessage() != null)
        {
            Log.w("API Error", t.getLocalizedMessage());
        }
        Log.w("API Error", t.toString());
        if(_apiClientCallBack!=null){
            _apiClientCallBack.Response(new ApiClientResponseModel(t));
        }


    }
    private static <T> ApiClientResponseModel<T> processResponse(Response<ApiClientResponseModel<T>> response)
    {
        ApiClientResponseModel<T> apiClientResponseModel=new ApiClientResponseModel<>();
        if(response.code()==200){
            apiClientResponseModel=response.body();
        }else{
            apiClientResponseModel.setStatusCode(response.code());
            apiClientResponseModel.setStatusMessage(response.message());
        }
        return apiClientResponseModel;
    }
}
