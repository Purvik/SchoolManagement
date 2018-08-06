package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 20-07-2018.
 */
public class StudentListRespData extends ParentResponse {

    private StudentListResp data;

    public StudentListResp getData() {
        return data;
    }

    public void setData(StudentListResp data) {
        this.data = data;
    }
}
