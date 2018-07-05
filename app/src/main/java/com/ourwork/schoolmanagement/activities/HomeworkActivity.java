package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.HomeworkAdapter;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkNode;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkResponse;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class HomeworkActivity extends AppCompatActivity {

    private static final String TAG = HomeworkActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LoginResponse loginResponse;
    ProgressDialog pDialog;

    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        mContext = HomeworkActivity.this;

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("HomeworkNode");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialized Mobile Ads
        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("6AD94C36A4BB46F171C05D3AFD84DBDE").build();


        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");


        if (loginResponse.getUsertype().equalsIgnoreCase("student")) {

            /*
            * Call Student Syllabus API
            * */

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
            parentStudentRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
            parentStudentRequest.setUsername(loginResponse.getUsername());
            parentStudentRequest.setUsertypeID(loginResponse.getUsertypeID());
            parentStudentRequest.setSchool_id(loginResponse.getSchool_id());

            Log.d(TAG, "" + parentStudentRequest.toString());

            Call<HomeworkResponseData> call = apiCall.homework(parentStudentRequest);
            call.enqueue(new Callback<HomeworkResponseData>() {
                @Override
                public void onResponse(Call<HomeworkResponseData> call, Response<HomeworkResponseData> response) {

                    Log.e(TAG, "onResponse: " + response.code());
                    Log.e(TAG, "onResponse: " + response.body());

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        HomeworkResponse homeworkResponse = response.body().getData();
                        List<HomeworkNode> homeWorkNodeList = homeworkResponse.getHomeworks();

                        if (homeWorkNodeList.size() == 0) {

                            AlertMessage.showMessage(HomeworkActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Homework Record Found!");

                        } else {

                            recyclerView = findViewById(R.id.recyclerview);

                            int resId = R.anim.layout_animation_slide_from_right;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(HomeworkActivity.this, resId);
                            recyclerView.setLayoutAnimation(animation);


                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                            recyclerView.setLayoutManager(mLayoutManager);

                            HomeworkAdapter homeworkAdapter = new HomeworkAdapter(mContext, homeWorkNodeList);
                            recyclerView.setAdapter(homeworkAdapter);

                        }


                    }

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                }

                @Override
                public void onFailure(Call<HomeworkResponseData> call, Throwable t) {


                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                }
            });

        } else if (loginResponse.getUsertype().equalsIgnoreCase("teacher")) {

            /*
            * Call Teacher Syllabus API
            * */

            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        }

        //Display Ads with Results
        mAdView.loadAd(adRequest);


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
