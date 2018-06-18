package com.ourwork.schoolmanagement.apicall;


import com.ourwork.schoolmanagement.singleton.request.LoginRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResp;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCall {

    @POST(AppConstant.URL_LOGIN)
    Call<LoginResp> login(@Body LoginRequest loginRequest);

   /* @GET(AppConstant.URL_FORGOT_PASSWORD)
    Call<Boolean> forgotPassword(@Query("email") String email);

    @POST(AppConstant.URL_CHANGE_PASSWORD)
    Call<Boolean> changePassword(@Header("token") String token,
                                 @Body ChangePasswordRequest changePasswordRequest);

    @POST(AppConstant.URL_LOGOUT)
    Call<Void> logout(@Header("token") String token);

    @GET(AppConstant.URL_DASHBOARD)
    Call<List<Department>> getDashboard(@Header("token") String token);

    @GET(AppConstant.URL_NOTES)
    Call<Notes> getNotes(@Query("departmentId") String departmentId);

    @GET(AppConstant.URL_NOTICES)
    Call<NoticeResp> getNotices(@Query("departmentId") String departmentId);

    @POST(AppConstant.URL_REGISTER_TOKEN)
    Call<Boolean> registerToken(@Body RegisterTokenRequest registerTokenRequest);*/
}
