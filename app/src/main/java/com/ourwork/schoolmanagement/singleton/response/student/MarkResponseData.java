package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;
import com.ourwork.schoolmanagement.singleton.response.student.temp.TempNode;

/**
 * Created by Purvik Rana on 28-06-2018.
 */

public class MarkResponseData extends ParentResponse {

    TempNode data;

    public TempNode getData() {
        return data;
    }

    public void setData(TempNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MarkResponseData{" +
                "data=" + data +
                '}';
    }
}
