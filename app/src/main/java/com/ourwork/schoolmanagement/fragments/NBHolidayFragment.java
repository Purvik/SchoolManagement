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
import com.ourwork.schoolmanagement.adapters.HolidayNodeAdapter;
import com.ourwork.schoolmanagement.singleton.response.HolidayNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class NBHolidayFragment extends Fragment {


    private static final String TAG = NBHolidayFragment.class.getName();
    private static  String ARG_SECTION = "section";
    private static  String ARG_SECTION_NAME = "section_name";
    private static String ARG_OBJECTS = "objects";
    private RecyclerView recyclerView;
    Context mContext;
    private HolidayNodeAdapter adapter;

    public static NBHolidayFragment newInstance(String section, ArrayList<HolidayNode> sections){

        NBHolidayFragment dayFragment = new NBHolidayFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_SECTION_NAME,section);
//        arguments.putString(ARG_SECTION_NAME, timetable );
        //ArrayList<NoticeNode> list= (ArrayList<NoticeNode>) sections.get(section);
        arguments.putParcelableArrayList(ARG_OBJECTS, (ArrayList<HolidayNode>) sections);
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

        ArrayList<HolidayNode> holidayNodeArrayList= getArguments().getParcelableArrayList(ARG_OBJECTS);
        adapter = new HolidayNodeAdapter(mContext, holidayNodeArrayList);
        recyclerView.setAdapter(adapter);
    }
}
