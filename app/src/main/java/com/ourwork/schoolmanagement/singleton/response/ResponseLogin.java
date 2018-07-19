package com.ourwork.schoolmanagement.singleton.response;

/**
 * Created by Purvik Rana on 11-07-2018.
 */
public class ResponseLogin extends ParentResponse {

    private StudentParentResp data;

    public StudentParentResp getData() {
        return data;
    }

    public void setData(StudentParentResp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "data=" + data +
                '}';
    }
}
