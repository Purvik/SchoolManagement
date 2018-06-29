
package com.ourwork.schoolmanagement.singleton.response.student;

import java.util.List;


@SuppressWarnings("unused")
public class ExamScheduleResponse {


    private List<Object> errors;

    private List<ExamScheduleNode> examschedules;

    private String set;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<ExamScheduleNode> getExamschedules() {
        return examschedules;
    }

    public void setExamschedules(List<ExamScheduleNode> examschedules) {
        this.examschedules = examschedules;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
