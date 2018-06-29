package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 28-06-2018.
 */

public class MarkResponseData extends ParentResponse {

    MarkResponse data;

    public MarkResponse getData() {
        return data;
    }

    public void setData(MarkResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MarkResponseData{" +
                "data=" + data +
                '}';
    }
}
