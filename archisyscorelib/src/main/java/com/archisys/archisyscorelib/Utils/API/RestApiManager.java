package com.archisys.archisyscorelib.Utils.API;

import android.content.Context;
import android.util.Log;

import com.archisys.archisyscorelib.Utils.Base.BaseApplication;
import com.archisys.archisyscorelib.Utils.Model.Device;
import com.archisys.archisyscorelib.Utils.Utils.CommonUtils;
import com.archisys.archisyscorelib.Utils.Utils.Prefs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RestApiManager {

    static Retrofit retrofit = null;

    static  <T> T get(Class<T> tClass) {

        final Context context = BaseApplication.getCurrentContext();
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request.Builder builder = chain.request().newBuilder();
                        if (CommonUtils.isNetworkAvailable(context))
                        {
                            builder.addHeader("Cache-Control", "public, max-age=" + 5);
                        }
                        else
                        {
                            builder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1);
                        }
                        builder.addHeader("Content-type", "application/json");
                        builder.addHeader("Device", Device.getInstance(context).toString());
                        if (Prefs.getValue(context, Prefs.TOKEN, "") != null) {
                            builder.addHeader("Authorization", "Bearer "+ Prefs.getToken());
                        }

                        Log.d("Device", Device.getInstance(context).toString());
                        Log.d("Authorization", "Bearer "+ Prefs.getToken());
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(Api.BaseUrl)
                .build();

        Log.d("Time", String.valueOf(System.currentTimeMillis()));
        return retrofit.create(tClass);
    }

    static RestApi restApi = null;
    public static RestApi getAPI(){
        if(restApi == null)
            restApi = get(RestApi.class);
        return restApi;
    }




}