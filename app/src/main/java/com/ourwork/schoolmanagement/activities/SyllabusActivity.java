package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.SubjectPagerAdapter;
import com.ourwork.schoolmanagement.adapters.SyllabusAdapter;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;
import com.ourwork.schoolmanagement.singleton.request.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusNode;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponse;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;

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
    LoginResponse loginResponse;
    RecyclerView recyclerView;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);


        /*viewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);*/

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Syllabus");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        List<SyllabusNode> syllabusNodeList = syllabusResponse.getSyllabusNodes();

                        if (syllabusNodeList.size() == 0) {

                            AlertMessage.showMessage(SyllabusActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Syllabus Record Found!");

                        } else {

                            recyclerView = findViewById(R.id.recycler);

                            int resId = R.anim.layout_animation_fall_down;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(SyllabusActivity.this, resId);
                            recyclerView.setLayoutAnimation(animation);


                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SyllabusActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);

                            RecyclerView.Adapter adapter = new SyllabusAdapter(syllabusNodeList, SyllabusActivity.this);
                            recyclerView.setAdapter(adapter);


                        }


                    }
                }

                @Override
                public void onFailure(Call<SyllabusResponseData> call, Throwable t) {
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

        }




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


    /*public String loadJSONFromAsset() {
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
    }*/

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
