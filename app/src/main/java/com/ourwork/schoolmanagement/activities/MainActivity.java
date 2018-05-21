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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.CustomGrid;
import com.ourwork.schoolmanagement.fragments.MainFragment;
import com.ourwork.schoolmanagement.singleton.AccountUser;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 16-05-2018.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity.java";
    Serializable accountUser;
    NavigationView navigationView;
    Menu navigationMenu;
    SharedPreferences mPref;
    GridView mainGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationMenu = navigationView.getMenu();

        accountUser = getIntent().getExtras().getSerializable("loggedInUser");
        Log.d(TAG, "onCreate: " + accountUser);

        if (accountUser != null) {

            hideLogInMenuItem();
        }

        loadMainGridFragment();
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // TOGGEL ACTIONBAR EVENT WITH ICON
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.openDrawer(GravityCompat.START);

        // NAVIGATION VIEW PORT
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        mPref = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);


    }

    private void loadMainGridFragment() {
        MainFragment mainFragment = new MainFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, mainFragment);
        fragmentTransaction.commit();
    }


    private void hideLogInMenuItem() {
        //hide login menu item
        navigationMenu.findItem(R.id.nav_login).setVisible(false);

        //display logout menu item
        navigationMenu.findItem(R.id.nav_logout).setVisible(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // Close DrawerLayout after selection of particular item
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (id) {


            case R.id.nav_login:

                //opens login activity from this menu
                Intent loginIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(loginIntent);

                break;

            case R.id.nav_calender:

                break;

            case R.id.nav_taketest:

                break;
            case R.id.nav_facility:

                break;

            case R.id.nav_achievments:

                break;
            case R.id.nav_lunchmenu:

                break;

            case R.id.nav_forms:

                break;

            case R.id.nav_exam_timetable:

                break;

            case R.id.nav_exam_paper:

                break;

            case R.id.nav_about_us:

                break;

            case R.id.nav_settings:

                break;

            case R.id.nav_logout:

                //remove the app preferences from the app
                AppSharedPreferences.storeAppPreferences(mPref, new AccountUser());


                break;
        }


        return false;
    }
}
