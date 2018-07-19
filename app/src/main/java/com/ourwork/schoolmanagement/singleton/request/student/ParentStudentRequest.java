
package com.ourwork.schoolmanagement.singleton.request.student;

@SuppressWarnings("unused")
public class ParentStudentRequest {



    private String defaultschoolyearID;

    private String school_id;

    private String studentID;

    private String usertypeID;

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getUsertypeID() {
        return usertypeID;
    }

    public void setDefaultschoolyearID(String defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setUsertypeID(String usertypeID) {
        this.usertypeID = usertypeID;
    }

    @Override
    public String toString() {
        return "ParentStudentRequest{" +
                "defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", school_id='" + school_id + '\'' +
                ", studentID='" + studentID + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
