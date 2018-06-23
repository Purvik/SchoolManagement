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
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.ExamScheduleAdapter;
import com.ourwork.schoolmanagement.singleton.HomeWorkNode;
import com.ourwork.schoolmanagement.singleton.request.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleNode;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleResponse;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleResponseData;
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

public class ExamScheduleActivity extends AppCompatActivity {

    private static final String TAG = ExamScheduleActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LoginResponse loginResponse;
    ArrayList<HomeWorkNode> homeWorkNodeArrayList;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedule);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Exam Schedule");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        if (loginResponse.getUsertype().equalsIgnoreCase("student")) {

            /*
            * Student API call to get : Exam Schedule
            * */

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading exam schedule...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
            parentStudentRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
            parentStudentRequest.setUsername(loginResponse.getUsername());
            parentStudentRequest.setUsertypeID(loginResponse.getUsertypeID());

            Log.d(TAG, "" + parentStudentRequest.toString());

            Call<ExamScheduleResponseData> call = apiCall.exam_schedule(parentStudentRequest);
            call.enqueue(new Callback<ExamScheduleResponseData>() {
                @Override
                public void onResponse(Call<ExamScheduleResponseData> call, Response<ExamScheduleResponseData> response) {

                    Log.e(TAG, "Exam Resp Code:" + response.code());
                    Log.e(TAG, "Exam Resp Body: " + response.body());

                    if(pDialog.isShowing())
                        pDialog.dismiss();

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        ExamScheduleResponse examScheduleResponse = response.body().getData();
                        List<ExamScheduleNode> examScheduleNodeList = examScheduleResponse.getExamschedules();


                        recyclerView = findViewById(R.id.recyclerview);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                        recyclerView.setLayoutManager(mLayoutManager);

                        ExamScheduleAdapter examScheduleAdapter = new ExamScheduleAdapter(ExamScheduleActivity.this, examScheduleNodeList);
                        recyclerView.setAdapter(examScheduleAdapter);


                    }


                }

                @Override
                public void onFailure(Call<ExamScheduleResponseData> call, Throwable t) {

                    if(pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                }
            });


        } else {

            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

        }


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