
package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;


@SuppressWarnings("unused")
public class TeacherClassNodeResponse {


    private List<TeacherClassNode> classes;

    private List<Object> errors;

    public List<TeacherClassNode> getClasses() {
        return classes;
    }

    public void setClasses(List<TeacherClassNode> classes) {
        this.classes = classes;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

}
