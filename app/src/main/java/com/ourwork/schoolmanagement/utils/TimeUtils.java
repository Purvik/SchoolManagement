package com.ourwork.schoolmanagement.utils;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class TimeUtils {

    private static final String TAG = TimeUtils.class.getName();
    static HashMap<Integer,String> times = new HashMap<>();

    public HashMap<Integer, String> getTimes() {
        return times;
    }

    public void setTimes() {
        times.put(0, "12 AM");
        times.put(1, "1 AM");
        times.put(2, "2 AM");
        times.put(3, "3 AM");
        times.put(4, "4 AM");
        times.put(5, "5 AM");
        times.put(6, "6 AM");
        times.put(7, "7 AM");
        times.put(8, "8 AM");
        times.put(9, "9 AM");
        times.put(10, "10 AM");
        times.put(11, "11 AM");
        times.put(12, "12 PM");
        times.put(13, "1 PM");
        times.put(14, "2 PM");
        times.put(15, "3 PM");
        times.put(16, "4 PM");
        times.put(17, "5 PM");
        times.put(18, "6 PM");
        times.put(19, "7 PM");
        times.put(20, "8 PM");
        times.put(21, "9 PM");
        times.put(22, "10 PM");
        times.put(23, "11 PM");
    }

    public static String getTime(int time){

        if (time >= 0 && time <= 23) {

            Log.d(TAG, time +" int || getTime: " + times.get(time));
            return times.get(time);
        }else{
            return "?";
        }

    }
}
