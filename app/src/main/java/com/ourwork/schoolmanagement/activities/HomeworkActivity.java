package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.teacher.AddAssignmentHomeworkActivity;
import com.ourwork.schoolmanagement.adapters.AdminHomeworkAdapter;
import com.ourwork.schoolmanagement.adapters.HomeworkAdapter;
import com.ourwork.schoolmanagement.adapters.TeacherHomeworkAdapter;
import com.ourwork.schoolmanagement.singleton.request.admin.AdminHomeworkRequest;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.request.student.StudentRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetHomeworkRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.admin.AdminHomeworkNode;
import com.ourwork.schoolmanagement.singleton.response.admin.AdminHomeworkRespData;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkNode;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkResponse;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherHomeworkNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherHomeworkRespData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class HomeworkActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private static final String TAG = HomeworkActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayout emptyDisplay;
    TextView tvEmptyView;
    SwipeRefreshLayout swipeRefreshLayout;
    StudentParentResp studentParentResp;
    ProgressDialog pDialog;

    CardView classListCardView;
    Spinner classListSpinner;
    TextView tvClassListTitle;

    List<TeacherClassNode> teacherClassNodeList;
    TeacherClassNode selectedTeacherClassNode;

    List<TeacherHomeworkNode> teacherHomeworkNodeList;
    List<AdminHomeworkNode> adminHomeworkNodeList;
    List<HomeworkNode> homeWorkNodeList;

    List<String> classList;


    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        mContext = HomeworkActivity.this;

        studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        recyclerView = findViewById(R.id.recyclerview);
        emptyDisplay = findViewById(R.id.emptyDisplay);
        tvEmptyView = findViewById(R.id.emptyTextView);

        classListCardView = findViewById(R.id.classListCardView);
        classListSpinner = findViewById(R.id.classListSpinner);
        tvClassListTitle = findViewById(R.id.tvSelectClassLabel);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Homework");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialized Mobile Ads
        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("6AD94C36A4BB46F171C05D3AFD84DBDE").build();

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

                    loadHomeworkForStudent();

                } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

                    loadClassList(studentParentResp);

                } else {

                    loadClassList(studentParentResp);
                }


                swipeRefreshLayout.setRefreshing(false);
            }
        });


        if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

            loadHomeworkForStudent();


        } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

            /*if (recyclerView.getVisibility() == View.VISIBLE)
                recyclerView.setVisibility(View.GONE);

            if (emptyDisplay.getVisibility() == View.GONE) {
                tvEmptyView.setText(AppConstant.APP_NOT_DEVELOPED_YET);
                tvEmptyView.setVisibility(View.VISIBLE);
                emptyDisplay.setVisibility(View.VISIBLE);
                }*/

            loadClassList(studentParentResp);


        } else {

            loadClassList(studentParentResp);

        }

        //Display Ads with Results
        mAdView.loadAd(adRequest);


    }

    private void loadClassList(StudentParentResp studentParentResp) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading list of classes...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        TeacherListRequest teacherListRequest = new TeacherListRequest();
        teacherListRequest.setSchoolId(studentParentResp.getSchoolId());

        /*
         * call to get class list
         * */
        Call<TeacherClassNodeResponseData> call = apiCall.class_list(teacherListRequest);
        call.enqueue(new Callback<TeacherClassNodeResponseData>() {
            @Override
            public void onResponse(Call<TeacherClassNodeResponseData> call, Response<TeacherClassNodeResponseData> response) {

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    teacherClassNodeList = response.body().getData().getClasses();

                    Log.e(TAG, response.code() + " | " + teacherClassNodeList.size());

                    if (teacherClassNodeList.size() != 0) {

                        classList = new ArrayList<String>();
                        classList.add("Select Class");

                        for (TeacherClassNode teacherClassNode : teacherClassNodeList) {
                            String className = teacherClassNode.getClasses();
                            classList.add(className);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                mContext, android.R.layout.simple_spinner_item, classList);

                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

                        classListSpinner.setAdapter(adapter);
                        classListSpinner.setSelection(0, false);
                        classListSpinner.setOnItemSelectedListener(HomeworkActivity.this);


                        if (classListCardView.getVisibility() == View.GONE)
                            classListCardView.setVisibility(View.VISIBLE);

                        classListCardView.animate().alpha(1.0f).setDuration(500);

                        /*if (classListSpinner.getVisibility() == View.GONE)
                            classListSpinner.setVisibility(View.VISIBLE);

                        classListSpinner.animate().alpha(1.0f).setDuration(750);*/


                    } else {

                        Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_NO_DATA, Toast.LENGTH_LONG).show();
                    }

                    if (pDialog.isShowing())
                        pDialog.dismiss();


                } else {
                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TeacherClassNodeResponseData> call, Throwable t) {

                if (pDialog.isShowing())
                    pDialog.dismiss();

                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();
            }
        });


    }


    private void loadHomeworkForStudent() {

        /*
         * Call Student Syllabus API
         * */

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading homework...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        /*ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
        parentStudentRequest.setDefaultschoolyearID(studentParentResp.getDefaultschoolyearID());
        parentStudentRequest.setStudentID(studentParentResp.getStudentID());
        parentStudentRequest.setUsertypeID(studentParentResp.getUsertypeID());
        parentStudentRequest.setSchool_id(studentParentResp.getSchoolId());*/

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setDefaultschoolyearID(Long.parseLong("1"));
        studentRequest.setLoginuserID(Long.parseLong(studentParentResp.getLoginuserID()));
        studentRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
        studentRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));

        Log.d(TAG, "" + studentRequest.toString());

        Call<HomeworkResponseData> call = apiCall.homework(studentRequest);
        call.enqueue(new Callback<HomeworkResponseData>() {
            @Override
            public void onResponse(Call<HomeworkResponseData> call, Response<HomeworkResponseData> response) {

                Log.e(TAG, "onResponse: " + response.code());
                Log.e(TAG, "onResponse: " + response.body());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    HomeworkResponse homeworkResponse = response.body().getData();
                    homeWorkNodeList = homeworkResponse.getHomeworks();

                    if (homeWorkNodeList.size() == 0) {

                        AlertMessage.showMessage(HomeworkActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..", "No Homework Record Found!");

                    } else {


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
    }

    private void loadHomeworkForTeacher(TeacherClassNode teacherClassNode) {


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading homework...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        GetHomeworkRequest getHomeworkRequest = new GetHomeworkRequest();
        getHomeworkRequest.setClassesID(Long.parseLong(teacherClassNode.getClassesID()));
        getHomeworkRequest.setDefaultschoolyearID(Long.parseLong("1"));
        getHomeworkRequest.setLoginuserID(Long.parseLong(studentParentResp.getLoginuserID()));
        getHomeworkRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
        getHomeworkRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));


        Call<TeacherHomeworkRespData> call = apiCall.t_get_homework(getHomeworkRequest);
        call.enqueue(new Callback<TeacherHomeworkRespData>() {
            @Override
            public void onResponse(Call<TeacherHomeworkRespData> call, Response<TeacherHomeworkRespData> response) {

                Log.e(TAG, "tHomework: " + response.code());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {


                    teacherHomeworkNodeList = response.body().getData().getHomeworks();

                    int resId = R.anim.layout_animation_slide_from_right;
                    LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(HomeworkActivity.this, resId);
                    recyclerView.setLayoutAnimation(animation);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomeworkActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);

                    TeacherHomeworkAdapter adapter = new TeacherHomeworkAdapter(mContext, teacherHomeworkNodeList);
                    recyclerView.setAdapter(adapter);

                } else {

                    //Display API Failure Message
                    if (recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }

                }
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TeacherHomeworkRespData> call, Throwable t) {

                Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();

                if (pDialog.isShowing())
                    pDialog.dismiss();

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
                Intent addAssignmentIntent = new Intent(HomeworkActivity.this, AddAssignmentHomeworkActivity.class);
                addAssignmentIntent.putExtra("loginResponse", studentParentResp);
                addAssignmentIntent.putExtra("addItemType", "homework");
                startActivity(addAssignmentIntent);


                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            case R.id.classListSpinner:


                if (adapterView.getSelectedItemPosition() == 0) {

                    Toast.makeText(mContext, "Select a Class from list", Toast.LENGTH_SHORT).show();
                } else {

                    selectedTeacherClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {
                        loadHomeworkForTeacher(selectedTeacherClassNode);
                    }else{
                        loadHomeworkForAdmin(selectedTeacherClassNode);
                    }

                }

                break;
        }

    }

    private void loadHomeworkForAdmin(TeacherClassNode selectedTeacherClassNode) {


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading homework...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();


        final AdminHomeworkRequest adminHomeworkRequest = new AdminHomeworkRequest();
        adminHomeworkRequest.setClassesID(Long.parseLong(selectedTeacherClassNode.getClassesID()));
        adminHomeworkRequest.setDefaultschoolyearID(Long.parseLong("1"));
        adminHomeworkRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
        adminHomeworkRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));

        Call<AdminHomeworkRespData> call = apiCall.a_get_homework(adminHomeworkRequest);
        call.enqueue(new Callback<AdminHomeworkRespData>() {
            @Override
            public void onResponse(Call<AdminHomeworkRespData> call, Response<AdminHomeworkRespData> response) {


                if (response.code() == AppConstant.RESPONSE_CODE_OK) {


                    adminHomeworkNodeList = response.body().getData().getHomeworks();

                    Log.e(TAG, "Admin HW List Size:" + adminHomeworkNodeList.size());
                    if (adminHomeworkNodeList.size() != 0) {

                        int resId = R.anim.layout_animation_slide_from_right;
                        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(HomeworkActivity.this, resId);
                        recyclerView.setLayoutAnimation(animation);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomeworkActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);

                        AdminHomeworkAdapter adapter = new AdminHomeworkAdapter(mContext, adminHomeworkNodeList);
                        recyclerView.setAdapter(adapter);

                        if (recyclerView.getVisibility() == View.GONE)
                            recyclerView.setVisibility(View.VISIBLE);

                        if (emptyDisplay.getVisibility() == View.VISIBLE) {
                            tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                            tvEmptyView.setVisibility(View.GONE);
                            emptyDisplay.setVisibility(View.GONE);
                        }


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

                } else {

                    //Display API Failure Message
                    if (recyclerView.getVisibility() == View.VISIBLE)
                        recyclerView.setVisibility(View.GONE);

                    if (emptyDisplay.getVisibility() == View.GONE) {
                        tvEmptyView.setText(AppConstant.API_RESPONSE_FAILURE);
                        tvEmptyView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);
                    }

                }

                if (pDialog.isShowing())
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<AdminHomeworkRespData> call, Throwable t) {

                Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();

                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });





    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
