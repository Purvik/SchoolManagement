package com.ourwork.schoolmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

/**
 * Created by Purvik Rana on 15-05-2018.
 */

public class Splash extends AppCompatActivity {

    private static final String TAG = "Splash.java";
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

                LoginResponse loginResponse;
                loginResponse = AppSharedPreferences.getAppPreferences(mPrefs);
                //Log.d(TAG, "run: " + accountUser.toString());


                if (loginResponse != null) {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    intent.putExtra("loggedInUser", loginResponse);
                    startActivity(intent);

                }else{
                    startActivity(new Intent(Splash.this, LogInActivity.class));
                }
                finish();




            }
        }, 4000);
    }

    private void findViewById() {

        imgAppIcon = findViewById(R.id.app_icon);
        tvTitle = findViewById(R.id.tv_school_title);
        tvSubTitle = findViewById(R.id.tv_school_subtitle);
        /*tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }
}
