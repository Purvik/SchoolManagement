package com.ourwork.schoolmanagement.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.ourwork.schoolmanagement.fragments.SubjectFragment;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SubjectPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = SubjectPagerAdapter.class.getName();
    private ArrayList<SingleSubjectDetails> subjectDetails;
    private FragmentManager fm;

    public SubjectPagerAdapter(FragmentManager fm, ArrayList<SingleSubjectDetails> subjectDetails) {
        super(fm);
        this.subjectDetails = subjectDetails;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {

        Log.d(TAG, "getItem: " + position);
        Fragment fragment = SubjectFragment.newInstance(position, subjectDetails.get(position).getTopiclist());
        return fragment;
    }

    @Override
    public int getCount() {
        return subjectDetails.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return subjectDetails.get(position).getSubname();
    }
}
