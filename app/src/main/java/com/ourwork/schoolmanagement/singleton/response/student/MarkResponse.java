package com.ourwork.schoolmanagement.singleton.response.student;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Purvik Rana on 28-06-2018.
 */

public class MarkResponse {

    private List<Object> errors;

    private MarkStudentNode student;

    private List<MarkExamNode> exams;

    private List<MarkNode> marks;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public MarkStudentNode getStudent() {
        return student;
    }

    public void setStudent(MarkStudentNode student) {
        this.student = student;
    }

    public List<MarkExamNode> getExams() {
        return exams;
    }

    public void setExams(List<MarkExamNode> exams) {
        this.exams = exams;
    }

    public List<MarkNode> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkNode> marks) {
        this.marks = marks;
    }
}
