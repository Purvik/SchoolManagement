
package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class SubjectNode {


    private String classes;

    private String classesID;

    private String subject;
    @SerializedName("subject_code")
    private String subjectCode;

    private String subjectID;

    private String teacherID;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "SubjectNode{" +
                "classes='" + classes + '\'' +
                ", classesID='" + classesID + '\'' +
                ", subject='" + subject + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", teacherID='" + teacherID + '\'' +
                '}';
    }

}
