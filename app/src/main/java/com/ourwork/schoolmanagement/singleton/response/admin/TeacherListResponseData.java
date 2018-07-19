package com.ourwork.schoolmanagement.singleton.response.admin;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 12-07-2018.
 */
public class TeacherListResponseData extends ParentResponse {

    TeacherNodeResp data;

    public TeacherNodeResp getData() {
        return data;
    }

    public void setData(TeacherNodeResp data) {
        this.data = data;
    }
}
