package com.ourwork.schoolmanagement.singleton.response;

/**
 * Created by Purvik Rana on 16-06-2018.
 */

public class LoginResp extends ParentResponse {
    LoginResponse data;

    public LoginResponse getData() {
        return data;
    }

    public void setData(LoginResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "data=" + data +
                '}';
    }
}
