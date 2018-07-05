package com.ourwork.schoolmanagement.activities.teacher;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSectionRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetStudentListRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.ParentTeacherRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionNodeResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponse;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class TeacherAttendanceActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private static final String TAG = TeacherAttendanceActivity.class.getName();

    Toolbar toolbar;
    LoginResponse loginResponse;
    ArrayList<String> classNameList, sectionNameList;
    Spinner classListSpinner, sectionListSpinner;
    Context mContext;
    List<TeacherClassNode> teacherClassNodeList;
    CardView mainCalendarCardView;
    String classesId, sectionID;
    List<SectionNode> sectionNodeList;
    private ProgressDialog pDialog;
    CaldroidListener caldroidListener;
    TeacherClassNode selectedClassNode;
    SectionNode selectedSectionNode;

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
        sectionListSpinner = findViewById(R.id.sectionListSpinner);

        mainCalendarCardView = findViewById(R.id.mainCalendarCardView);

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        loadClassList(loginResponse);

    }

    private void loadClassList(LoginResponse loginResponse) {

        ParentTeacherRequest parentTeacherRequest = new ParentTeacherRequest();
        parentTeacherRequest.setDefaultschoolyearID(loginResponse.getDefaultschoolyearID());
        parentTeacherRequest.setUsername(loginResponse.getUsername());
        parentTeacherRequest.setUsertypeID(loginResponse.getUsertypeID());

        Log.e(TAG, "" + parentTeacherRequest.toString());

        //Display Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching Class List ...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        /*
        * Call to Get Class List based on the Login Details
        * */
        Call<TeacherClassNodeResponseData> call = apiCall.get_class_list(parentTeacherRequest);
        call.enqueue(new Callback<TeacherClassNodeResponseData>() {
            @Override
            public void onResponse(Call<TeacherClassNodeResponseData> call, Response<TeacherClassNodeResponseData> response) {

                Log.e(TAG, "" + response.code());
                Log.e(TAG, "" + response.body().toString());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    TeacherClassNodeResponseData teacherClassNodeResponseData = response.body();
                    TeacherClassNodeResponse teacherClassNodeResponse = teacherClassNodeResponseData.getData();

                    teacherClassNodeList = teacherClassNodeResponse.getClasses();

                    classNameList = new ArrayList();

                    if (teacherClassNodeList.size() == 0) {

                        AlertMessage.showMessage(mContext, "Pro-Pathshala Says...", "No classes are assigned to you");

                    } else {
                        for (TeacherClassNode teacherClassNode : teacherClassNodeList) {

                            String className = teacherClassNode.getClasses();
                            classNameList.add(className);

                        }

                        //attach array of class names to the class list spinner
                        ArrayAdapter dataAdapter = new ArrayAdapter(TeacherAttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, classNameList);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                        classListSpinner.setAdapter(dataAdapter);
                        classListSpinner.setSelection(0, false);
                        classListSpinner.setOnItemSelectedListener(TeacherAttendanceActivity.this);

                        //hide section list spinner if class selection changes
                        if (sectionListSpinner.getVisibility() == View.VISIBLE)
                            classListSpinner.animate().alpha(0.0f).setDuration(750);

                        classListSpinner.setVisibility(View.VISIBLE);
                        //display class list spinner with Animation
                        classListSpinner.animate().alpha(1.0f).setDuration(750);
                    }
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

        Log.d(TAG, "onItemSelected: " + adapterView.getId());
        switch (adapterView.getId()) {

            case R.id.classListSpinner:

                if (sectionListSpinner.getVisibility() == View.VISIBLE)
                    sectionListSpinner.setVisibility(View.GONE);

                if (mainCalendarCardView.getVisibility() == View.VISIBLE)
                    mainCalendarCardView.setVisibility(View.GONE);


                selectedClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition());

                classesId = selectedClassNode.getClassesID();

                Log.e(TAG, "Selected class Id:" + classesId);

                TeacherClassNode tempNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition());

                GetSectionRequest getSectionRequest = new GetSectionRequest();
                getSectionRequest.setClassesID(classesId);

                //Display Progress Dialog
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Fetching HomeworkSectionNode List for Selected Class ...");
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();

                Call<SectionNodeResponseData> call = apiCall.get_section_list(getSectionRequest);
                call.enqueue(new Callback<SectionNodeResponseData>() {
                    @Override
                    public void onResponse(Call<SectionNodeResponseData> call, Response<SectionNodeResponseData> response) {


                        Log.e(TAG, " HomeworkSectionNode Code: " + response.code());

                        if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                            //Log.e(TAG, " SectionList :" + response.body());

                            sectionNodeList = response.body().getData();
                            if (sectionNodeList.size() != 0) {

                                sectionNameList = new ArrayList();
                                for (SectionNode sectionNode : sectionNodeList) {
                                    String sectionName = sectionNode.getSection();
                                    sectionNameList.add(sectionName);
                                    Log.e(TAG, "HomeworkSectionNode Item Name:" + sectionName);
                                }

                                //attach array of class names to the section list spinner
                                ArrayAdapter dataAdapter = new ArrayAdapter(TeacherAttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, sectionNameList);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                                sectionListSpinner.setAdapter(dataAdapter);
                                sectionListSpinner.setSelection(0, false);
                                sectionListSpinner.setOnItemSelectedListener(TeacherAttendanceActivity.this);

                                sectionListSpinner.setVisibility(View.VISIBLE);
                                //display section list spinner
                                sectionListSpinner.animate().alpha(1.0f).setDuration(750);

                            } else {
                                AlertMessage.showMessage(mContext, "Pro-Pathshala Say..", "Class has no sections.", "OK", R.mipmap.ic_launcher);
                            }
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


                break;

            case R.id.sectionListSpinner:


                selectedSectionNode = sectionNodeList.get(adapterView.getSelectedItemPosition());

                sectionID = selectedSectionNode.getSectionID();
                Log.e(TAG, "Selected HomeworkSectionNode Id:" + sectionID);

                CaldroidFragment caldroidFragment = new CaldroidFragment();
                Bundle args = new Bundle();
                Calendar cal = Calendar.getInstance();
                args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                caldroidFragment.setArguments(args);

                //Plot Today
                Date c = cal.getTime();
                caldroidFragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_blue_ot_current_day, null), c);

                //Replace Fragment with Calendar
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.fragment, caldroidFragment);
                t.commit();

                loadCaldroidListener(caldroidFragment);

                mainCalendarCardView.setVisibility(View.VISIBLE);
                mainCalendarCardView.animate().alpha(1.0f).setDuration(1000);

                Toast.makeText(mContext, "Select Date to Load Student List", Toast.LENGTH_LONG).show();

                break;
        }

    }

    private void loadCaldroidListener(CaldroidFragment caldroidFragment) {

        caldroidListener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                //load the student list for Current Date

                //Build Request Node
                GetStudentListRequest getStudentListRequest = new GetStudentListRequest();
                getStudentListRequest.setLoginuserID(loginResponse.getLoginuserID());
                getStudentListRequest.setUsertype(loginResponse.getUsertype());
                getStudentListRequest.setUsername(loginResponse.getUsername());
                getStudentListRequest.setClassesID(selectedClassNode.getClassesID());
                getStudentListRequest.setSectionID(selectedSectionNode.getSectionID());
                getStudentListRequest.setDate(date.toString());
                getStudentListRequest.setSchoolyearID(selectedSectionNode.getSchoolId());

                Log.d(TAG, "GetStudentListRequest: " + getStudentListRequest.toString());



            }
        };

        caldroidFragment.setCaldroidListener(caldroidListener);


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



}
