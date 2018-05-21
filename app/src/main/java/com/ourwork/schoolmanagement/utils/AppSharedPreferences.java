package com.ourwork.schoolmanagement.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.ourwork.schoolmanagement.singleton.AccountUser;

/**
 * Created by Purvik Rana on 19-05-2018.
 */



public class AppSharedPreferences {


    private static final String TAG = "AppPreferences";

    public static void  storeAppPreferences(SharedPreferences pref, AccountUser accountUser){
        SharedPreferences.Editor prefsEditor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(accountUser);
        Log.d(TAG, "storeAppPreferences: " + json);
        prefsEditor.putString("LoggedInAccountUser", json);
        prefsEditor.apply();
    }

    public static AccountUser getAppPreferences(SharedPreferences pref){
        Gson gson = new Gson();
        String json = pref.getString("LoggedInAccountUser", "");
        AccountUser accountUser = gson.fromJson(json, AccountUser.class);
        Log.d(TAG, json + "getAppPreferences: " + accountUser);
        return accountUser;
    }




}
