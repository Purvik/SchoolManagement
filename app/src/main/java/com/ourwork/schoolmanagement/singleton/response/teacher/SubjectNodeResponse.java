package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;

/**
 * Created by Purvik Rana on 16-07-2018.
 */
public class SubjectNodeResponse {

    private List<Object> errors;

    private List<SubjectNode> subjects;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<SubjectNode> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectNode> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "SubjectNodeResponse{" +
                "errors=" + errors +
                ", subjects=" + subjects +
                '}';
    }
}
