package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 21-06-2018.
 */

public class SyllabusResponseData extends ParentResponse{

    SyllabusResponse data;

    public SyllabusResponse getData() {
        return data;
    }

    public void setData(SyllabusResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SyllabusResponseData{" +
                "data=" + data +
                '}';
    }
}
