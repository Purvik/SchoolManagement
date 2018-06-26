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
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.MarkListAdapter;
import com.ourwork.schoolmanagement.singleton.MarkNode;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Purvik Rana on 06-06-2018.
 */

public class MarksActivity extends AppCompatActivity {

    private static final String TAG = MarksActivity.class.getName();
    RecyclerView recyclerView;
    ArrayList<MarkNode> markNodeArrayList;
    Toolbar toolbar;
    MarkListAdapter markListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Results");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String json = loadJSONFromAsset();
        Type type = new TypeToken<ArrayList<MarkNode>>() {}.getType();
        markNodeArrayList = new Gson().fromJson(json, type);

        MarkNode markNode = markNodeArrayList.get(0);
        Log.d(TAG, "onCreate: " + markNode.getExamTitle());

        recyclerView =findViewById(R.id.recyclerview);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MarksActivity.this, resId);
        recyclerView.setLayoutAnimation(animation);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MarksActivity.this);

        recyclerView.setLayoutManager(mLayoutManager);


        if (markNodeArrayList != null && !markNodeArrayList.isEmpty()) {
            markListAdapter = new MarkListAdapter(MarksActivity.this, markNodeArrayList);
            recyclerView.setAdapter(markListAdapter);
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = (MarksActivity.this.getAssets().open("marks.json"));
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
