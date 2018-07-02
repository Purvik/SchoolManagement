
package com.ourwork.schoolmanagement.singleton.request.teacher;

@SuppressWarnings("unused")
public class GetStudentListRequest {


    private String classesID;

    private String date;

    private String loginuserID;

    private String schoolyearID;

    private String sectionID;

    private String username;

    private String usertype;

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoginuserID() {
        return loginuserID;
    }

    public void setLoginuserID(String loginuserID) {
        this.loginuserID = loginuserID;
    }

    public String getSchoolyearID() {
        return schoolyearID;
    }

    public void setSchoolyearID(String schoolyearID) {
        this.schoolyearID = schoolyearID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
