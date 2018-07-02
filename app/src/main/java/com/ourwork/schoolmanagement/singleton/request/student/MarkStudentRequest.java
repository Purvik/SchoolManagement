
package com.ourwork.schoolmanagement.singleton.request.student;

@SuppressWarnings("unused")
public class MarkStudentRequest {



    private String schoolyearID;

    private String username;

    private String usertypeID;

    public String getSchoolyearID() {
        return schoolyearID;
    }

    public void setSchoolyearID(String schoolyearID) {
        this.schoolyearID = schoolyearID;
    }

    public String getUsername() {
        return username;
    }

    public String getUsertypeID() {
        return usertypeID;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsertypeID(String usertypeID) {
        this.usertypeID = usertypeID;
    }

    @Override
    public String toString() {
        return "ParentStudentRequest{" +
                "defaultschoolyearID='" + schoolyearID + '\'' +
                ", username='" + username + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
