package com.ourwork.schoolmanagement.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.SubjectPagerAdapter;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SyllabusActivity extends AppCompatActivity {

    private static final String TAG = SyllabusActivity.class.getName();
    Toolbar toolbar;
    ArrayList<SingleSubjectDetails> singleSubjectDetails;
    ViewPager viewPager;
    SubjectPagerAdapter subjectPagerAdapter;
    TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        viewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Syllabus");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<SingleSubjectDetails>>() {}.getType();
        singleSubjectDetails = new Gson().fromJson(json, type);
        /*try {
            singleSubjectDetails = new Gson().fromJson(IOUtils.toString(getAssets().open("syllabus.json"), "UTF-8"),
                    type);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        SingleSubjectDetails temp = (SingleSubjectDetails) singleSubjectDetails.get(1);
        Log.d(TAG, "onCreate: " + temp.getSubname());

        if (singleSubjectDetails != null && !singleSubjectDetails.isEmpty()) {

            subjectPagerAdapter = new SubjectPagerAdapter(getSupportFragmentManager(), singleSubjectDetails);
            viewPager.setAdapter(subjectPagerAdapter);
            tabs.setupWithViewPager(viewPager);

            viewPager.setCurrentItem(0);


            /*int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (today <= 0 && today >= 5) {
                viewPager.setCurrentItem(today);
            } else {
               *//* viewPager.setCurrentItem(0);
            }*/
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = (SyllabusActivity.this.getAssets().open("syllabus.json"));
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
