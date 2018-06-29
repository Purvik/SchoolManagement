
package com.ourwork.schoolmanagement.singleton.request.student;

@SuppressWarnings("unused")
public class ParentStudentRequest {



    private String defaultschoolyearID;

    private String username;

    private String usertypeID;

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public String getUsername() {
        return username;
    }

    public String getUsertypeID() {
        return usertypeID;
    }

    public void setDefaultschoolyearID(String defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
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
                "defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", username='" + username + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
