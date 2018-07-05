package com.ourwork.schoolmanagement.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.NoticeAdapter;
import com.ourwork.schoolmanagement.singleton.NoticeNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Purvik Rana on 12-06-2018.
 */

public class NoticeActivity extends AppCompatActivity {

    private static final String TAG = HomeworkActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<NoticeNode> noticeNodeArrayList;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notices");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<NoticeNode>>() {}.getType();
        noticeNodeArrayList = new Gson().fromJson(json, type);

        NoticeNode noticeNode = noticeNodeArrayList.get(0);
        Log.d(TAG, "onCreate: " + noticeNode.toString());


        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);

        NoticeAdapter noticeAdapter = new NoticeAdapter(this, noticeNodeArrayList);
        recyclerView.setAdapter(noticeAdapter);

        //Display Banner Ad
        mAdView.loadAd(adRequest);
        if (mAdView.getVisibility() == View.GONE)
            mAdView.setVisibility(View.VISIBLE);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = (NoticeActivity.this.getAssets().open("notices.json"));
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
