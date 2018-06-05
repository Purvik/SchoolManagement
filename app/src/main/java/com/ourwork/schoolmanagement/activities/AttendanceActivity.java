package com.ourwork.schoolmanagement.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AttendanceDataAdapter;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * Created by Purvik Rana on 01-06-2018.
 */

public class AttendanceActivity extends AppCompatActivity {

    Calendar startDate, endDate;
    String classes[] = {"8-A","8-B","8-C","9-A","9-B","9-C","10-A","10-B","10-C"};
    String sections[] = {"Primary", "Secondary"};
    RecyclerView recyclerViewClass, recyclerViewSection;
    AttendanceDataAdapter adapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //set up classes
        recyclerViewClass = findViewById(R.id.classListPanel);
        recyclerViewClass.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        adapter = new AttendanceDataAdapter(this, classes);
        recyclerViewClass.setAdapter(adapter);

        //set up sections
        recyclerViewSection = findViewById(R.id.sectionListPanel);
        recyclerViewSection.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        adapter = new AttendanceDataAdapter(this, sections);
        recyclerViewSection.setAdapter(adapter);

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
