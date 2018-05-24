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
import com.ourwork.schoolmanagement.fragments.MainFragment;
import com.ourwork.schoolmanagement.singleton.AccountUser;
import com.ourwork.schoolmanagement.singleton.StudentUserProfile;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;
import com.ourwork.schoolmanagement.utils.BorderCirularTransform;
import com.ourwork.schoolmanagement.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Purvik Rana on 16-05-2018.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity.java";
    Serializable accountUserSerial;
    AccountUser accountUser;
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

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationMenu = navigationView.getMenu();

        navHeaderView = navigationView.getHeaderView(0);
        navHeaderProfileIcon = navHeaderView.findViewById(R.id.navHeaderProfileIcon);
        navigationDrawerTitle = navHeaderView.findViewById(R.id.navigationDrawerTitle);
        navigationDrawerSubTitle = navHeaderView.findViewById(R.id.navigationDrawerSubTitle);


        accountUserSerial = getIntent().getExtras().getSerializable("loggedInUser");
        Log.d(TAG, "onCreate: " + accountUserSerial);

        //Toast.makeText(getApplicationContext(), "" + accountUserSerial.toString(), Toast.LENGTH_LONG).show();

        if (accountUserSerial != null) {

            navigationDrawerTitle.setVisibility(View.VISIBLE);
            navigationDrawerSubTitle.setVisibility(View.VISIBLE);

            accountUser= (AccountUser) accountUserSerial;
            navigationDrawerTitle.setText(accountUser.getUsername());
            navigationDrawerSubTitle.setText(accountUser.getUsertype());

            /*Picasso.get()
                    .load(R.drawable.ic_login)
                    .transform(new BorderCirularTransform())
                    .into(navHeaderProfileIcon);*/

            /*"https://maxcdn.icons8.com/Share/icon/color/Users//donald_trump1600.png"*/
            Picasso.with(getApplicationContext())
                    .load(R.drawable.ic_login)
                    .placeholder(R.drawable.ic_login)
                    .into(navHeaderProfileIcon);

            navHeaderProfileIcon.setOnClickListener(this);


            hideLogInMenuItem(accountUser.getUsertype());
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
        MainFragment mainFragment = MainFragment.newInstance(accountUser);
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
            navigationMenu.findItem(R.id.nav_student).setVisible(false);
            navigationMenu.findItem(R.id.nav_parents).setVisible(false);
            navigationMenu.findItem(R.id.nav_mail_sms).setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // Close DrawerLayout after selection of particular item
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        switch (id) {


            case R.id.nav_login:

                //opens login activity from this menu
                Intent loginIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(loginIntent);

                break;

            case R.id.nav_student:

                break;

            case R.id.nav_parents:

                break;
            case R.id.nav_attendants:

                break;

            case R.id.nav_assignments:

                break;
            case R.id.nav_mail_sms:

                break;

            case R.id.nav_marks:

                break;

            case R.id.nav_materials:

                break;

            case R.id.nav_notifications:

                break;

            case R.id.nav_about_us:

                break;

            case R.id.nav_settings:

                break;

            case R.id.nav_logout:

                //remove the app preferences from the app
                AppSharedPreferences.removeStoredUserAccount(mPref);

                startActivity(new Intent(MainActivity.this, LogInActivity.class));

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

                if (accountUser != null) {
                    displayUserProfileActivity(accountUser.getUsertype());
                }



                break;
        }

    }

    private void displayUserProfileActivity(String userType) {

        if (userType.equalsIgnoreCase("student")) {

            StudentUserProfile studentUserProfile =
                    new StudentUserProfile(1002,"Primary", "Male","Hindu","Gujarat","5555544444", accountUser.getUsername(),55,"12/03/1990","B-","p@gmail.com","Surat", "India","10-A");

            Intent actIntent = new Intent(MainActivity.this, UserProfileActivity.class);
            actIntent.putExtra("studentProfile", studentUserProfile);

            startActivity(actIntent);
        }



    }
}
