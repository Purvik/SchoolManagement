package com.ourwork.schoolmanagement.activities.student;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AttendanceDataAdapter;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceNode;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.roomorama.caldroid.CaldroidFragment;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 01-06-2018.
 */

public class StudentAttendanceActivity extends AppCompatActivity {

    private static final String TAG = StudentAttendanceActivity.class.getName();

    AttendanceDataAdapter adapter;
    Toolbar toolbar;
    StudentParentResp studentParentResp;
    CardView topCardViewPanel, calendarCardView;
    private ProgressDialog pDialog;

    private InterstitialAd mInterstitialAd;

    private AdView mAdView;
    private AdRequest adRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        topCardViewPanel = findViewById(R.id.topCardViewPanel);
        calendarCardView = findViewById(R.id.calendarCardView);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Initialized Mobile Ads (not required every time)
        //MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        //Build InterstitialAd Object
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.ads_test_device_id)).build());
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e(TAG, "Interstitial Ads Failed To Load");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "Interstitial Finished Loading ready to show.");
            }
        });

        //Load Ads
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");


        if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

            /*
            * Student AdminAssignmentListNode API calls
            * */


            //Display Progress Dialog
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading attendances...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            //Build Request Node
            ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
            parentStudentRequest.setDefaultschoolyearID(studentParentResp.getDefaultschoolyearID());
            parentStudentRequest.setStudentID(studentParentResp.getStudentID());
            parentStudentRequest.setUsertypeID(studentParentResp.getUsertypeID());
            parentStudentRequest.setSchool_id(studentParentResp.getSchoolId());


            Log.d(TAG, "" + parentStudentRequest.toString());

            //Call API
            Call<AttendanceResponseData> call = apiCall.student_attendance(parentStudentRequest);
            call.enqueue(new Callback<AttendanceResponseData>() {
                @Override
                public void onResponse(Call<AttendanceResponseData> call, Response<AttendanceResponseData> response) {


                    Log.e(TAG, "Attendance Resp : " + response.body().getData());

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        AttendanceResponse attendanceResponse = response.body().getData();
                        List<AttendanceNode> attendanceNodeList = attendanceResponse.getAttendances();

                        CaldroidFragment caldroidFragment = new CaldroidFragment();
                        Bundle args = new Bundle();
                        Calendar cal = Calendar.getInstance();
                        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                        //args.putBoolean(CaldroidStringContants.SQUARE_TEXT_VIEW_CELL, false);
                        caldroidFragment.setArguments(args);

                        //Plot Today
                        Date c = cal.getTime();
                        caldroidFragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_blue_ot_current_day, null), c);


                        if (attendanceNodeList.size() == 0) {

                            AlertMessage.showMessage(StudentAttendanceActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Attendance Record Found!");

                        } else {


                            //Plot Present & Absent
                            for (int i = 0; i < attendanceNodeList.size(); i++) {

                                AttendanceNode attendanceNode = attendanceNodeList.get(i);

                                String month = String.valueOf(attendanceNode.getMonthyear().toCharArray(), 0, 2);
                                int intMonth = Integer.parseInt(month);
                                String year = String.valueOf(attendanceNode.getMonthyear().toCharArray(), 3, 4);
                                int intYear = Integer.parseInt(year);

                                //boolean isLeapYear = checkIsLeapYear(year);
                                int noOfMonthDays;

                                if (intYear % 4 == 0) {
                                    noOfMonthDays = AppConstant.leapYearMonthDays[intMonth - 1];
                                } else {
                                    noOfMonthDays = AppConstant.normalYearMonthDays[intMonth - 1];
                                }

                                //Log.e(TAG, i + " : " + "- Year :" + year + " Month - " + month +" No Of Days:" + noOfMonthDays );


                                for (int j = 1; j <= noOfMonthDays; j++) {

                                    try {

                                        //Get Date Parameter and It's Value
                                        Field field = attendanceNode.getClass().getField("a" + j);
                                        String value = String.valueOf(field.get(attendanceNode));

                                        //Create Date Object
                                        String nodeDateStirng = j + "/" + intMonth + "/" + intYear;
                                        Date nodeDate = new SimpleDateFormat("dd/MM/yyyy").parse(nodeDateStirng);

                                        if (value.equalsIgnoreCase("P")) {  //for present

                                            caldroidFragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_green_dot_present, null), nodeDate);


                                        } else if (value.equalsIgnoreCase("A")) {   //for absent

                                            caldroidFragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_red_dot_absent, null), nodeDate);

                                        } else {  //for null


                                        }

                                    } catch (NoSuchFieldException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                }

                            }
                        }

                        topCardViewPanel.animate().alpha(1.0f).setDuration(1000);

                        //refresh CalendarView
                        caldroidFragment.refreshView();
                        calendarCardView.animate().alpha(1.0f).setDuration(2000);


                        //Replace Fragment with Calendar
                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.fragment, caldroidFragment);
                        t.commit();
                    }


                    //Dismiss Progress Dialog if its there
                    if (pDialog.isShowing())
                        pDialog.dismiss();

                }

                @Override
                public void onFailure(Call<AttendanceResponseData> call, Throwable t) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Log.e(TAG, "onFailure: " + t.getMessage());

                    Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                }
            });

        } else {

            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        }

        mAdView.loadAd(adRequest);


    }

    /*private boolean checkIsLeapYear(String year) {

        if (Integer.parseInt(year) % 4 == 0) {
            return true;
        }else{
            return  false;
        }


    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

        super.onBackPressed();
    }
}
