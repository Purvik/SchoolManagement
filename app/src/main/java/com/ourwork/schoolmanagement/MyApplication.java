package com.ourwork.schoolmanagement;

import android.app.Application;

import com.ourwork.schoolmanagement.apicall.ApiCall;
import com.ourwork.schoolmanagement.utils.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Purvik Rana on 16-06-2018.
 */

public class MyApplication extends Application {

    public static Retrofit retrofit;
    public static ApiCall apiCall;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(ApiCall.class);
    }
}
