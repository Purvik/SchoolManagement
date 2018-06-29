package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class TeacherClassNodeResponseData extends ParentResponse {

    TeacherClassNodeResponse data;

    public TeacherClassNodeResponse getData() {
        return data;
    }

    public void setData(TeacherClassNodeResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TeacherClassNodeResponseData{" +
                "data=" + data +
                '}';
    }
}
