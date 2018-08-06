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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.StudentListAdapter;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSectionRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetStudentListRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.StudentListNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.StudentListRespData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
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

public class StudentListActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private static final String TAG = StudentListActivity.class.getName();

    Context mContext;
    Toolbar toolbar;
    StudentParentResp studentParentResp;

    Spinner classListSpinner, sectionListSpinner;
    TextView tvClassListTitle, tvSectionListTitle, tvStudentListTitle;

    LinearLayout emptyDisplay;
    TextView emptyTextView;


    CardView studentListCardView;
    String classesId, sectionID;

    List<String> classList, sectionList;
    List<SectionListNode> sectionNodeList;
    List<TeacherClassNode> teacherClassNodeList;

    //CaldroidListener caldroidListener;

    TeacherClassNode selectedClassNode;
    SectionListNode selectedSectionNode;
    RecyclerView studentListRecyclerView;

    private ProgressDialog pDialog;
    private AdView mAdView;
    private AdRequest adRequest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        mContext = StudentListActivity.this;

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of Students");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emptyDisplay = findViewById(R.id.emptyDisplay);
        emptyTextView = findViewById(R.id.emptyTextView);
        tvStudentListTitle = findViewById(R.id.tvStudentListTitle);

        classListSpinner = findViewById(R.id.classListSpinner);
        tvClassListTitle = findViewById(R.id.tvSelectClassLabel);
        sectionListSpinner = findViewById(R.id.sectionListSpinner);
        tvSectionListTitle = findViewById(R.id.tvSelectSectionLabel);

        //mainCalendarCardView = findViewById(R.id.mainCalendarCardView);
        studentListCardView = findViewById(R.id.studentListCardView);

        studentListRecyclerView = findViewById(R.id.studentListRecyclerView);

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
                        classListSpinner.setOnItemSelectedListener(StudentListActivity.this);


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

                    sectionListSpinner.setAdapter(null);
                    Toast.makeText(mContext, "Select a Class from list", Toast.LENGTH_SHORT).show();


                } else {


                    if (tvSectionListTitle.getVisibility() == View.VISIBLE)
                        tvSectionListTitle.setVisibility(View.GONE);

                    if (sectionListSpinner.getVisibility() == View.VISIBLE)
                        sectionListSpinner.setVisibility(View.GONE);

                    /*if (mainCalendarCardView.getVisibility() == View.VISIBLE)
                        mainCalendarCardView.setVisibility(View.GONE);*/

                    if (studentListCardView.getVisibility() == View.VISIBLE)
                        studentListCardView.setVisibility(View.GONE);


                    selectedClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    classesId = selectedClassNode.getClassesID();

                    //Log.e(TAG, "Selected class Id:" + classesId);

                    //Display Progress Dialog
                    pDialog = new ProgressDialog(this);
                    pDialog.setMessage("loading sections...");
                    pDialog.setCanceledOnTouchOutside(false);
                    pDialog.show();

                    GetSectionRequest getSectionRequest = new GetSectionRequest();
                    getSectionRequest.setClassesID(classesId);
                    getSectionRequest.setSchoolId(studentParentResp.getSchoolId());

                    Log.e(TAG, "" + getSectionRequest.toString());

                    Call<SectionListResponseData> call = apiCall.section_list(getSectionRequest);
                    call.enqueue(new Callback<SectionListResponseData>() {
                        @Override
                        public void onResponse(Call<SectionListResponseData> call, Response<SectionListResponseData> response) {


                            Log.e(TAG, "GetSection" + response.code());

                            if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                                Log.e(TAG, " GetSection :" + response.body().getData());

                                sectionNodeList = response.body().getData().getSection();
                                if (sectionNodeList.size() != 0) {

                                    sectionList = new ArrayList<String>();
                                    sectionList.add("Select Section");

                                    for (SectionListNode sectionNode : sectionNodeList) {
                                        String sectionName = sectionNode.getSection();
                                        sectionList.add(sectionName);
                                        Log.e(TAG, "GetSection:" + sectionName);
                                    }

                                    //attach array of class names to the section list spinner
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                                            (mContext, android.R.layout.simple_spinner_item, sectionList);

                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


                                    sectionListSpinner.setAdapter(dataAdapter);
                                    sectionListSpinner.setSelection(0, false);
                                    sectionListSpinner.setOnItemSelectedListener(StudentListActivity.this);

                                    if (tvSectionListTitle.getVisibility() == View.GONE)
                                        tvSectionListTitle.setVisibility(View.VISIBLE);

                                    tvSectionListTitle.animate().alpha(1.0f).setDuration(500);

                                    if (sectionListSpinner.getVisibility() == View.GONE)
                                        sectionListSpinner.setVisibility(View.VISIBLE);

                                    sectionListSpinner.animate().alpha(1.0f).setDuration(750);

                                } else {
                                    AlertMessage.showMessage(mContext, "Pro-Pathshala Say..", "Class has no sections. Select any other class.", "OK", R.mipmap.ic_launcher);
                                }
                            }

                            //Dismiss Progress Dialog if its there
                            if (pDialog.isShowing())
                                pDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<SectionListResponseData> call, Throwable t) {

                            //Dismiss Progress Dialog if its there
                            if (pDialog.isShowing())
                                pDialog.dismiss();

                            Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                break;

            case R.id.sectionListSpinner:

                if (adapterView.getSelectedItemPosition() == 0) {

                    Toast.makeText(mContext, "Select a Section from list", Toast.LENGTH_SHORT).show();

                } else {

                    selectedSectionNode = sectionNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    sectionID = selectedSectionNode.getSectionID();
                    Log.e(TAG, "Selected SectionNode Id:" + sectionID);

                    loadStudentList();

                    /*CaldroidFragment caldroidFragment = new CaldroidFragment();
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
                    mainCalendarCardView.animate().alpha(1.0f).setDuration(1000);*/

                    //Toast.makeText(mContext, "Select Date to Load Student List", Toast.LENGTH_LONG).show();


                }

                break;
        }

    }

    private void loadStudentList() {

        Log.e(TAG, "loadStudentList: Called");

        //Build Request Node
        GetStudentListRequest getStudentListRequest = new GetStudentListRequest();
        getStudentListRequest.setClassesID(Long.parseLong(classesId));
        getStudentListRequest.setSectionID(Long.parseLong(sectionID));
        getStudentListRequest.setDefaultschoolyearID(Long.parseLong("1"));
        getStudentListRequest.setSchoolId(Long.parseLong(studentParentResp.getSchoolId()));

        Log.e(TAG, "GetStudentListRequest: " + getStudentListRequest.toString());

        //Display Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading student list...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        Call<StudentListRespData> getStudentList = apiCall.student_list(getStudentListRequest);
        getStudentList.enqueue(new Callback<StudentListRespData>() {
            @Override
            public void onResponse(Call<StudentListRespData> call, Response<StudentListRespData> response) {

                Log.e(TAG, "Resp Code:" + response.code());

                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    List<StudentListNode> studentListNodeList = response.body().getData().getStudents();

                    if (studentListNodeList.size() != 0) {

                        Log.e(TAG, "onResponse: Student List Size - " + studentListNodeList.size());
                        //studentListRecyclerView.setAdapter(null);

                        if(emptyDisplay.getVisibility() == View.VISIBLE)
                            emptyDisplay.setVisibility(View.GONE);

                        StudentListAdapter adapter = new StudentListAdapter(mContext, studentListNodeList);

                        LinearLayoutManager llm = new LinearLayoutManager(mContext);
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        studentListRecyclerView.setLayoutManager(llm);

                        studentListRecyclerView.setAdapter(adapter);

                        if (studentListCardView.getVisibility() == View.GONE)
                            studentListCardView.setVisibility(View.VISIBLE);

                        if (tvStudentListTitle.getVisibility() == View.GONE)
                            tvStudentListTitle.setVisibility(View.VISIBLE);

                        if (studentListRecyclerView.getVisibility() == View.GONE)
                            studentListRecyclerView.setVisibility(View.VISIBLE);

                        studentListCardView.animate().alpha(1.0f).setDuration(1000);

                    } else {
//                        AlertMessage.showMessage(mContext, "Pro-Pathshala Say..", "Section of this Class has no Students. Please Choose Other Section or Class.", "OK", R.mipmap.ic_launcher);


                        tvStudentListTitle.setVisibility(View.GONE);
                        studentListRecyclerView.setVisibility(View.GONE);
                        emptyTextView.setText(getResources().getString(R.string.empty_student_list_message));
                        emptyTextView.setVisibility(View.VISIBLE);
                        emptyDisplay.setVisibility(View.VISIBLE);

                        /*if(studentListCardView.getAlpha() == 0.0)*/
                            studentListCardView.animate().alpha(1.0f).setDuration(500);

                    }

                    if(pDialog.isShowing())
                        pDialog.dismiss();


                } else {



                    Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();

                    //Display Empty List View in place of Student Recycler View
                    tvStudentListTitle.setVisibility(View.GONE);
                    studentListRecyclerView.setVisibility(View.GONE);
                    emptyTextView.setText(getResources().getString(R.string.empty_student_list_message));
                    emptyTextView.setVisibility(View.VISIBLE);
                    emptyDisplay.setVisibility(View.VISIBLE);

//                    if(studentListCardView.getAlpha() == 0.0)
                        studentListCardView.animate().alpha(1.0f).setDuration(500);

                }

                if (pDialog.isShowing())
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<StudentListRespData> call, Throwable t) {

                if (pDialog.isShowing())
                    pDialog.dismiss();

                tvStudentListTitle.setVisibility(View.GONE);
                studentListRecyclerView.setVisibility(View.GONE);
                emptyTextView.setText(AppConstant.API_RESPONSE_FAILURE);
                emptyTextView.setVisibility(View.VISIBLE);
                emptyDisplay.setVisibility(View.VISIBLE);

//                if(studentListCardView.getAlpha() == 0.0)
                    studentListCardView.animate().alpha(1.0f).setDuration(500);

                //Toast.makeText(mContext, AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*private void loadCaldroidListener(CaldroidFragment caldroidFragment) {

        caldroidListener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                //load the student list for Current Date


            }
        };

        caldroidFragment.setCaldroidListener(caldroidListener);


    }*/

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
