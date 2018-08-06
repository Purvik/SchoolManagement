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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.EventNodeAdapter;
import com.ourwork.schoolmanagement.singleton.response.EventNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class NBEventFragment extends Fragment {


    private static final String TAG = NBEventFragment.class.getName();
    private static  String ARG_SECTION = "section";
    private static  String ARG_SECTION_NAME = "section_name";
    private static String ARG_OBJECTS = "objects";
    private RecyclerView recyclerView;
    LinearLayout emptyDisplay;
    TextView tvEmptyView;
    Context mContext;
    private EventNodeAdapter adapter;

    public static NBEventFragment newInstance(String section, ArrayList<EventNode> sections){

        NBEventFragment dayFragment = new NBEventFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_SECTION_NAME,section);
//        arguments.putString(ARG_SECTION_NAME, timetable );
        //ArrayList<NoticeNode> list= (ArrayList<NoticeNode>) sections.get(section);
        arguments.putParcelableArrayList(ARG_OBJECTS, (ArrayList<EventNode>) sections);
        dayFragment.setArguments(arguments);

        Log.d(TAG, "newInstance: " + arguments);
        return dayFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_day, container, false);

        mContext = getActivity();

        emptyDisplay = v.findViewById(R.id.emptyDisplay);
        tvEmptyView = v.findViewById(R.id.emptyTextView);
        recyclerView =v.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setLayoutManager(mLayoutManager);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<EventNode> eventNodeArrayList= getArguments().getParcelableArrayList(ARG_OBJECTS);

        if (eventNodeArrayList.size() != 0) {

            if (emptyDisplay.getVisibility() == View.VISIBLE)
                emptyDisplay.setVisibility(View.GONE);

            if (recyclerView.getVisibility() == View.GONE)
                recyclerView.setVisibility(View.VISIBLE);

            adapter = new EventNodeAdapter(mContext, eventNodeArrayList);
        }else{

            if (recyclerView.getVisibility() == View.VISIBLE)
                recyclerView.setVisibility(View.GONE);
            tvEmptyView.setText(getResources().getString(R.string.no_event_message));
            tvEmptyView.setVisibility(View.VISIBLE);
            emptyDisplay.setVisibility(View.VISIBLE);
        }


/*
        if (section.equalsIgnoreCase("notices")) {

            ArrayList<NoticeNode> noticeNodeArrayList= getArguments().getParcelableArrayList(ARG_OBJECTS);
            adapter = new NoticeNodeAdapter(mContext, noticeNodeArrayList);

        } else if (section.equalsIgnoreCase("events")) {
            ArrayList<NoticeNode> noticeNodeArrayList= (ArrayList<NoticeNode>) getArguments().getSerializable(ARG_OBJECTS);
            adapter = new NoticeNodeAdapter(mContext, noticeNodeArrayList);
        } else if (section.equalsIgnoreCase("holidays")) {
            ArrayList<NoticeNode> noticeNodeArrayList= (ArrayList<NoticeNode>) getArguments().getSerializable(ARG_OBJECTS);
            adapter = new NoticeNodeAdapter(mContext, noticeNodeArrayList);
        } else{


        }*/


        recyclerView.setAdapter(adapter);
    }
}
