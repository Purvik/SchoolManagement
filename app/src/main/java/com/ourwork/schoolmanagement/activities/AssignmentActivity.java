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

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AssignmentAdapter;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentNode;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponseData;
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

public class AssignmentActivity extends AppCompatActivity {

    private static final String TAG = AssignmentActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<AssignmentNode> assignmentNodeArrayList;
    LoginResponse loginResponse;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignments");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (loginResponse.getUsertype().equalsIgnoreCase("student")) {


            /*
            * Student Assignment API calls
            * */

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading assignments...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            ParentStudentRequest parentStudentRequest = new ParentStudentRequest();
            parentStudentRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
            parentStudentRequest.setUsername(loginResponse.getUsername());
            parentStudentRequest.setUsertypeID(loginResponse.getUsertypeID());

            Log.d(TAG, "" + parentStudentRequest.toString());

            Call<AssignmentResponseData> call = apiCall.assignment(parentStudentRequest);
            call.enqueue(new Callback<AssignmentResponseData>() {
                @Override
                public void onResponse(Call<AssignmentResponseData> call, Response<AssignmentResponseData> response) {


                    Log.d(TAG, "Assignment Resp Code" + response.code());
                    Log.d(TAG, "Resp Body:" + response.body());

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {


                        AssignmentResponse assignmentResponse = response.body().getData();
                        List<AssignmentNode> assignmentNodes = assignmentResponse.getAssignments();

                        if (assignmentNodes.size() == 0) {

                            AlertMessage.showMessage(AssignmentActivity.this, R.mipmap.ic_launcher, "ProPathshala Says..","No Assignment Record Found!");

                        } else {

                            recyclerView = findViewById(R.id.recyclerview);

                            int resId = R.anim.layout_animation_slide_from_right;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(AssignmentActivity.this, resId);
                            recyclerView.setLayoutAnimation(animation);

                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AssignmentActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);

                            RecyclerView.Adapter adapter = new AssignmentAdapter(AssignmentActivity.this, assignmentNodes);
                            recyclerView.setAdapter(adapter);

                        }

                    }


                }

                @Override
                public void onFailure(Call<AssignmentResponseData> call, Throwable t) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                }
            });


        } else if (loginResponse.getUsertype().equalsIgnoreCase("teacher")) {

            /*
            * Teacher Assignment API calls
            * */

            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();


        }


    }

    /*public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = (AssignmentActivity.this.getAssets().open("homework.json"));
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
