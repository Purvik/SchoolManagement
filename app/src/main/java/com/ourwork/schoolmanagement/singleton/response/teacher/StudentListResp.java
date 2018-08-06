
package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;


@SuppressWarnings("unused")
public class StudentListResp {


    private List<Object> errors;

    private Long set;

    private List<StudentListNode> students;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public Long getSet() {
        return set;
    }

    public void setSet(Long set) {
        this.set = set;
    }

    public List<StudentListNode> getStudents() {
        return students;
    }

    public void setStudents(List<StudentListNode> students) {
        this.students = students;
    }

}
