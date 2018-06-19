package com.ourwork.schoolmanagement.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.LoginRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResp;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.ourwork.schoolmanagement.utils.AppSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    SharedPreferences mPref;
    private ProgressDialog pDialog;


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


                        pDialog = new ProgressDialog(this);
                        pDialog.setMessage("Please Wait");
                        pDialog.setCanceledOnTouchOutside(false);
                        pDialog.show();
                        final LoginRequest loginRequest = new LoginRequest();
                        loginRequest.setUsername(userName);
                        loginRequest.setPassword(password);


                        /*
                        * Check the log in code
                        * */
                        Call<LoginResp> call = apiCall.login(loginRequest);
                        call.enqueue(new Callback<LoginResp>() {
                            @Override
                            public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {
                                Log.e("Resp", response.code() +" ");
                                pDialog.dismiss();

                                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                                    //Log.d("Resp", "" + response.body());
                                    LoginResponse loginResponse = response.body().getData();
                                    Log.d("Resp", "" + loginResponse.toString());



                                    AppSharedPreferences.storeAppPreferences(mPref, loginResponse);
                                    startMainActivity(loginResponse);

                                }

                            }

                            @Override
                            public void onFailure(Call<LoginResp> call, Throwable t) {

                                Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
                                pDialog.dismiss();

                            }
                        });

                } else {

                    Toast.makeText(getApplicationContext(), "Please Provide All Details", Toast.LENGTH_LONG).show();


                }


                break;

        }

    }

    private void startMainActivity(LoginResponse loginResponse) {

        Intent mainIntent = new Intent(LogInActivity.this, MainActivity.class);
        mainIntent.putExtra("loggedInUser", loginResponse);
        startActivity(mainIntent);

    }
}
