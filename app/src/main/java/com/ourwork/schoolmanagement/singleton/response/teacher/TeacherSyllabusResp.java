
package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;


@SuppressWarnings("unused")
public class TeacherSyllabusResp {


    private List<Object> errors;

    private List<TeacherSyllabusNode> syllabuss;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<TeacherSyllabusNode> getSyllabuss() {
        return syllabuss;
    }

    public void setSyllabuss(List<TeacherSyllabusNode> syllabuss) {
        this.syllabuss = syllabuss;
    }

}
