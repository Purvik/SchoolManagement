package com.ourwork.schoolmanagement.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.StudentUserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Purvik Rana on 23-05-2018.
 */


public class UserProfileActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    TableLayout tableLayout;
    Serializable studetProfileSerializable;
    StudentUserProfile studentUserProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleGravity(GravityCompat.START);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("User Name");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        tableLayout = findViewById(R.id.tableLayout);

        studetProfileSerializable = getIntent().getExtras().getSerializable("studentProfile");

        studentUserProfile = (StudentUserProfile) studetProfileSerializable;
        Gson gson = new Gson();
        String json = gson.toJson(studentUserProfile);
        Log.d("User Details String", "onCreate: " + json);
        JSONObject selectedNode = null;
        TableRow row = new TableRow(UserProfileActivity.this);
        TableRow.LayoutParams lp =
                new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        try {
            selectedNode = new JSONObject(json);

            //parse the node and generate the TableView
            Iterator keys = selectedNode.keys();
            while (keys.hasNext()) {

                String key = keys.next().toString();
                //sb.append("\n" + key + ":\t\t" + selectedNode.getString(key));

                // Add Values to Table Layout
                TableRow row1 = new TableRow(UserProfileActivity.this);

                TableRow.LayoutParams lp1 =
                        new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

                lp1.setMargins(5, 1, 1, 1);
                row.setLayoutParams(lp1);

                TextView Tv1 = new TextView(UserProfileActivity.this);
                Tv1.setText(key);
                Tv1.setTextColor(Color.BLACK);

                row1.addView(Tv1);

                TextView Tv2 = new TextView(UserProfileActivity.this);
                Tv2.setText(selectedNode.getString(key));
                Tv2.setPadding(50, 0, 0, 0);
                row1.addView(Tv2);

                tableLayout.addView(row1);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }









    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
