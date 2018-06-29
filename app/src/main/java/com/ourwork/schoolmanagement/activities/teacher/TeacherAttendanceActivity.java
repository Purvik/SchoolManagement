package com.ourwork.schoolmanagement.activities.teacher;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.teacher.ParentTeacherRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class TeacherAttendanceActivity extends AppCompatActivity {

    private static final String TAG = TeacherAttendanceActivity.class.getName();

    Toolbar toolbar;
    LoginResponse loginResponse;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        ParentTeacherRequest parentTeacherRequest= new ParentTeacherRequest();
        parentTeacherRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
        parentTeacherRequest.setUsername(loginResponse.getUsername());
        parentTeacherRequest.setUsertypeID(loginResponse.getUsertypeID());

        Log.e(TAG, "" + parentTeacherRequest.toString());

        //Display Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading attendances...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        Call<TeacherClassNodeResponseData> call = apiCall.get_class_list(parentTeacherRequest);
        call.enqueue(new Callback<TeacherClassNodeResponseData>() {
            @Override
            public void onResponse(Call<TeacherClassNodeResponseData> call, Response<TeacherClassNodeResponseData> response) {

                Log.e(TAG, ""+ response.code() );
                Log.e(TAG, ""+ response.body().toString() );

                //Dismiss Progress Dialog if its there
                if (pDialog.isShowing())
                    pDialog.dismiss();


            }

            @Override
            public void onFailure(Call<TeacherClassNodeResponseData> call, Throwable t) {

                //Dismiss Progress Dialog if its there
                if (pDialog.isShowing())
                    pDialog.dismiss();

                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

            }
        });







    }
}
