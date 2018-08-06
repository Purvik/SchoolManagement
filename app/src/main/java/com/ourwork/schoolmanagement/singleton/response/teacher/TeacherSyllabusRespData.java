package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 04-08-2018.
 */
public class TeacherSyllabusRespData extends ParentResponse {

    TeacherSyllabusResp data;

    public TeacherSyllabusResp getData() {
        return data;
    }

    public void setData(TeacherSyllabusResp data) {
        this.data = data;
    }
}
