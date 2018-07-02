package com.ourwork.schoolmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.student.StudentAttendanceActivity;
import com.ourwork.schoolmanagement.activities.teacher.TeacherAttendanceActivity;
import com.ourwork.schoolmanagement.fragments.MainFragment;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 16-05-2018.
 * MainActivity
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity.java";
    Serializable loginuserSerail;
    LoginResponse loginResponse;
    NavigationView navigationView;
    Menu navigationMenu;
    SharedPreferences mPref;
    View navHeaderView;
    ImageView navHeaderProfileIcon;
    Toolbar toolbar;
    TextView navigationDrawerTitle, navigationDrawerSubTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationMenu = navigationView.getMenu();

        navHeaderView = navigationView.getHeaderView(0);
        navHeaderProfileIcon = navHeaderView.findViewById(R.id.navHeaderProfileIcon);
        navigationDrawerTitle = navHeaderView.findViewById(R.id.navigationDrawerTitle);
        navigationDrawerSubTitle = navHeaderView.findViewById(R.id.navigationDrawerSubTitle);


        if(getIntent() != null)
            loginuserSerail = getIntent().getExtras().getSerializable("loggedInUser");
        Log.d(TAG, "onCreate: " + loginuserSerail);

        if (loginuserSerail != null) {

            navigationDrawerTitle.setVisibility(View.VISIBLE);
            navigationDrawerSubTitle.setVisibility(View.VISIBLE);

            loginResponse= (LoginResponse) loginuserSerail;
            navigationDrawerTitle.setText(loginResponse.getUsername());
            navigationDrawerSubTitle.setText(loginResponse.getUsertype());

            /*Picasso.get()
                    .load(R.drawable.ic_login)
                    .transform(new BorderCirularTransform())
                    .into(navHeaderProfileIcon);*/

            /*uncomment when work with actual web url png for profile*/
            /*Picasso.with(getApplicationContext())
                    .load(R.drawable.ic_login)
                    .placeholder(R.drawable.ic_login)
                    .into(navHeaderProfileIcon);*/

            if (loginResponse.getUsertype() .equalsIgnoreCase("teacher")  ) {

                //check gender also then put the icon
                navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_male);

            } else if(loginResponse.getUsertype() .equalsIgnoreCase("student")){

                //check gender also then put the icon
                navHeaderProfileIcon.setImageResource(R.drawable.ic_student_male);

            }else if(loginResponse.getUsertype() .equalsIgnoreCase("admin")){

                //check gender also then put the icon
                navHeaderProfileIcon.setImageResource(R.drawable.ic_teacher_female);

            }
            else{

                //this is for admin
                navHeaderProfileIcon.setImageResource(R.drawable.ic_student_female);

            }

            navHeaderProfileIcon.setOnClickListener(this);

            hideLogInMenuItem(loginResponse.getUsertype());
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

        mPref = getSharedPreferences("loggedInAccountInfo", MODE_PRIVATE);


    }

    private void loadMainGridFragment() {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MainFragment mainFragment = MainFragment.newInstance(loginResponse);
        fragmentTransaction.replace(R.id.frame, mainFragment);
        fragmentTransaction.commit();

    }


    private void hideLogInMenuItem(String userType) {
        //hide login menu item
        navigationMenu.findItem(R.id.nav_login).setVisible(false);

        //display logout menu item
        navigationMenu.findItem(R.id.nav_logout).setVisible(true);

        //customize navigation menu as per user type
        if (userType.equalsIgnoreCase("student")) {

            navigationMenu.findItem(R.id.nav_notice_board).setVisible(false);
            navigationMenu.findItem(R.id.nav_chat).setVisible(false);
            //navigationMenu.findItem(R.id.nav_mail_sms).setVisible(false);

        } else if (userType.equalsIgnoreCase("teacher")) {

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


            case R.id.nav_login:

                //opens login activity from this menu
                Intent loginIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(loginIntent);

                break;

            case R.id.nav_syllabus:

                intent = new Intent(MainActivity.this, SyllabusActivity.class);
                intent.putExtra("loginResponse", loginResponse);
                startActivity(intent);

                break;

            case R.id.nav_timetable:


                intent = new Intent(MainActivity.this, TimeTableActivity.class);
                startActivity(intent);

                break;

            case R.id.nav_homework:

                intent = new Intent(MainActivity.this, HomeworkActivity.class);
                startActivity(intent);

                break;

            case R.id.nav_assignment:

                intent = new Intent(MainActivity.this, AssignmentActivity.class);
                intent.putExtra("loginResponse", loginResponse);
                startActivity(intent);

                break;

            case R.id.nav_attendance:


                if (loginResponse.getUsertype() .equalsIgnoreCase( "student")) {

                    //Student Login
                    intent = new Intent(MainActivity.this, StudentAttendanceActivity.class);
                    intent.putExtra("loginResponse", loginResponse);
                    startActivity(intent);

                } else if (loginResponse.getUsertype() .equalsIgnoreCase( "teacher")) {

                    //Teacher Login
                    intent = new Intent(MainActivity.this, TeacherAttendanceActivity.class);
                    intent.putExtra("loginResponse", loginResponse);
                    startActivity(intent);

                } else{

                    //Admin Login

                }



                break;
            case R.id.nav_behaviour:

                Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_parent_meeting:

                Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_notice_board:

                Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_notifications:

                intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);


                break;

            case R.id.nav_exam:

                intent = new Intent(MainActivity.this, ExamScheduleActivity.class);
                intent.putExtra("loginResponse", loginResponse);
                startActivity(intent);


                break;

            case R.id.nav_result:

                intent = new Intent(MainActivity.this, MarksActivity.class);
                intent.putExtra("loginResponse", loginResponse);
                startActivity(intent);

                break;

            case R.id.nav_sports:

                Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_feepayment:

                Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_gallery:

                intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
              //  Toast.makeText(getApplicationContext(), ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();


                break;

            case R.id.nav_chat:

                break;

            case R.id.nav_about_us:

                break;

            case R.id.nav_settings:

                break;

            case R.id.nav_logout:

                //remove the app preferences from the app
                AppSharedPreferences.removeStoredUserAccount(mPref);

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

                if (loginResponse != null) {
                    displayUserProfileActivity(loginResponse);
                }



                break;
        }

    }

    private void displayUserProfileActivity(LoginResponse loginResponse) {

       /* if (loginResponse.getUsertype().equalsIgnoreCase("student")) {

            *//*StudentUserProfile studentUserProfile =
                    new StudentUserProfile("Krutik Patel",1002,"Primary", "Male","Hindu","Gujarat","5555544444", loginResponse.getUsername(),55,"12/03/1990","B-","p@gmail.com","Surat", "India","10-A");*//*

            Intent actIntent = new Intent(MainActivity.this, UserProfileActivity.class);
            actIntent.putExtra("studentProfile", loginResponse);
            startActivity(actIntent);

        } else if(loginResponse.getUsertype().equalsIgnoreCase("teacher")){*/

           /* TeacherUserProfile teacherUserProfile =
                    new TeacherUserProfile("Ronak Roy", "SingleClass Teacher", "Male", "Christian","5555588888","ronak@9655","25/12/1990","12/05/2015","r@yahoo.in","C-5 High Heights Bunglows, Pragati Nagar, Nadiyad","photo_url");*/

            Intent actIntent = new Intent(MainActivity.this, UserProfileActivity.class);
            actIntent.putExtra("loginResponse", loginResponse);
            startActivity(actIntent);

       /* } else if(loginResponse.getUsertype() .equalsIgnoreCase("admin")){




        }
*/


    }
}
