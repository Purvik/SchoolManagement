package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.NoticeBoardPagerAdapter;
import com.ourwork.schoolmanagement.singleton.request.NoticeBoardRequest;
import com.ourwork.schoolmanagement.singleton.response.EventNode;
import com.ourwork.schoolmanagement.singleton.response.HolidayNode;
import com.ourwork.schoolmanagement.singleton.response.NoticeBoardRespData;
import com.ourwork.schoolmanagement.singleton.response.NoticeNode;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 12-06-2018.
 */

public class NoticeBoardActivity extends AppCompatActivity {

    private static final String TAG = NoticeBoardActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ProgressDialog pDialog;
    StudentParentResp studentParentResp;
    NoticeBoardPagerAdapter noticeBoardPagerAdapter;
    LinearLayout emptyDisplay;
    TextView tvEmptyView;
    SwipeRefreshLayout swipeRefreshLayout;

    ViewPager viewPager;
    TabLayout tabs;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);

        mContext = NoticeBoardActivity.this;

        if (getIntent() != null)
            studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        viewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notice Board");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();

        mAdView.loadAd(adRequest);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

               loadNoticeBoard();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        loadNoticeBoard();

        //Display Banner Ad
        if (mAdView.getVisibility() == View.GONE)
            mAdView.setVisibility(View.VISIBLE);
    }

    public void loadNoticeBoard() {

        //Get the list of the NoticeNode, Events, Holidays
        final NoticeBoardRequest noticeBoardRequest = new NoticeBoardRequest();
        noticeBoardRequest.setDefaultschoolyearID(Long.parseLong("1"));
        noticeBoardRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading noticeboard items...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        Call<NoticeBoardRespData> call = apiCall.get_noticeboard(noticeBoardRequest);
        call.enqueue(new Callback<NoticeBoardRespData>() {
            @Override
            public void onResponse(Call<NoticeBoardRespData> call, Response<NoticeBoardRespData> response) {

                Log.e(TAG, "onResponse: NB" + response.code());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    Log.e(TAG, "onResponse: Data" + response.body().getData());

                    ArrayList<NoticeNode> noticeNodeList = (ArrayList<NoticeNode>) response.body().getData().getNotices();
                    ArrayList<EventNode> eventNodeList = (ArrayList<EventNode>) response.body().getData().getEvents();
                    ArrayList<HolidayNode> holidayNodeList = (ArrayList<HolidayNode>) response.body().getData().getHolidays();

                    HashMap<String, Object> map = new HashMap<>();

                    map.put("Notices", noticeNodeList);
                    map.put("Event", eventNodeList);
                    map.put("Holiday", holidayNodeList);


                    Log.e(TAG, "Event Items:" + eventNodeList.size());
                    Log.e(TAG, "Holiday Items:" + holidayNodeList.size());
                    Log.e(TAG, "Notice Items:" + noticeNodeList.size());


                    noticeBoardPagerAdapter = new NoticeBoardPagerAdapter(getSupportFragmentManager(), map);
                    viewPager.setAdapter(noticeBoardPagerAdapter);
                    tabs.setupWithViewPager(viewPager);

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                } else {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<NoticeBoardRespData> call, Throwable t) {

            }
        });

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
