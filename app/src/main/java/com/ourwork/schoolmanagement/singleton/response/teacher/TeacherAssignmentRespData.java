package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 28-07-2018.
 */
public class TeacherAssignmentRespData extends ParentResponse {

    TeacherAssignmentResp data;

    public TeacherAssignmentResp getData() {
        return data;
    }

    public void setData(TeacherAssignmentResp data) {
        this.data = data;
    }
}
