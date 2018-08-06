package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;

/**
 * Created by Purvik Rana on 28-07-2018.
 */
public class TeacherAssignmentResp {

    List<Object> errors;

    List<TeacherAssignmentNode> assignments;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<TeacherAssignmentNode> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TeacherAssignmentNode> assignments) {
        this.assignments = assignments;
    }
}
