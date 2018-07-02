package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 30-06-2018.
 */

public class StudentAttendanceResponseData extends ParentResponse {

    StudentAttendanceResponse data;

    public StudentAttendanceResponse getData() {
        return data;
    }

    public void setData(StudentAttendanceResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StudentAttendanceResponseData{" +
                "data=" + data +
                '}';
    }
}
