package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 16-07-2018.
 */
public class SubjectNodeResponseData extends ParentResponse {

    private SubjectNodeResponse data;

    public SubjectNodeResponse getData() {
        return data;
    }

    public void setData(SubjectNodeResponse data) {
        this.data = data;
    }


}
