package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

import java.util.List;

/**
 * Created by Purvik Rana on 29-06-2018.
 */

public class SectionListResponse extends ParentResponse {

    private List<Object> errors;

    private List<SectionListNode> section;

    public List<SectionListNode> getSection() {
        return section;
    }

    public void setSection(List<SectionListNode> section) {
        this.section = section;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }
}
