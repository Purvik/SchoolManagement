package com.ourwork.schoolmanagement.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.AssignmentAdapter;
import com.ourwork.schoolmanagement.adapters.HomeworkAdapter;
import com.ourwork.schoolmanagement.singleton.AssignmentNode;
import com.ourwork.schoolmanagement.singleton.HomeWorkNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class AssignmentActivity extends AppCompatActivity {

    private static final String TAG = AssignmentActivity.class.getName();
    Context mContext;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<AssignmentNode> assignmentNodeArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Assignments");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<AssignmentNode>>() {}.getType();
        assignmentNodeArrayList = new Gson().fromJson(json, type);
        
        AssignmentNode assignmentNode= assignmentNodeArrayList.get(0);
        Log.d(TAG, "onCreate: " + assignmentNode.toString());


        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);

        AssignmentAdapter assignmentAdapter= new AssignmentAdapter(this, assignmentNodeArrayList);
        recyclerView.setAdapter(assignmentAdapter);

    }

    public String loadJSONFromAsset() {
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
