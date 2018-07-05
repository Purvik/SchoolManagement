package com.ourwork.schoolmanagement.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

/**
 * Created by Purvik Rana on 15-05-2018.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivityActivity.java";
    private ImageView imgAppIcon;
    private TextView tvTitle, tvSubTitle;
    private static final int MY_ALL_PERMISSIONS_REQUEST_CODE = 100;
    //private Animation iconAnimation, textAnimation;
    //Context mContext;

    SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPrefs = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);

        MobileAds.initialize(this, getResources().getString(R.string.sample_adMob_app_id));

        findViewById();
        //mContext = getApplicationContext();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkAllPermission();

                startApp();
                finish();
            }
        }, 4000);
    }

    private void startApp() {

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
                    /*Intent intent = new Intent(SplashActivityActivity.this, TeacherAttendanceActivity.class);
                    LoginResponse loginResponse1 = new LoginResponse();
                    loginResponse1.setUsertypeID("3");
                    loginResponse1.setUsername("pTeacher");
                    loginResponse1.setDefaultschoolyearID("1");
                    intent.putExtra("loginResponse", loginResponse1);
                    startActivity(intent);*/
        }
    }

    private void checkAllPermission() {

        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    MY_ALL_PERMISSIONS_REQUEST_CODE);

        } else {
            /*//You already have permission
            //Load the location on the Map from the Firebase
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();*/

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case MY_ALL_PERMISSIONS_REQUEST_CODE:
                //Log.d(TAG, "Permission  Array Length:" + grantResults.length);

                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED /*&&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[4] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[5] == PackageManager.PERMISSION_GRANTED*/) {

                    startApp();


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    AlertMessage.showMessage(SplashActivity.this, "Pro-Pathshala Says..","Enable Internet then app will work.", "EXIT");

                   // Toast.makeText(SplashActivity.this, "Enable Internet then app will work.", Toast.LENGTH_SHORT).show();
                    SplashActivity.this.finish();
                }

                return;
        }
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
