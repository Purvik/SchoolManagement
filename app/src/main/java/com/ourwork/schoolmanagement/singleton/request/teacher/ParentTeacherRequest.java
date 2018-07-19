
package com.ourwork.schoolmanagement.singleton.request.teacher;

@SuppressWarnings("unused")
public class ParentTeacherRequest {

    private String defaultschoolyearID;

    private String username;

    private String usertypeID;

    private String school_id;

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(String usertypeID) {
        this.usertypeID = usertypeID;
    }

    @Override
    public String toString() {
        return "ParentTeacherRequest{" +
                "defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", username='" + username + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
