package com.ourwork.schoolmanagement.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.ProfileListAdapter;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Purvik Rana on 23-05-2018.
 */


public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {


    Serializable loginResponseSerializable;
    ImageView expandedImage, userTypeOverlapImageView;
    Button btnbackArrow;
    ArrayList<String> title_array = new ArrayList<String>();
    ArrayList<String> values_array = new ArrayList<String>();
    ListView listView;
    BaseAdapter profileListAdapter;
    TextView tvDisplayName;
    String jsonString;
    StudentParentResp studentParentResp;
    SharedPreferences mPrefs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //get view instances
        tvDisplayName = findViewById(R.id.displayName);
        expandedImage = findViewById(R.id.expandedImage);
        userTypeOverlapImageView = findViewById(R.id.userTypeOverlapImageView);

        btnbackArrow = findViewById(R.id.btnBackArrow);
        btnbackArrow.setOnClickListener(this);

        mPrefs = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);

        loginResponseSerializable = getIntent().getExtras().getSerializable("loginResponse");
        studentParentResp = (StudentParentResp) loginResponseSerializable;


        if (studentParentResp.getUsertype().equalsIgnoreCase("student") || studentParentResp.getUsertype().equalsIgnoreCase("parents")) {

            if (studentParentResp.getGender().equalsIgnoreCase("Male")) {

                expandedImage.setImageResource(R.drawable.ic_student_male);
                userTypeOverlapImageView.setImageResource(R.drawable.ic_student_male);

            } else {

                expandedImage.setImageResource(R.drawable.ic_student_female);
                userTypeOverlapImageView.setImageResource(R.drawable.ic_student_female);

            }


        } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher") || studentParentResp.getUsertype().equalsIgnoreCase("admin")) {

            if (studentParentResp.getGender().equalsIgnoreCase("Male")) {

                expandedImage.setImageResource(R.drawable.ic_teacher_male);
                userTypeOverlapImageView.setImageResource(R.drawable.ic_teacher_male);

            } else {

                expandedImage.setImageResource(R.drawable.ic_teacher_female);
                userTypeOverlapImageView.setImageResource(R.drawable.ic_teacher_female);

            }

        } else {


        }


        tvDisplayName.setText(studentParentResp.getName());

        Gson gson = new Gson();
        jsonString = gson.toJson(studentParentResp);
        Log.d("User Details String", "onCreate: " + jsonString);

        buildArrayList(jsonString);

        /*} else if (profileType.equalsIgnoreCase("teacher")) {

            //for teacher
            expandedImage.setImageResource(R.drawable.ic_teacher_male);
            userTypeOverlapImageView.setImageResource(R.drawable.ic_teacher_female);
            teacherProfileSerializable = getIntent().getExtras().getSerializable("teacherProfile");
            teacherUserProfile = (TeacherUserProfile) teacherProfileSerializable;


            tvDisplayName.setText(teacherUserProfile.getName());

            Gson gson = new Gson();
            jsonString = gson.toJson(teacherUserProfile);
            Log.d("User Details String", "onCreate: " + jsonString);

            buildArrayList(jsonString);


        }else{

            //for admin profile

        }*/

        listView = findViewById(R.id.listView);
        profileListAdapter = new ProfileListAdapter(UserProfileActivity.this, title_array, values_array);
        listView.setAdapter(profileListAdapter);

    }

    private void buildArrayList(String jsonString) {

        JSONObject selectedNode = null;

        try {
            selectedNode = new JSONObject(jsonString);

            //parse the node and generate the TableView
            Iterator keys = selectedNode.keys();
            while (keys.hasNext()) {

                String key = keys.next().toString();
                title_array.add(key);
                values_array.add(selectedNode.getString(key));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnBackArrow:

                onBackPressed();

                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
