package com.ourwork.schoolmanagement.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.AccountUser;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById();

        //ActivityLoginBinding binding = DataBindingUtil.setContentView(LogInActivity.this, R.layout.activity_login);

    }

    private void findViewById() {

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(this);
        mPref = getSharedPreferences("loggedInAccountInfo",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogIn:

                String userName = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (userName.length() != 0 && password.length() != 0) {

                    if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {

                        AccountUser accountUser = new AccountUser("admin", "admin", "admin");
                        AppSharedPreferences.storeAppPreferences(mPref, accountUser);
                        startMainActivity(accountUser);

                    } else if (userName.equalsIgnoreCase("student") && password.equalsIgnoreCase("student")) {

                        AccountUser accountUser = new AccountUser("student", "student", "student");
                        AppSharedPreferences.storeAppPreferences(mPref, accountUser);
                        startMainActivity(accountUser);

                    } else if (userName.equalsIgnoreCase("teacher") && password.equalsIgnoreCase("teacher")) {

                        AccountUser accountUser = new AccountUser("teacher", "teacher", "teacher");
                        AppSharedPreferences.storeAppPreferences(mPref, accountUser);
                        startMainActivity(accountUser);

                    } else {

                        Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Please Provide All Details", Toast.LENGTH_LONG).show();


                }


                break;

        }

    }

    private void startMainActivity(AccountUser accountUser) {

        Intent mainIntent = new Intent(LogInActivity.this, MainActivity.class);
        mainIntent.putExtra("loggedInUser", accountUser);
        startActivity(mainIntent);

    }
}
