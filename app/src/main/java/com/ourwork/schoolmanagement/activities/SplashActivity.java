package com.ourwork.schoolmanagement.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

/**
 * Created by Purvik Rana on 15-05-2018.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity.java";
    private ImageView imgAppIcon;
    private TextView tvTitle, tvSubTitle;
    private static final int MY_ALL_PERMISSIONS_REQUEST_CODE = 100;
    private TextView tvAppVersion;
    //private Animation iconAnimation, textAnimation;
    //Context mContext;

    SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPrefs = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);

        MobileAds.initialize(this, getResources().getString(R.string.adMob_app_id));

        findViewById();
        //mContext = getApplicationContext();

        //Get the VersionName and Display to the Text View on the Splash Screen
        PackageManager manager = getApplicationContext().getPackageManager();
        PackageInfo info = null;

        try {
            info = manager.getPackageInfo(
                    getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //Log.d("Version Code", ""+info.versionName);
        tvAppVersion.setText("v_" + info.versionName);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                Log.e(TAG, "Before checkAllPermission" );

                checkAllPermission();

//                Log.e(TAG, "After checkAllPermission" );

                //finish();
            }
        }, 3500);
    }

    private void checkAllPermission() {

        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            Log.e(TAG, "checkAllPermission: Permission Called" );
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_ALL_PERMISSIONS_REQUEST_CODE);

        } else {
            /*//You already have permission
            //Load the location on the Map from the Firebase
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();*/

            Log.e(TAG, "checkAllPermission: Permission Already Granted" );
            startApp();
//            finish();
            finishAndRemoveTask();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        Log.e(TAG, "onRequestPermissionsResult: Called");

        switch (requestCode) {
            case MY_ALL_PERMISSIONS_REQUEST_CODE:
                //Log.d(TAG, "Permission  Array Length:" + grantResults.length);

                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED /*&&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[4] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[5] == PackageManager.PERMISSION_GRANTED*/) {

                    startApp();


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Log.e(TAG, "checkAllPermission: Permission Not Granted" );
                    AlertMessage.showMessage(SplashActivity.this, "Pro-Pathshala Says..","Enable Permissions then app will work.", "EXIT");

                   // Toast.makeText(SplashActivity.this, "Enable Internet then app will work.", Toast.LENGTH_SHORT).show();

                }

//                SplashActivity.this.finish();
                SplashActivity.this.finishAndRemoveTask();

                //return;
        }
    }

    private void startApp() {

        StudentParentResp studentParentResp = AppSharedPreferences.getUserlogInAppPreferences(mPrefs);
        //Log.d(TAG, "run: " + studentParentResp.toString());

        if (studentParentResp != null) {

            //already login redirect user to the main home screen
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("loggedInUser", studentParentResp);
            startActivity(intent);

        }else{

            //Provide Login Screen to the user
            startActivity(new Intent(SplashActivity.this, LogInActivity.class));

        }
        finish();
    }

    private void findViewById() {

        imgAppIcon = findViewById(R.id.app_icon);
        imgAppIcon.animate().alpha(0.9f).setDuration(2500);

        tvTitle = findViewById(R.id.tv_school_title);
        tvTitle.animate().alpha(0.9f).setDuration(2500);
        tvSubTitle = findViewById(R.id.tv_school_subtitle);
        tvSubTitle.animate().alpha(0.9f).setDuration(2500);

        tvAppVersion = findViewById(R.id.app_version);
        tvAppVersion.animate().alpha(0.9f).setDuration(2500);
    }


}
