package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.MarkListAdapter;
import com.ourwork.schoolmanagement.singleton.MarkNode;
import com.ourwork.schoolmanagement.singleton.request.student.MarkStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.MarkResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 06-06-2018.
 */

public class MarksActivity extends AppCompatActivity implements RewardedVideoAdListener{

    private static final String TAG = MarksActivity.class.getName();
    RecyclerView recyclerView;
    ArrayList<MarkNode> markNodeArrayList;
    Toolbar toolbar;
    MarkListAdapter markListAdapter;
    LoginResponse loginResponse;
    ProgressDialog pDialog;

    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Results");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize Mobile Ads SDK
        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        // Get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        if (loginResponse.getUsertype().equalsIgnoreCase("student")) {

            //for Student

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading results ...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            MarkStudentRequest markStudentRequest = new MarkStudentRequest();
            markStudentRequest.setSchoolyearID(loginResponse.getDefaultschoolyearID());
            markStudentRequest.setUsername(loginResponse.getUsername());
            markStudentRequest.setUsertypeID(loginResponse.getUsertypeID());

            Log.d(TAG, "" + markStudentRequest.toString());

            Call<MarkResponseData> call = apiCall.mark(markStudentRequest);
            call.enqueue(new Callback<MarkResponseData>() {
                @Override
                public void onResponse(Call<MarkResponseData> call, Response<MarkResponseData> response) {

                    Log.e(TAG, "Result Resp Code:" + response.code());
                    Log.e(TAG, "Result Resp Body: " + response.body());

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        AlertMessage.showMessage(MarksActivity.this,"ProPathshala","YES");

                    }



                }

                @Override
                public void onFailure(Call<MarkResponseData> call, Throwable t) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Log.e(TAG, "Result Fail Response:" + t.getMessage());

                    Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                }
            });



        } else if (loginResponse.getUsertype().equalsIgnoreCase("teacher")) {

            //for Teacher
            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        }else{

            //for Admin
            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        }



        recyclerView =findViewById(R.id.recyclerview);

        int resId = R.anim.layout_animation_slide_from_right;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MarksActivity.this, resId);
        recyclerView.setLayoutAnimation(animation);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MarksActivity.this);

        recyclerView.setLayoutManager(mLayoutManager);


        if (markNodeArrayList != null && !markNodeArrayList.isEmpty()) {
            markListAdapter = new MarkListAdapter(MarksActivity.this, markNodeArrayList);
            recyclerView.setAdapter(markListAdapter);
        }



    }

    /*
    * Load the Rewarded Video Ad
    * */
    private void loadRewardedVideoAd() {

        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().addTestDevice(getResources().getString(R.string.ads_test_device_id)).build());
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

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Loaded");
        if (mRewardedVideoAd.isLoaded())
            mRewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Opened");

    }

    @Override
    public void onRewardedVideoStarted() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Started");

    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Closed");

    }

    @Override
    public void onRewarded(RewardItem reward) {

        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Left Application");

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Failed To Load" + errorCode);

    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.e(TAG, "onRewardedVideoAdLoaded: Rewarded Video Completed");

    }
}
