package com.ourwork.schoolmanagement.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.HomeMenuAdapter;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;

/**
 * Created by Purvik Rana on 19-05-2018.
 */

public class MainFragment extends Fragment {

    View v;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    StudentParentResp studentParentResp;
    static String ACCOUNT_KEY = "account_type";
    Context mContext;
    String titles[];/*{"Calender", "Take Test", "Facility", "Achievments", "Lunch Menu", "Forms"};*/
    TypedArray imgIds;/*[] = {R.drawable.ic_calender, R.drawable.ic_test, R.drawable.ic_facility, R.drawable.ic_achievements, R.drawable.ic_lunchmenu, R.drawable.ic_forms,R.drawable.ic_forms,R.drawable.ic_forms};*/
    //TypedArray stdImg;


    public static MainFragment newInstance(StudentParentResp studentParentResp) {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACCOUNT_KEY, studentParentResp);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_grid_main, container, false);

        mContext= getActivity();

        studentParentResp = (StudentParentResp) getArguments().getSerializable(ACCOUNT_KEY);

        recyclerView = v.findViewById(R.id.recycler_view);

        // Define a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(mLayoutManager);

        loadCustomGridAdapter(studentParentResp);


        return v;
    }

    private void loadCustomGridAdapter(StudentParentResp studentParentResp) {

        if (studentParentResp.getUsertype().equalsIgnoreCase("parent" ) || studentParentResp.getUsertype().equalsIgnoreCase("student" )) {

            titles = getResources().getStringArray(R.array.stud_parent_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.student_img_icons);

        } else if (studentParentResp.getUsertype().equalsIgnoreCase("admin" )) {

            titles = getResources().getStringArray(R.array.admin_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.admin_img_icons);


        } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher" )) {

            titles = getResources().getStringArray(R.array.teacher_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.teacher_img_icons);


        } else{


        }


       mAdapter = new HomeMenuAdapter(titles, imgIds, getActivity(), studentParentResp);

       recyclerView.setAdapter(mAdapter);

    }
}
