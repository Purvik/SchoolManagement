package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 22-06-2018.
 */

public class AttendanceResponseData extends ParentResponse {

    AttendanceResponse data;

    public AttendanceResponse getData() {
        return data;
    }

    public void setData(AttendanceResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AttendanceResponseData{" +
                "data=" + data +
                '}';
    }
}
