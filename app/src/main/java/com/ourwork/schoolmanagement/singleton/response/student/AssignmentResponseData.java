package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 21-06-2018.
 */

public class AssignmentResponseData extends ParentResponse {

    AssignmentResponse data;

    public AssignmentResponse getData() {
        return data;
    }

    public void setData(AssignmentResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AssignmentResponseData{" +
                "data=" + data +
                '}';
    }
}
