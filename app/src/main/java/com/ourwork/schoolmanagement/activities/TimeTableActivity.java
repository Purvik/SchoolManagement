package com.ourwork.schoolmanagement.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import org.apache.commons.io.IOUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.DaysPagerAdapter;
import com.ourwork.schoolmanagement.singleton.SingleClass;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class TimeTableActivity extends AppCompatActivity {

    private static final String TAG = TimeTableActivity.class.getName();
    Toolbar toolbar;
    ArrayList<SingleClass> timetable;
    ViewPager viewPager;
    DaysPagerAdapter daysPagerAdapter;
    TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        viewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Time Table");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<SingleClass>>() {
        }.getType();
        //timetable = new Gson().fromJson(json, type);
        try {
            timetable = new Gson().fromJson(IOUtils.toString(getAssets().open("timetable.json"), "UTF-8"),
                    type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onCreate: " + timetable.get(1).toString());

        if (timetable != null && !timetable.isEmpty()) {

            daysPagerAdapter = new DaysPagerAdapter(getSupportFragmentManager(), timetable);
            viewPager.setAdapter(daysPagerAdapter);
            tabs.setupWithViewPager(viewPager);


            int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (today <= 0 && today >= 5) {
                viewPager.setCurrentItem(today);
            } else {
                viewPager.setCurrentItem(0);
            }
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = (TimeTableActivity.this.getAssets().open("timetable.json"));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
