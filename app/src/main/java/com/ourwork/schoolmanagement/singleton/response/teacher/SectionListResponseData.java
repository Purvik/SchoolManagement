package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 16-07-2018.
 */
public class SectionListResponseData extends ParentResponse {

    private SectionListResponse data;

    public SectionListResponse getData() {
        return data;
    }

    public void setData(SectionListResponse data) {
        this.data = data;
    }
}
