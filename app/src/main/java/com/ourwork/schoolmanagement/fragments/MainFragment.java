package com.ourwork.schoolmanagement.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.MainActivity;
import com.ourwork.schoolmanagement.adapters.CustomGrid;
import com.ourwork.schoolmanagement.singleton.AccountUser;

/**
 * Created by Purvik Rana on 19-05-2018.
 */

public class MainFragment extends Fragment {

    View v;
    GridView mainGrid;
    AccountUser accountUser;
    static String ACCOUNT_KEY = "account_type";
    String titles[];/*{"Calender", "Take Test", "Facility", "Achievments", "Lunch Menu", "Forms"};*/
    TypedArray imgIds;/*[] = {R.drawable.ic_calender, R.drawable.ic_test, R.drawable.ic_facility, R.drawable.ic_achievements, R.drawable.ic_lunchmenu, R.drawable.ic_forms,R.drawable.ic_forms,R.drawable.ic_forms};*/
    //TypedArray stdImg;


    public static MainFragment newInstance(AccountUser accountUser) {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACCOUNT_KEY, accountUser);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_grid_main, container, false);

        accountUser = (AccountUser)getArguments().getSerializable(ACCOUNT_KEY);

        mainGrid = v.findViewById(R.id.grid_view);

        loadCustomGridAdapter(accountUser);


        return v;
    }

    private void loadCustomGridAdapter(AccountUser accountUser) {

        if (accountUser.getUsertype().equalsIgnoreCase("parent" ) || accountUser.getUsertype().equalsIgnoreCase("student" )) {

            titles = getResources().getStringArray(R.array.stud_parent_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.student_img_icons);

        } else if (accountUser.getUsertype().equalsIgnoreCase("admin" )) {

            titles = getResources().getStringArray(R.array.teacher_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.teacher_img_icons);


        } else if (accountUser.getUsertype().equalsIgnoreCase("teacher" )) {

            titles = getResources().getStringArray(R.array.teacher_dashboard_titles);
            imgIds = getResources().obtainTypedArray(R.array.teacher_img_icons);


        } else{


        }

        CustomGrid adapter = new CustomGrid(getActivity().getApplicationContext(), titles, imgIds);
        mainGrid.setAdapter(adapter);
        mainGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "You Clicked at " + titles[+position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
