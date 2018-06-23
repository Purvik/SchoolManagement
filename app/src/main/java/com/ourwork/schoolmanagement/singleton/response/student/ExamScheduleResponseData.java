package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 23-06-2018.
 */

public class ExamScheduleResponseData extends ParentResponse {

    ExamScheduleResponse data;

    public ExamScheduleResponse getData() {
        return data;
    }

    public void setData(ExamScheduleResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExamScheduleResponseData{" +
                "data=" + data +
                '}';
    }
}
