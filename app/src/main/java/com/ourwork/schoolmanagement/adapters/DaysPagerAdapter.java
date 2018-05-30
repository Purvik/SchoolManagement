package com.ourwork.schoolmanagement.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.ourwork.schoolmanagement.fragments.DayFragment;
import com.ourwork.schoolmanagement.singleton.SingleClass;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class DaysPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = DaysPagerAdapter.class.getName();
    private ArrayList<SingleClass> timetable;
    private FragmentManager fm;

    public DaysPagerAdapter(FragmentManager fm, ArrayList<SingleClass> timetable) {
        super(fm);
        this.timetable = timetable;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {

        Log.d(TAG, "getItem: " + position);
        Fragment fragment = DayFragment.newInstance(position +1 , timetable);

        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            default:
                return "?";
        }




    }
}
