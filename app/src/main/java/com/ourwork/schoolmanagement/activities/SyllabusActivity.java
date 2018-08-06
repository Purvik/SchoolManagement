package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.teacher.AddSyllabusActivity;
import com.ourwork.schoolmanagement.adapters.SubjectPagerAdapter;
import com.ourwork.schoolmanagement.adapters.SyllabusAdapter;
import com.ourwork.schoolmanagement.adapters.TeacherSyllabusAdapter;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSyllabusRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusNode;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponse;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherSyllabusNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherSyllabusRespData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

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
    String userType;
    StudentParentResp studentParentResp;
    List<TeacherSyllabusNode> teacherSyllabusNodeList;
    List<SyllabusNode> syllabusNodeList;
    Serializable loginuserSerail;
    RecyclerView recyclerView;
    LinearLayout emptyDisplay;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView tvEmptyTextView;
    private ProgressDialog pDialog;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadSyllabusForStudent();

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        tvEmptyTextView = findViewById(R.id.emptyTextView);
        emptyDisplay = findViewById(R.id.emptyDisplay);

        /*viewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);*/

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Syllabus");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialized Mobile Ads
        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
//        mAdView.setVisibility(View.GONE);
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        if (getIntent() != null)
            loginuserSerail = getIntent().getExtras().getSerializable("loginResponse");

        Log.e(TAG, "Serial:"+ loginuserSerail);

        studentParentResp = (StudentParentResp) loginuserSerail;

        Log.e(TAG, "user:" +studentParentResp.toString());

        if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

            loadSyllabusForStudent();

        } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

            loadSyllabusForTeacher();

        } else {

            if(recyclerView.getVisibility() == View.VISIBLE)
                recyclerView.setVisibility(View.GONE);

            if (emptyDisplay.getVisibility() == View.GONE) {
                tvEmptyTextView.setText(AppConstant.APP_NOT_DEVELOPED_YET);
                tvEmptyTextView.setVisibility(View.VISIBLE);
                emptyDisplay.setVisibility(View.VISIBLE);
            }
        }

                mAdView.loadAd(adRequest);


        /*String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<SingleSubjectDetails>>() {}.getType();
        singleSubjectDetails = new Gson().fromJson(json, type);
        *//*try {
            singleSubjectDetails = new Gson().fromJson(IOUtils.toString(getAssets().open("syllabus.json"), "UTF-8"),
                    type);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*

        SingleSubjectDetails temp = (SingleSubjectDetails) singleSubjectDetails.get(1);
        Log.d(TAG, "onCreate: " + temp.getSubname());

        if (singleSubjectDetails != null && !singleSubjectDetails.isEmpty()) {

            subjectPagerAdapter = new SubjectPagerAdapter(getSupportFragmentManager(), singleSubjectDetails);
            viewPager.setAdapter(subjectPagerAdapter);
            tabs.setupWithViewPager(viewPager);

            viewPager.setCurrentItem(0);


            *//*int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (today <= 0 && today >= 5) {
                viewPager.setCurrentItem(today);
            } else {
               *//**//* viewPager.setCurrentItem(0);
            }*//*
        }*/
    }

    private void loadSyllabusForTeacher() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading syllabus...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        GetSyllabusRequest getSyllabusRequest = new GetSyllabusRequest();
        getSyllabusRequest.setDefaultschoolyearID(Long.parseLong("1"));
        getSyllabusRequest.setLoginuserID(Long.parseLong(studentParentResp.getLoginuserID()));
        getSyllabusRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));

        Call<TeacherSyllabusRespData> call = apiCall.t_get_syllabus(getSyllabusRequest);
        call.enqueue(new Callback<TeacherSyllabusRespData>() {
            @Override
            public void onResponse(Call<TeacherSyllabusRespData> call, Response<TeacherSyllabusRespData> response) {

                Log.e(TAG, "onResponse: "+ response.code());
                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    teacherSyllabusNodeList = response.body().getData().getSyllabuss();
                    Toast.makeText(getApplicationContext(),"Data Received", Toast.LENGTH_SHORT).show();

                    Log.e(TAG, "onResponse: " +teacherSyllabusNodeList.size());

                    if (teacherSyllabusNodeList.size() == 0) {

                        //AlertMessage.showMessage(SyllabusActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Syllabus Record Found!");

                        recyclerView.setVisibility(View.INVISIBLE);
                        tvEmptyTextView.setText(getResources().getString(R.string.no_syllabus_message));
                        emptyDisplay.setVisibility(View.VISIBLE);

                    } else {

                        if(emptyDisplay.getVisibility() == View.VISIBLE)
                            emptyDisplay.setVisibility(View.GONE);

                        if(recyclerView.getVisibility() == View.GONE)
                            recyclerView.setVisibility(View.VISIBLE);

                        int resId = R.anim.layout_animation_slide_from_right;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(SyllabusActivity.this, resId);
                        recyclerView.setLayoutAnimation(animation);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SyllabusActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);

                        RecyclerView.Adapter adapter = new TeacherSyllabusAdapter(teacherSyllabusNodeList, SyllabusActivity.this);
                        recyclerView.setAdapter(adapter);
                    }



                } else {

                    recyclerView.setVisibility(View.INVISIBLE);
                    tvEmptyTextView.setText(getResources().getString(R.string.no_syllabus_message));
                    emptyDisplay.setVisibility(View.VISIBLE);


                }

                if (pDialog.isShowing())
                    pDialog.dismiss();


            }

            @Override
            public void onFailure(Call<TeacherSyllabusRespData> call, Throwable t) {

                if (pDialog.isShowing())
                    pDialog.dismiss();

                recyclerView.setVisibility(View.GONE);
                tvEmptyTextView.setText(getString(R.string.error_server));
                tvEmptyTextView.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

            }
        });





    }

    private void loadSyllabusForStudent() {

        /*
         * Call Student Syllabus API
         * */

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading syllabus...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
        parentStudentRequest.setDefaultschoolyearID(studentParentResp.getDefaultschoolyearID());
        parentStudentRequest.setStudentID(studentParentResp.getStudentID());
        parentStudentRequest.setUsertypeID(studentParentResp.getUsertypeID());
        parentStudentRequest.setSchool_id(studentParentResp.getSchoolId());

        Log.d(TAG, "" + parentStudentRequest.toString());

        Call<SyllabusResponseData> call = apiCall.syllabus(parentStudentRequest);
        call.enqueue(new Callback<SyllabusResponseData>() {
            @Override
            public void onResponse(Call<SyllabusResponseData> call, Response<SyllabusResponseData> response) {


                Log.e("Resp", response.code() + " ");
                Log.d("Resp", "" + response.body().toString());
                if (pDialog.isShowing())
                    pDialog.dismiss();

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    SyllabusResponse syllabusResponse = response.body().getData();
                    syllabusNodeList = syllabusResponse.getSyllabusNodes();

                    if (syllabusNodeList.size() == 0) {

                        //AlertMessage.showMessage(SyllabusActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Syllabus Record Found!");

                        recyclerView.setVisibility(View.INVISIBLE);
                        tvEmptyTextView.setText(getResources().getString(R.string.no_syllabus_message));
                        emptyDisplay.setVisibility(View.VISIBLE);

                    } else {

                        if(emptyDisplay.getVisibility() == View.VISIBLE)
                            emptyDisplay.setVisibility(View.GONE);

                        if(recyclerView.getVisibility() == View.GONE)
                            recyclerView.setVisibility(View.VISIBLE);

                        int resId = R.anim.layout_animation_slide_from_right;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(SyllabusActivity.this, resId);
                        recyclerView.setLayoutAnimation(animation);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SyllabusActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);

                        RecyclerView.Adapter adapter = new SyllabusAdapter(syllabusNodeList, SyllabusActivity.this);
                        recyclerView.setAdapter(adapter);
                    }


                } else {

                    recyclerView.setVisibility(View.GONE);
                    tvEmptyTextView.setText(getString(R.string.empty_listview_message));
                    tvEmptyTextView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<SyllabusResponseData> call, Throwable t) {
                if (pDialog.isShowing())
                    pDialog.dismiss();


                recyclerView.setVisibility(View.GONE);
                tvEmptyTextView.setText(getString(R.string.error_server));
                tvEmptyTextView.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {
            getMenuInflater().inflate(R.menu.menu_actionbar_add_item, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_item_add:

                //Open add assignment activity for the teacher
                Intent addAssignmentIntent = new Intent(SyllabusActivity.this, AddSyllabusActivity.class);
                addAssignmentIntent.putExtra("loginResponse", studentParentResp);
                startActivity(addAssignmentIntent);


                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
