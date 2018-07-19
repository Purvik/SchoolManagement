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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSectionRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetStudentListRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
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
    StudentParentResp studentParentResp;
    ArrayList<String> classList, sectionList;
    Spinner classListSpinner, sectionListSpinner;
    TextView tvClassListTitle, tvSectionListTitle;
    Context mContext;
    List<TeacherClassNode> teacherClassNodeList;
    CardView mainCalendarCardView;
    String classesId, sectionID;
    List<SectionListNode> sectionNodeList;
    private ProgressDialog pDialog;
    CaldroidListener caldroidListener;
    TeacherClassNode selectedClassNode;
    SectionListNode selectedSectionNode;

    private AdView mAdView;
    private AdRequest adRequest;


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
        tvClassListTitle = findViewById(R.id.tvSelectClassLabel);
        sectionListSpinner = findViewById(R.id.sectionListSpinner);
        tvSectionListTitle = findViewById(R.id.tvSelectSectionLabel);

        mainCalendarCardView = findViewById(R.id.mainCalendarCardView);

        //Initialized Mobile Ads
       // MobileAds.initialize(this, getResources().getString(R.string.adMob_app_id));

        //Load Ads
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();

        studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        loadClassList(studentParentResp);

        Toast.makeText(getApplicationContext(), AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

    }

    private void loadClassList(StudentParentResp studentParentResp) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading class list...");
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

                    Log.e(TAG, response.code() + " | " + teacherClassNodeList.size() );

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
                        classListSpinner.setOnItemSelectedListener(TeacherAttendanceActivity.this);

                        if(classListSpinner.getVisibility() == View.GONE)
                            classListSpinner.setVisibility(View.VISIBLE);

                        tvClassListTitle.animate().alpha(1.0f).setDuration(500);
                        classListSpinner.animate().alpha(1.0f).setDuration(750);



                    } else {
                        Toast.makeText(getApplicationContext(), "" + AppConstant.APP_RESPONSE_NO_DATA, Toast.LENGTH_LONG).show();
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

                    sectionListSpinner.setAdapter(null);

                    Toast.makeText(mContext, "Select a Class from list", Toast.LENGTH_SHORT).show();


                }else{

                    if (sectionListSpinner.getVisibility() == View.VISIBLE)
                        sectionListSpinner.setVisibility(View.GONE);

                    if (mainCalendarCardView.getVisibility() == View.VISIBLE)
                        mainCalendarCardView.setVisibility(View.GONE);


                    selectedClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    classesId = selectedClassNode.getClassesID();

                    //Log.e(TAG, "Selected class Id:" + classesId);

                    GetSectionRequest getSectionRequest = new GetSectionRequest();
                    getSectionRequest.setClassesID(classesId);
                    getSectionRequest.setSchoolId(studentParentResp.getSchoolId());

                    Log.e(TAG, ""+ getSectionRequest.toString() );


                    //Display Progress Dialog
                    pDialog = new ProgressDialog(this);
                    pDialog.setMessage("Fetching HomeworkSectionNode List for Selected Class ...");
                    pDialog.setCanceledOnTouchOutside(false);
                    pDialog.show();

                    Call<SectionListResponseData> call = apiCall.section_list(getSectionRequest);
                    call.enqueue(new Callback<SectionListResponseData>() {
                        @Override
                        public void onResponse(Call<SectionListResponseData> call, Response<SectionListResponseData> response) {


                            Log.e(TAG, "GetSection" + response.code());

                            if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                                Log.e(TAG, " GetSection :" + response.body().getData());

                                sectionNodeList = response.body().getData().getSection();
                                if (sectionNodeList.size() != 0) {

                                    sectionList = new ArrayList();
                                    sectionList.add("Select Section");
                                    for (SectionListNode sectionNode : sectionNodeList) {
                                        String sectionName = sectionNode.getSection();
                                        sectionList.add(sectionName);
                                        Log.e(TAG, "GetSection:" + sectionName);
                                    }

                                    //attach array of class names to the section list spinner
                                    ArrayAdapter dataAdapter = new ArrayAdapter(TeacherAttendanceActivity.this, android.R.layout.simple_spinner_dropdown_item, sectionList);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                                    sectionListSpinner.setAdapter(dataAdapter);
                                    sectionListSpinner.setSelection(0, false);
                                    sectionListSpinner.setOnItemSelectedListener(TeacherAttendanceActivity.this);

                                    if(sectionListSpinner.getVisibility() == View.GONE)
                                        sectionListSpinner.setVisibility(View.VISIBLE);

                                    //display section list spinner
                                    tvSectionListTitle.animate().alpha(1.0f).setDuration(500);
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
                        public void onFailure(Call<SectionListResponseData> call, Throwable t) {

                            Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();

                            //Dismiss Progress Dialog if its there
                            if (pDialog.isShowing())
                                pDialog.dismiss();

                        }
                    });


                }




                break;

            case R.id.sectionListSpinner:

                if (adapterView.getSelectedItemPosition() == 0) {

                    sectionListSpinner.setAdapter(null);

                    Toast.makeText(mContext, "Select a Section from list", Toast.LENGTH_SHORT).show();


                }else{

                    selectedSectionNode = sectionNodeList.get(adapterView.getSelectedItemPosition() - 1);

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


                }

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
                getStudentListRequest.setLoginuserID(studentParentResp.getLoginuserID());
                getStudentListRequest.setUsertype(studentParentResp.getUsertype());
                getStudentListRequest.setUsername(studentParentResp.getUsername());
                getStudentListRequest.setClassesID(selectedClassNode.getClassesID());
                getStudentListRequest.setSectionID(selectedSectionNode.getSectionID());
                getStudentListRequest.setDate(date.toString());
                //getStudentListRequest.setSchoolyearID(selectedSectionNode.getSchoolId());

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
