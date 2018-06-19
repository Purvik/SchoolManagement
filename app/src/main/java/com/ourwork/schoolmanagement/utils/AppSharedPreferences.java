package com.ourwork.schoolmanagement.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;

/**
 * Created by Purvik Rana on 19-05-2018.
 */



public class AppSharedPreferences {


    private static final String TAG = "AppPreferences";

    public static void  storeAppPreferences(SharedPreferences pref, LoginResponse loginResponse){
        SharedPreferences.Editor prefsEditor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        Log.d(TAG, "storeAppPreferences: " + json);
        prefsEditor.putString("LoggedInAccountUser", json);
        prefsEditor.apply();
    }

    public static LoginResponse getAppPreferences(SharedPreferences pref){
        Gson gson = new Gson();
        String json = pref.getString("LoggedInAccountUser", "");
        LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);
        Log.d(TAG, json + "getAppPreferences: " + loginResponse);
        return loginResponse;
    }
    public static void removeStoredUserAccount(SharedPreferences mPrf){

        SharedPreferences.Editor prefsEditor = mPrf.edit();
        prefsEditor.remove("LoggedInAccountUser").apply();

    }




}
