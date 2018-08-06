package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 28-07-2018.
 */
public class TeacherHomeworkRespData extends ParentResponse {


    TeacherHomeworkResp data;

    public TeacherHomeworkResp getData() {
        return data;
    }

    public void setData(TeacherHomeworkResp data) {
        this.data = data;
    }
}
