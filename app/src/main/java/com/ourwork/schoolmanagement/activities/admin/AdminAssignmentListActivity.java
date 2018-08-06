package com.ourwork.schoolmanagement.activities.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AdminAssignmentListAdapter;
import com.ourwork.schoolmanagement.singleton.request.admin.AdminAssignmentListRequest;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.admin.AdminAssignmentListNode;
import com.ourwork.schoolmanagement.singleton.response.admin.AdminAssignmentRespData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class AdminAssignmentListActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private static final String TAG = AdminAssignmentListActivity.class.getName();

    Context mContext;
    Toolbar toolbar;
    StudentParentResp studentParentResp;

    Spinner classListSpinner;
    TextView tvClassListTitle, tvAssignmentListTitle;

    LinearLayout emptyDisplay;
    TextView emptyTextView;


    CardView assignmentListCardView;
    String classesId;

    List<String> classList;

    //List of classes from API call
    List<TeacherClassNode> teacherClassNodeList;

    List<AdminAssignmentListNode> assignmentListNodes;

    TeacherClassNode selectedClassNode;

    RecyclerView assignmentListRecyclerView;

    private ProgressDialog pDialog;
    private AdView mAdView;
    private AdRequest adRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assignment_list);

        mContext = AdminAssignmentListActivity.this;

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of Assignments");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emptyDisplay = findViewById(R.id.emptyDisplay);
        emptyTextView = findViewById(R.id.emptyTextView);
        tvAssignmentListTitle = findViewById(R.id.tvAssignmentsListTitle);

        classListSpinner = findViewById(R.id.classListSpinner);
        tvClassListTitle = findViewById(R.id.tvSelectClassLabel);

        //mainCalendarCardView = findViewById(R.id.mainCalendarCardView);
        assignmentListCardView = findViewById(R.id.assignmentListCardView);

        assignmentListRecyclerView = findViewById(R.id.assignmentListRecyclerView);

        //Initialized Mobile Ads
        // MobileAds.initialize(this, getResources().getString(R.string.adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        loadClassList(studentParentResp);

        //Toast.makeText(getApplicationContext(), AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

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
                        classListSpinner.setOnItemSelectedListener(AdminAssignmentListActivity.this);


                        if (tvClassListTitle.getVisibility() == View.GONE)
                            tvClassListTitle.setVisibility(View.VISIBLE);

                        tvClassListTitle.animate().alpha(1.0f).setDuration(500);

                        if (classListSpinner.getVisibility() == View.GONE)
                            classListSpinner.setVisibility(View.VISIBLE);

                        classListSpinner.animate().alpha(1.0f).setDuration(750);


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

        mAdView.loadAd(adRequest);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.d(TAG, "onItemSelected: " + adapterView.getId());
        switch (adapterView.getId()) {

            case R.id.classListSpinner:

                if (adapterView.getSelectedItemPosition() == 0) {
                    Toast.makeText(mContext, "Select a Class from list", Toast.LENGTH_SHORT).show();
                } else {

                    if (assignmentListCardView.getVisibility() == View.VISIBLE)
                        assignmentListCardView.setVisibility(View.GONE);

                    selectedClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    classesId = selectedClassNode.getClassesID();

                    //Log.e(TAG, "Selected class Id:" + classesId);

                    //Display Progress Dialog
                    pDialog = new ProgressDialog(this);
                    pDialog.setMessage("loading assignments...");
                    pDialog.setCanceledOnTouchOutside(false);
                    pDialog.show();

                    AdminAssignmentListRequest adminAssignmentListRequest = new AdminAssignmentListRequest();
                    adminAssignmentListRequest.setClassesID(Long.parseLong(classesId));
                    adminAssignmentListRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));
                    adminAssignmentListRequest.setDefaultschoolyearID(Long.parseLong("1"));
                    adminAssignmentListRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));

                    Log.e(TAG, "" + adminAssignmentListRequest.toString());

                    Call<AdminAssignmentRespData> call = apiCall.admin_assignment_list(adminAssignmentListRequest);
                    call.enqueue(new Callback<AdminAssignmentRespData>() {
                        @Override
                        public void onResponse(Call<AdminAssignmentRespData> call, Response<AdminAssignmentRespData> response) {


                            Log.e(TAG, "GetAssignmentList" + response.code());

                            if (response.code() == AppConstant.RESPONSE_CODE_OK) {


                                assignmentListNodes = response.body().getData().getAssignments();

                                Log.e(TAG, "onResponse: Size:" + assignmentListNodes.size());

                                if (assignmentListNodes.size() != 0) {

                                    if (emptyDisplay.getVisibility() == View.VISIBLE)
                                        emptyDisplay.setVisibility(View.GONE);

                                    if (assignmentListCardView.getVisibility() == View.GONE)
                                        assignmentListCardView.setVisibility(View.VISIBLE);

                                    if (tvAssignmentListTitle.getVisibility() == View.GONE)
                                        tvAssignmentListTitle.setVisibility(View.VISIBLE);

                                    if (assignmentListRecyclerView.getVisibility() == View.GONE)
                                        assignmentListRecyclerView.setVisibility(View.VISIBLE);

                                    int resId = R.anim.layout_animation_slide_from_right;
                                    LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(AdminAssignmentListActivity.this, resId);
                                    assignmentListRecyclerView.setLayoutAnimation(animation);

                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AdminAssignmentListActivity.this);
                                    assignmentListRecyclerView.setLayoutManager(mLayoutManager);

                                    RecyclerView.Adapter adapter = new AdminAssignmentListAdapter(AdminAssignmentListActivity.this, assignmentListNodes);
                                    assignmentListRecyclerView.setAdapter(adapter);


                                    // Log.e(TAG, "onResponse: Adapter Attached");
                                   /* for (AdminAssignmentListNode node: assignmentListNodes) {
                                        Log.e(TAG, "onResponse: " + node.toString());
                                    }*/

                                } else {

                                    assignmentListRecyclerView.setAdapter(null);
                                    assignmentListRecyclerView.setVisibility(View.GONE);
                                    tvAssignmentListTitle.setVisibility(View.GONE);
                                    emptyTextView.setText(getResources().getString(R.string.no_assignment_message));

                                    emptyDisplay.setVisibility(View.VISIBLE);
                                    assignmentListCardView.setVisibility(View.VISIBLE);


                                    //AlertMessage.showMessage(mContext, "Pro-Pathshala Say..", "Class has no Assignments. Select any other class.", "OK", R.mipmap.ic_launcher);

                                }
                                assignmentListCardView.animate().alpha(1.0f).setDuration(750);
                            }

                            //Dismiss Progress Dialog if its there
                            if (pDialog.isShowing())
                                pDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<AdminAssignmentRespData> call, Throwable t) {

                            //Dismiss Progress Dialog if its there
                            if (pDialog.isShowing())
                                pDialog.dismiss();

                            assignmentListRecyclerView.setVisibility(View.GONE);
                            tvAssignmentListTitle.setVisibility(View.GONE);
                            emptyTextView.setText(AppConstant.API_RESPONSE_FAILURE);

                            emptyDisplay.setVisibility(View.VISIBLE);
                            assignmentListCardView.setVisibility(View.VISIBLE);

                        }
                    });
                }


                break;

        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
