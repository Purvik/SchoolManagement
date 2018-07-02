package com.ourwork.schoolmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.teacher.TeacherAttendanceActivity;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

/**
 * Created by Purvik Rana on 15-05-2018.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity.java";
    private ImageView imgAppIcon;
    private TextView tvTitle, tvSubTitle;
    //private Animation iconAnimation, textAnimation;
    //Context mContext;

    SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPrefs = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);

        findViewById();
        //mContext = getApplicationContext();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                LoginResponse loginResponse = AppSharedPreferences.getAppPreferences(mPrefs);
                //Log.d(TAG, "run: " + accountUser.toString());

                if (loginResponse != null) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("loggedInUser", loginResponse);
                    startActivity(intent);

                }else{

                    //Actual Call
                    startActivity(new Intent(SplashActivity.this, LogInActivity.class));

                    //For Testing Only
                    /*Intent intent = new Intent(SplashActivity.this, TeacherAttendanceActivity.class);
                    LoginResponse loginResponse1 = new LoginResponse();
                    loginResponse1.setUsertypeID("3");
                    loginResponse1.setUsername("pTeacher");
                    loginResponse1.setDefaultschoolyearID("1");
                    intent.putExtra("loginResponse", loginResponse1);
                    startActivity(intent);*/
                }
                finish();




            }
        }, 4000);
    }

    private void findViewById() {

        imgAppIcon = findViewById(R.id.app_icon);
        imgAppIcon.animate().alpha(0.9f).y(300f).setDuration(3000);

        tvTitle = findViewById(R.id.tv_school_title);
        tvTitle.animate().alpha(0.9f).setDuration(2500);
        tvSubTitle = findViewById(R.id.tv_school_subtitle);
        tvSubTitle.animate().alpha(0.9f).setDuration(2500);
        /*tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }
}
