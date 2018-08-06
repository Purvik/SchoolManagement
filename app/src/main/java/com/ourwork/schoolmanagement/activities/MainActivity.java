package com.ourwork.schoolmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.admin.AdminAssignmentListActivity;
import com.ourwork.schoolmanagement.activities.admin.AdminAttendanceActivity;
import com.ourwork.schoolmanagement.activities.admin.StudentListActivity;
import com.ourwork.schoolmanagement.activities.admin.SubjectListActivity;
import com.ourwork.schoolmanagement.activities.admin.TeacherListActivity;
import com.ourwork.schoolmanagement.activities.teacher.TeacherAttendanceActivity;
import com.ourwork.schoolmanagement.fragments.MainFragment;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;
import com.ourwork.schoolmanagement.utils.ForceUpdateAsync;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 16-05-2018.
 * MainActivity
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity.java";
    Serializable loginuserSerial;
    StudentParentResp studentParentResp;
    NavigationView navigationView;
    Menu navigationMenu;
    View navHeaderView;
    ImageView navHeaderProfileIcon;
    Toolbar toolbar;
    TextView navigationDrawerTitle, navigationDrawerSubTitle;
    int backPressCount = 0;
    SharedPreferences mPrefs;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        forceUpdate();
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationMenu = navigationView.getMenu();

        navHeaderView = navigationView.getHeaderView(0);
        navHeaderProfileIcon = navHeaderView.findViewById(R.id.navHeaderProfileIcon);
        navigationDrawerTitle = navHeaderView.findViewById(R.id.navigationDrawerTitle);
        navigationDrawerSubTitle = navHeaderView.findViewById(R.id.navigationDrawerSubTitle);

        //Load Ads
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        mPrefs = getSharedPreferences("loggedInAccountInfo", MODE_PRIVATE);


        if (getIntent() != null)
            loginuserSerial = getIntent().getExtras().getSerializable("loggedInUser");


        if (loginuserSerial != null) {

            navigationDrawerTitle.setVisibility(View.VISIBLE);
            navigationDrawerSubTitle.setVisibility(View.VISIBLE);

            studentParentResp = (StudentParentResp) loginuserSerial;

            Log.e(TAG, "onCreate: " + studentParentResp.toString());

            navigationDrawerTitle.setText(studentParentResp.getUsername());
            navigationDrawerSubTitle.setText(studentParentResp.getUsertype());

            /*Picasso.get()
                    .load(R.drawable.ic_login)
                    .transform(new BorderCirularTransform())
                    .into(navHeaderProfileIcon);*/

            /*uncomment when work with actual web url png for profile*/
            /*Picasso.with(getApplicationContext())
                    .load(R.drawable.ic_login)
                    .placeholder(R.drawable.ic_login)
                    .into(navHeaderProfileIcon);*/

            if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

                //check gender also then put the icon
                if (studentParentResp.getGender().equalsIgnoreCase("Male")) {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_male);
                } else {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_female);
                }


            } else if (studentParentResp.getUsertype().equalsIgnoreCase("student") || studentParentResp.getUsertype().equalsIgnoreCase("parents")) {


                //check gender also then put the icon
                if (studentParentResp.getGender().equalsIgnoreCase("Male")) {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_student_male);
                } else {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_student_female);
                }

            } else if (studentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                //check gender also then put the icon
                if (studentParentResp.getGender().equalsIgnoreCase("Male")) {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_male);
                } else {
                    navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_female);
                }

            } else {

                //this is for admin
                navHeaderProfileIcon.setImageResource(R.drawable.ic_student_female);

            }

            navHeaderProfileIcon.setOnClickListener(this);

            hideLogInMenuItem(studentParentResp.getUsertype());
        }

        loadMainGridFragment();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // TOGGEL ACTIONBAR EVENT WITH ICON
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //drawer.openDrawer(GravityCompat.START);

        // NAVIGATION VIEW PORT
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        //Load the Banned Ads
        mAdView.loadAd(adRequest);

        //Build InterstitialAd Object
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.ads_test_device_id)).build());
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e(TAG, "Interstitial Ads Failed To Load");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "Interstitial Finished Loading ready to show.");
            }
        });


    }

    private void loadMainGridFragment() {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = MainFragment.newInstance(studentParentResp);
        fragmentTransaction.replace(R.id.frame, mainFragment);
        fragmentTransaction.commit();

    }


    private void hideLogInMenuItem(String userType) {
        //hide login menu item
        navigationMenu.findItem(R.id.nav_login).setVisible(false);

        //display logout menu item
        navigationMenu.findItem(R.id.nav_logout).setVisible(true);

        //customize navigation menu as per user type
        if (userType.equalsIgnoreCase("student") || userType.equalsIgnoreCase("parents")) {

            navigationMenu.findItem(R.id.nav_teachers).setVisible(false);
            navigationMenu.findItem(R.id.nav_students).setVisible(false);
            navigationMenu.findItem(R.id.nav_subjects).setVisible(false);
            navigationMenu.findItem(R.id.nav_notifications).setVisible(false);
            navigationMenu.findItem(R.id.nav_chat).setVisible(false);
            //navigationMenu.findItem(R.id.nav_mail_sms).setVisible(false);

        } else if (userType.equalsIgnoreCase("teacher")) {

            navigationMenu.findItem(R.id.nav_teachers).setVisible(false);
            navigationMenu.findItem(R.id.nav_students).setVisible(false);
            navigationMenu.findItem(R.id.nav_subjects).setVisible(false);
            navigationMenu.findItem(R.id.nav_notifications).setVisible(false);
            navigationMenu.findItem(R.id.nav_sports).setVisible(false);

        } else if (userType.equalsIgnoreCase("Admin")) {

            navigationMenu.findItem(R.id.nav_notifications).setVisible(false);
            navigationMenu.findItem(R.id.nav_sports).setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // Close DrawerLayout after selection of particular item
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Intent intent;

        switch (id) {


            //invisible
            case R.id.nav_login:

                //opens login activity from this menu
                Intent loginIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(loginIntent);

                break;

            // ADMIN
            case R.id.nav_teachers:

                if (studentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                    intent = new Intent(MainActivity.this, TeacherListActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();

                }


                //Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            // ADMIN
            case R.id.nav_students:

                if (studentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                    intent = new Intent(MainActivity.this, StudentListActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();
                }

                break;

            // ADMIN
            case R.id.nav_subjects:

                if (studentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                    //Toast.makeText(getApplicationContext(), AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    intent = new Intent(MainActivity.this, SubjectListActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();
                }

                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_syllabus:


                intent = new Intent(MainActivity.this, SyllabusActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);


                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_timetable:

                intent = new Intent(MainActivity.this, TimeTableActivity.class);
                startActivity(intent);

                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_homework:

                /*intent = new Intent(MainActivity.this, HomeworkActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);*/


                intent = new Intent(MainActivity.this, HomeworkActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);


                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_assignment:

                if (studentParentResp.getUsertype() .equalsIgnoreCase( "admin")) {

                    intent = new Intent(MainActivity.this, AdminAssignmentListActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);

                } else {

                    intent = new Intent(MainActivity.this, AssignmentActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);
                }


                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_attendance:


                if (studentParentResp.getUsertype().equalsIgnoreCase("student")) {

                    //Student Login
                    /*intent = new Intent(MainActivity.this, StudentAttendanceActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);*/

                    Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                } else if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

                    //Teacher Login
                    intent = new Intent(MainActivity.this, TeacherAttendanceActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);

                    //Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                } else {

                    //Admin Login
                    intent = new Intent(MainActivity.this, AdminAttendanceActivity.class);
                    intent.putExtra("loginResponse", studentParentResp);
                    startActivity(intent);
                   // Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                }
                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_behaviour:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_parent_meeting:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_notice_board:

                intent = new Intent(MainActivity.this, NoticeBoardActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            //STUDENT
            case R.id.nav_notifications:

                /*intent = new Intent(MainActivity.this, NoticeBoardActivity.class);
                startActivity(intent);*/

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();


                break;

            //ADMIN/TEACHER/PARENT/STUDENT
            case R.id.nav_exam:

                /*intent = new Intent(MainActivity.this, ExamScheduleActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);*/

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();


                break;

            case R.id.nav_result:

                /*intent = new Intent(MainActivity.this, MarksActivity.class);
                intent.putExtra("loginResponse", studentParentResp);
                startActivity(intent);*/

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_sports:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_feepayment:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_gallery:

               /* intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);*/
                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();


                break;

            case R.id.nav_chat:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_about_us:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_settings:

                Toast.makeText(getApplicationContext(), "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_logout:

                //remove the app preferences from the app
                AppSharedPreferences.removeStoredUserAccount(mPrefs);

                Intent logInIntent = new Intent(MainActivity.this, LogInActivity.class);
                logInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logInIntent);
                finish();

                break;


        }


        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.navHeaderProfileIcon:

                //Toast.makeText(getApplicationContext(), "ICON CLICKER", Toast.LENGTH_SHORT).show();

                if (studentParentResp != null) {
                    displayUserProfileActivity(studentParentResp);
                }


                break;
        }

    }

    private void displayUserProfileActivity(StudentParentResp studentParentResp) {

        /* if (loginStudentParentResponse.getUsertype().equalsIgnoreCase("student")) {

         *//*StudentUserProfile studentUserProfile =
                    new StudentUserProfile("Krutik Patel",1002,"Primary", "Male","Hindu","Gujarat","5555544444", loginStudentParentResponse.getUsername(),55,"12/03/1990","B-","p@gmail.com","Surat", "India","10-A");*//*

            Intent actIntent = new Intent(MainActivity.this, UserProfileActivity.class);
            actIntent.putExtra("studentProfile", loginStudentParentResponse);
            startActivity(actIntent);

        } else if(loginStudentParentResponse.getUsertype().equalsIgnoreCase("teacher")){*/

           /* TeacherUserProfile teacherUserProfile =
                    new TeacherUserProfile("Ronak Roy", "SingleClass Teacher", "Male", "Christian","5555588888","ronak@9655","25/12/1990","12/05/2015","r@yahoo.in","C-5 High Heights Bunglows, Pragati Nagar, Nadiyad","photo_url");*/

        Intent actIntent = new Intent(MainActivity.this, UserProfileActivity.class);
        actIntent.putExtra("loginResponse", studentParentResp);
        startActivity(actIntent);

       /* } else if(loginStudentParentResponse.getUsertype() .equalsIgnoreCase("admin")){




        }
*/


    }

    @Override
    public void onBackPressed() {

        if (backPressCount == 0) {

            backPressCount = 1;
            Toast.makeText(MainActivity.this, "Press Back Again to Exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressCount = 0;
                }
            }, 3500);

        } else {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

            super.onBackPressed();
        }

    }

    public void forceUpdate() {

        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;
        new ForceUpdateAsync(currentVersion, MainActivity.this).execute();
    }
}
