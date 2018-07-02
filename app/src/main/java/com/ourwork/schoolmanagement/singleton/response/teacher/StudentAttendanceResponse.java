
package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;


@SuppressWarnings("unused")
public class StudentAttendanceResponse {


    private String date;

    private String day;

    private List<Object> errors;

    private String monthyear;

    private Sattendanceinfo sattendanceinfo;

    private String sectionID;

    private String set;

    private List<Object> students;

    private List<StudentAttendanceNode> studentsAttandace;

    private Long subjectID;

    private String subjects;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getMonthyear() {
        return monthyear;
    }

    public void setMonthyear(String monthyear) {
        this.monthyear = monthyear;
    }

    public Sattendanceinfo getSattendanceinfo() {
        return sattendanceinfo;
    }

    public void setSattendanceinfo(Sattendanceinfo sattendanceinfo) {
        this.sattendanceinfo = sattendanceinfo;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public List<Object> getStudents() {
        return students;
    }

    public void setStudents(List<Object> students) {
        this.students = students;
    }

    public List<StudentAttendanceNode> getStudentsAttandace() {
        return studentsAttandace;
    }

    public void setStudentsAttandace(List<StudentAttendanceNode> studentsAttandace) {
        this.studentsAttandace = studentsAttandace;
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

}
