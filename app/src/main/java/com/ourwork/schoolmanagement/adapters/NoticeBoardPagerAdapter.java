package com.ourwork.schoolmanagement.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ourwork.schoolmanagement.fragments.NBEventFragment;
import com.ourwork.schoolmanagement.fragments.NBHolidayFragment;
import com.ourwork.schoolmanagement.fragments.NBNoticeFragment;
import com.ourwork.schoolmanagement.singleton.response.EventNode;
import com.ourwork.schoolmanagement.singleton.response.HolidayNode;
import com.ourwork.schoolmanagement.singleton.response.NoticeNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class NoticeBoardPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = NoticeBoardPagerAdapter.class.getName();
    private HashMap<String, Object> nbSectionhash;
    private FragmentManager fm;

    public NoticeBoardPagerAdapter(FragmentManager fm, HashMap<String, Object> timetable) {
        super(fm);
        this.nbSectionhash = timetable;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = NBEventFragment.newInstance("Events" , (ArrayList<EventNode>) nbSectionhash.get("Event"));
                break;
            case 1:
                fragment = NBNoticeFragment.newInstance("Notices" , (ArrayList<NoticeNode>) nbSectionhash.get("Notices"));
                break;
            case 2:
                fragment = NBHolidayFragment.newInstance("Holidays" , (ArrayList<HolidayNode>) nbSectionhash.get("Holiday"));
                break;
            default:
                break;
        }

         /* if (nbSectionhash.keySet().contains("notices")) {
               fragment = NBNoticeFragment.newInstance("notices" , nbSectionhash);
        }*/


        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Events";
            case 1:
                return "Notices";
            case 2:
                return "Holidays";
            default:
                return "?";
        }




    }
}
