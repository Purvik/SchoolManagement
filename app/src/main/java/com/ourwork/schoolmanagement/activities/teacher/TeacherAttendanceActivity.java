package com.ourwork.schoolmanagement.activities.teacher;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSectionRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.ParentTeacherRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionNodeResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponse;
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

public class TeacherAttendanceActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private static final String TAG = TeacherAttendanceActivity.class.getName();

    Toolbar toolbar;
    LoginResponse loginResponse;
    private ProgressDialog pDialog;
    ArrayList<String> classNameList, sectionNameList;
    Spinner classListSpinner, sectionListSpinner;
    Context mContext;
    List<TeacherClassNode> teacherClassNodeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);

        mContext = TeacherAttendanceActivity.this;

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        classListSpinner = findViewById(R.id.classListSpinner);
        classListSpinner.setOnItemSelectedListener(this);
        sectionListSpinner = findViewById(R.id.sectionListSpinner);
        sectionListSpinner.setOnItemSelectedListener(this);

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

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    TeacherClassNodeResponseData teacherClassNodeResponseData = response.body();
                    TeacherClassNodeResponse teacherClassNodeResponse = teacherClassNodeResponseData.getData();

                    teacherClassNodeList = teacherClassNodeResponse.getClasses();


                    classNameList = new ArrayList();
                    for (TeacherClassNode teacherClassNode: teacherClassNodeList) {

                        String className = teacherClassNode.getClasses();
                        classNameList.add(className);

                    }

                    ArrayAdapter dataAdapter=new ArrayAdapter(TeacherAttendanceActivity.this,android.R.layout.simple_spinner_dropdown_item,classNameList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    classListSpinner.setAdapter(dataAdapter);

                    classListSpinner.animate().alpha(1.0f).setDuration(750);


                }




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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        TeacherClassNode tempNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition());

//        Toast.makeText(mContext, "Class ID"+ tempNode.getClassesID(),Toast.LENGTH_SHORT).show();


        GetSectionRequest getSectionRequest = new GetSectionRequest();
        getSectionRequest.setClassesID(tempNode.getClassesID());

        //Display Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading attendances...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        Call<SectionNodeResponseData> call = apiCall.get_section_list(getSectionRequest);
        call.enqueue(new Callback<SectionNodeResponseData>() {
            @Override
            public void onResponse(Call<SectionNodeResponseData> call, Response<SectionNodeResponseData> response) {


                Log.e(TAG, " Section Code: " + response.code() );

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    Log.e(TAG, " SectionList :"+ response.body() );

                }

                //Dismiss Progress Dialog if its there
                if (pDialog.isShowing())
                    pDialog.dismiss();



            }

            @Override
            public void onFailure(Call<SectionNodeResponseData> call, Throwable t) {

                Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();

                //Dismiss Progress Dialog if its there
                if (pDialog.isShowing())
                    pDialog.dismiss();

            }
        });




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
