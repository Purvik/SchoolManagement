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
import com.ourwork.schoolmanagement.adapters.TopicsAdapter;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;
import com.ourwork.schoolmanagement.singleton.SingleTopic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SubjectFragment extends Fragment {

    private static final String TAG = SubjectFragment.class.getName();
    private static  String ARG_SUBJECT = "subject";
    private static String ARG_SUB_LIST = "sub_list";
    private RecyclerView recyclerView;
    Context mContext;

    public static SubjectFragment newInstance(int subject, ArrayList<SingleTopic> singleSubjectDetails){

        SubjectFragment subjectFragment = new SubjectFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_SUBJECT,subject);
        arguments.putSerializable(ARG_SUB_LIST, singleSubjectDetails);
        subjectFragment.setArguments(arguments);

        Log.d(TAG, "newInstance: " + arguments);
        return subjectFragment;
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

        int subject = getArguments().getInt(ARG_SUBJECT);
        Serializable subjectDetails = getArguments().getSerializable(ARG_SUB_LIST);
        ArrayList<SingleTopic> arrayList = (ArrayList<SingleTopic>)subjectDetails;

        Log.d(TAG,subject + "onViewCreated: " + arrayList);

        /*if (subject == 0 || subjectDetails == null) {
            return;
        }*/

        RecyclerView.Adapter adapter = new TopicsAdapter(arrayList, subject, mContext);
        recyclerView.setAdapter(adapter);
    }



}
