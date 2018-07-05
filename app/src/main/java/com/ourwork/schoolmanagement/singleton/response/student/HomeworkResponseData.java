package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 03-07-2018.
 */

public class HomeworkResponseData extends ParentResponse {

    HomeworkResponse data;

    public HomeworkResponse getData() {
        return data;
    }

    public void setData(HomeworkResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeworkResponseData{" +
                "data=" + data +
                '}';
    }
}
