package com.ourwork.schoolmanagement.fragments;

import android.content.Context;
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

/**
 * Created by Purvik Rana on 19-05-2018.
 */

public class MainFragment extends Fragment {

    View v;
    GridView mainGrid;
    String titles[] = {"Calender","Take Test","Facility","Achievments","Lunch Menu", "Forms"};
    int imgIds[] = { R.drawable.ic_calender, R.drawable.ic_test,R.drawable.ic_facility,R.drawable.ic_achievements,R.drawable.ic_lunchmenu, R.drawable.ic_forms};



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_grid_main, container, false);

        mainGrid = v.findViewById(R.id.grid_view);

        loadCustomGridAdapter();


        return v;
    }

    private void loadCustomGridAdapter() {

        CustomGrid adapter = new CustomGrid(getActivity().getApplicationContext(), titles, imgIds);
        mainGrid.setAdapter(adapter);
        mainGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "You Clicked at " +titles[+ position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
