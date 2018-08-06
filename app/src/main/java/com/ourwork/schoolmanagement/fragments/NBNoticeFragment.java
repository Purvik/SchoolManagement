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
import com.ourwork.schoolmanagement.adapters.NoticeNodeAdapter;
import com.ourwork.schoolmanagement.singleton.response.NoticeNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class NBNoticeFragment extends Fragment {


    private static final String TAG = NBNoticeFragment.class.getName();
    private static String ARG_SECTION = "section";
    private static String ARG_SECTION_NAME = "section_name";
    private static String ARG_OBJECTS = "objects";
    Context mContext;
    private RecyclerView recyclerView;
    private NoticeNodeAdapter adapter;

    public static NBNoticeFragment newInstance(String section, ArrayList<NoticeNode> sections) {

        NBNoticeFragment dayFragment = new NBNoticeFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_SECTION_NAME, section);
//        arguments.putString(ARG_SECTION_NAME, timetable );
        //ArrayList<NoticeNode> list= (ArrayList<NoticeNode>) sections.get(section);
        arguments.putParcelableArrayList(ARG_OBJECTS, (ArrayList<NoticeNode>) sections);
        dayFragment.setArguments(arguments);

        Log.d(TAG, "newInstance: " + arguments);
        return dayFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_day, container, false);

        mContext = getActivity();

        recyclerView = v.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<NoticeNode> noticeNodeArrayList = getArguments().getParcelableArrayList(ARG_OBJECTS);
        adapter = new NoticeNodeAdapter(mContext, noticeNodeArrayList);
        recyclerView.setAdapter(adapter);
    }
}
