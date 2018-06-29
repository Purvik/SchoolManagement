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

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.MarkListAdapter;
import com.ourwork.schoolmanagement.singleton.MarkNode;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
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

public class MarksActivity extends AppCompatActivity {

    private static final String TAG = MarksActivity.class.getName();
    RecyclerView recyclerView;
    ArrayList<MarkNode> markNodeArrayList;
    Toolbar toolbar;
    MarkListAdapter markListAdapter;
    LoginResponse loginResponse;
    ProgressDialog pDialog;

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

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        if (loginResponse.getUsertype().equalsIgnoreCase("student")) {

            //for Student

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading results ...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
            parentStudentRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
            parentStudentRequest.setUsername(loginResponse.getUsername());
            parentStudentRequest.setUsertypeID(loginResponse.getUsertypeID());

            Log.d(TAG, "" + parentStudentRequest.toString());

            Call<MarkResponseData> call = apiCall.mark(parentStudentRequest);
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

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

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
