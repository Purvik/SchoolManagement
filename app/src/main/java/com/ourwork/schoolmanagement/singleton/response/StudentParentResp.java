package com.ourwork.schoolmanagement.singleton.response;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 11-07-2018.
 */
public class StudentParentResp extends TeacherAdminResp implements Serializable {

    private String classesID;

    private String sectionID;

    private String defaultschoolyearID;

    private String studentID;

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public void setDefaultschoolyearID(String defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "StudentParentResp{" +
                "classesID='" + classesID + '\'' +
                ", sectionID='" + sectionID + '\'' +
                ", defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", studentID='" + studentID + '\'' +
                ", username='" + username + '\'' +
                "email='" + email + '\'' +
                ", lang='" + lang + '\'' +
                ", loggedin=" + loggedin +
                ", loginuserID='" + loginuserID + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", username='" + username + '\'' +
                ", usertype='" + usertype + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
