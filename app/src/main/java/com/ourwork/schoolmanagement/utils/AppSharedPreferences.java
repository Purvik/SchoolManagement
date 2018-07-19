package com.ourwork.schoolmanagement.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;

/**
 * Created by Purvik Rana on 19-05-2018.
 */


public class AppSharedPreferences {


    private static final String TAG = "AppPreferences";

    public static void storeUserLogInUserAppPreferences(SharedPreferences pref, StudentParentResp studentParentResp) {
        SharedPreferences.Editor prefsEditor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(studentParentResp);

        Log.d(TAG, "storeUserLogInUserAppPreferences: " + json);
        prefsEditor.putString("LoggedInAccountUser", json);
        prefsEditor.apply();
    }

    public static StudentParentResp getUserlogInAppPreferences(SharedPreferences pref) {
        Gson gson = new Gson();
        String json = pref.getString("LoggedInAccountUser", "");

        Log.e(TAG, "getUserlogInAppPreferences: " + json);

        return gson.fromJson(json, StudentParentResp.class);

    }

    public static void removeStoredUserAccount(SharedPreferences mPrf) {

        SharedPreferences.Editor prefsEditor = mPrf.edit();
        prefsEditor.remove("LoggedInAccountUser").apply();

    }


}
