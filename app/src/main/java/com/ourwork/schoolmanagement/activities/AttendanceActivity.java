package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AttendanceDataAdapter;
import com.ourwork.schoolmanagement.singleton.request.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceNode;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponse;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponseData;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 01-06-2018.
 */

public class AttendanceActivity extends AppCompatActivity {

    private static final String TAG = AttendanceActivity.class.getName();
    Calendar startDate, endDate;
    String classes[] = {"8-A","8-B","8-C","9-A","9-B","9-C","10-A","10-B","10-C"};
    String sections[] = {"Primary", "Secondary"};
    RecyclerView recyclerViewClass, recyclerViewSection;
    AttendanceDataAdapter adapter;
    Toolbar toolbar;
    LoginResponse loginResponse;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("AttendanceNode");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginResponse = (LoginResponse) getIntent().getExtras().getSerializable("loginResponse");

        /*//set up classes
        recyclerViewClass = findViewById(R.id.classListPanel);
        recyclerViewClass.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        adapter = new AttendanceDataAdapter(this, classes);
        recyclerViewClass.setAdapter(adapter);*/

        //set up sections
        /*recyclerViewSection = findViewById(R.id.sectionListPanel);
        recyclerViewSection.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        adapter = new AttendanceDataAdapter(this, sections);
        recyclerViewSection.setAdapter(adapter);*/

        //set up calender view

        startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH,-1);


        endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH,1);


        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.goToday(true);

        //horizontalCalendar.show();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something

            }
        });

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

            Call<AttendanceResponseData> call = apiCall.student_attendance(parentStudentRequest);
            call.enqueue(new Callback<AttendanceResponseData>() {
                @Override
                public void onResponse(Call<AttendanceResponseData> call, Response<AttendanceResponseData> response) {

                    if(pDialog.isShowing())
                        pDialog.dismiss();


                    Log.e(TAG, "Attendance Resp : " + response.code() );

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        AttendanceResponse attendanceResponse = response.body().getData();
                        List<AttendanceNode> attendanceNodeList = attendanceResponse.getAttendances();

                        /*
                        * Response is coming
                        * Calendar View Setup is Remaining Now Onwards
                        * */

                        CaldroidFragment caldroidFragment = new CaldroidFragment();
                        Bundle args = new Bundle();
                        Calendar cal = Calendar.getInstance();
                        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                        caldroidFragment.setArguments(args);

                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.fragment, caldroidFragment);
                        t.commit();


                    }


                }

                @Override
                public void onFailure(Call<AttendanceResponseData> call, Throwable t) {

                    if(pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                }
            });

        } else  {

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
