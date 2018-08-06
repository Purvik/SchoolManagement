package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.teacher.AddAssignmentHomeworkActivity;
import com.ourwork.schoolmanagement.adapters.AssignmentAdapter;
import com.ourwork.schoolmanagement.adapters.TeacherAssignmentAdapter;
import com.ourwork.schoolmanagement.singleton.request.student.StudentRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetAssignmentRequst;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.admin.AdminAssignmentListNode;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherAssignmentNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherAssignmentRespData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class AssignmentActivity extends AppCompatActivity {

    private static final String TAG = AssignmentActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<TeacherAssignmentNode> teacherAssignmentNodeList;
    List<AdminAssignmentListNode> assignmentNodes;
    LinearLayout emptyDisplay;
    TextView tvEmptyView;
    SwipeRefreshLayout swipeRefreshLayout;
    StudentParentResp studentParentResp;
    private ProgressDialog pDialog;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        mContext = AssignmentActivity.this;

        if(getIntent() != null)
            studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

                    loadAssignmentForStudent();


                } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

                    /*if(recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.APP_NOT_DEVELOPED_YET);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }*/

                    loadAssignmentForTeacher();


                } else {

                    if(recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.APP_NOT_DEVELOPED_YET);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }
                }


                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        tvEmptyView = findViewById(R.id.emptyTextView);
        emptyDisplay = findViewById(R.id.emptyDisplay);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignments");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Initialized Mobile Ads
        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice("6AD94C36A4BB46F171C05D3AFD84DBDE").build();


        if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

            loadAssignmentForStudent();

        } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

            loadAssignmentForTeacher();


        } else {

            if(recyclerView.getVisibility() == View.VISIBLE)
                recyclerView.setVisibility(View.GONE);

            if (emptyDisplay.getVisibility() == View.GONE) {
                tvEmptyView.setText(AppConstant.APP_NOT_DEVELOPED_YET);
                tvEmptyView.setVisibility(View.VISIBLE);
                emptyDisplay.setVisibility(View.VISIBLE);
            }


        }

        //Display Ads with Results
        mAdView.loadAd(adRequest);


    }

    /*
     * Load AdminAssignmentListNode Method for Student
     * */
    private void loadAssignmentForStudent() {


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading assignments...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setDefaultschoolyearID(Long.parseLong("1"));
        studentRequest.setLoginuserID(Long.parseLong(studentParentResp.getLoginuserID()));
        studentRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
        studentRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));


        Call<AssignmentResponseData> call = apiCall.assignment(studentRequest);
        call.enqueue(new Callback<AssignmentResponseData>() {
            @Override
            public void onResponse(Call<AssignmentResponseData> call, Response<AssignmentResponseData> response) {


//                Log.d(TAG, "AdminAssignmentListNode Resp Code" + response.code());
//                Log.d(TAG, "Resp Body:" + response.body());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    AssignmentResponse assignmentResponse = response.body().getData();
                    assignmentNodes = assignmentResponse.getAssignments();

                    if (assignmentNodes.size() == 0) {

                        //AlertMessage.showMessage(AssignmentActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..","No AdminAssignmentListNode Record Found!");

                        //Display API Failure Message
                        if (recyclerView.getVisibility() == View.VISIBLE)
                            recyclerView.setVisibility(View.GONE);

                        if (emptyDisplay.getVisibility() == View.GONE) {
                            tvEmptyView.setText(AppConstant.API_RESPONSE_NO_DATA);
                            tvEmptyView.setVisibility(View.VISIBLE);
                            emptyDisplay.setVisibility(View.VISIBLE);
                        }


                    } else {

                        if(emptyDisplay.getVisibility() == View.VISIBLE)
                            emptyDisplay.setVisibility(View.GONE);

                        if(recyclerView.getVisibility() == View.GONE)
                            recyclerView.setVisibility(View.VISIBLE);

                        int resId = R.anim.layout_animation_slide_from_right;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(AssignmentActivity.this, resId);
                        recyclerView.setLayoutAnimation(animation);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AssignmentActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);

                        RecyclerView.Adapter adapter = new AssignmentAdapter(AssignmentActivity.this, assignmentNodes);
                        recyclerView.setAdapter(adapter);

                    }
                    if (pDialog.isShowing())
                        pDialog.dismiss();

                } else{

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    //Display API Failure Message
                    if (recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }

//                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<AssignmentResponseData> call, Throwable t) {

                if (pDialog.isShowing())
                    pDialog.dismiss();

                //Display API Failure Message
                if (recyclerView.getVisibility() == View.VISIBLE)
                    recyclerView.setVisibility(View.GONE);

                if (emptyDisplay.getVisibility() == View.GONE) {
                    tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                    tvEmptyView.setVisibility(View.VISIBLE);
                    emptyDisplay.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    /*
    * Load AdminAssignmentListNode Method for Teacher
    * */
    private void loadAssignmentForTeacher() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading assignments...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        GetAssignmentRequst getAssignmentRequst = new GetAssignmentRequst();
        getAssignmentRequst.setDefaultschoolyearID(Long.parseLong("1"));
        getAssignmentRequst.setLoginuserID(Long.parseLong(studentParentResp.getLoginuserID()));
        getAssignmentRequst.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
        getAssignmentRequst.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));

        Call<TeacherAssignmentRespData> call = apiCall.t_get_assignment(getAssignmentRequst);
        call.enqueue(new Callback<TeacherAssignmentRespData>() {
            @Override
            public void onResponse(Call<TeacherAssignmentRespData> call, Response<TeacherAssignmentRespData> response) {

                Log.e(TAG, "onResponse: " + response.code());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {


                    teacherAssignmentNodeList = response.body().getData().getAssignments();

                    if (teacherAssignmentNodeList.size() != 0) {

                        if(emptyDisplay.getVisibility() == View.VISIBLE)
                            emptyDisplay.setVisibility(View.GONE);

                        if(recyclerView.getVisibility() == View.GONE)
                            recyclerView.setVisibility(View.VISIBLE);

                        int resId = R.anim.layout_animation_slide_from_right;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(AssignmentActivity.this, resId);
                        recyclerView.setLayoutAnimation(animation);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AssignmentActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);

                        RecyclerView.Adapter adapter = new TeacherAssignmentAdapter(AssignmentActivity.this, teacherAssignmentNodeList);
                        recyclerView.setAdapter(adapter);


                    }else{

                        //Display API Failure Message
                        if (recyclerView.getVisibility() == View.VISIBLE)
                            recyclerView.setVisibility(View.GONE);

                        if (emptyDisplay.getVisibility() == View.GONE) {
                            tvEmptyView.setText(AppConstant.API_RESPONSE_NO_DATA);
                            tvEmptyView.setVisibility(View.VISIBLE);
                            emptyDisplay.setVisibility(View.VISIBLE);
                        }

                    }

                }else{


                    //Display API Failure Message
                    if (recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }
                }
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TeacherAssignmentRespData> call, Throwable t) {

                if(pDialog.isShowing())
                    pDialog.dismiss();

                //Display API Failure Message
                if (recyclerView.getVisibility() == View.VISIBLE)
                    recyclerView.setVisibility(View.GONE);

                if (emptyDisplay.getVisibility() == View.GONE) {
                    tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                    tvEmptyView.setVisibility(View.VISIBLE);
                    emptyDisplay.setVisibility(View.VISIBLE);
                }
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
                Intent addAssignmentIntent = new Intent(AssignmentActivity.this, AddAssignmentHomeworkActivity.class);
                addAssignmentIntent.putExtra("loginResponse", studentParentResp);
                addAssignmentIntent.putExtra("addItemType", "assignment");
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
