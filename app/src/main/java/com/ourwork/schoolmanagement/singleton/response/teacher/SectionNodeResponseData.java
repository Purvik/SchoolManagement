package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

import java.util.List;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class SectionNodeResponseData extends ParentResponse {

    List<SectionNode> data;

    public List<SectionNode> getData() {
        return data;
    }

    public void setData(List<SectionNode> data) {
        this.data = data;
    }
}
