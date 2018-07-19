package com.ourwork.schoolmanagement.singleton.response.admin;

import java.util.List;

/**
 * Created by Purvik Rana on 12-07-2018.
 */
public class TeacherNodeResp {

    private List<Object> errors;
    private List<TeacherNode> teachers;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<TeacherNode> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherNode> teachers) {
        this.teachers = teachers;
    }
}
