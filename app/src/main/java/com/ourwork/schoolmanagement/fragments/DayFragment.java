package com.ourwork.schoolmanagement.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.ClassesAdapter;
import com.ourwork.schoolmanagement.singleton.SingleClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class DayFragment extends Fragment {


    private static final String TAG = DayFragment.class.getName();
    private static  String ARG_DAY = "day";
    private static String ARG_TIMETABLE = "timetable";
    private RecyclerView recyclerView;
    Context mContext;

    public static DayFragment newInstance(int day, ArrayList<SingleClass> timetable){

        DayFragment dayFragment = new DayFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_DAY,day);
        arguments.putSerializable(ARG_TIMETABLE, timetable);
        dayFragment.setArguments(arguments);

        Log.d(TAG, "newInstance: " + arguments);
        return dayFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_day, container, false);

        mContext = getActivity();

        recyclerView =v.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(mLayoutManager);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int day = getArguments().getInt(ARG_DAY);
        Serializable timetable = getArguments().getSerializable(ARG_TIMETABLE);
        ArrayList<SingleClass> arrayList = (ArrayList<SingleClass>)timetable;

        if (day == 0 || timetable == null) {
            return;
        }

        RecyclerView.Adapter adapter = new ClassesAdapter(arrayList, day, mContext);
        recyclerView.setAdapter(adapter);
    }
}
