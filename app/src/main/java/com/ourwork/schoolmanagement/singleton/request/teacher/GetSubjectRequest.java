
package com.ourwork.schoolmanagement.singleton.request.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetSubjectRequest {


    private String defaultschoolyearID;

    private String classesID;
    
    private String loginuserID;
    @SerializedName("school_id")
    private String schoolId;
    
    private Long usertypeID;

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public void setDefaultschoolyearID(String defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getLoginuserID() {
        return loginuserID;
    }

    public void setLoginuserID(String loginuserID) {
        this.loginuserID = loginuserID;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Long getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(Long usertypeID) {
        this.usertypeID = usertypeID;
    }

    @Override
    public String toString() {
        return "GetSubjectRequest{" +
                "defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", classesID='" + classesID + '\'' +
                ", loginuserID='" + loginuserID + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", usertypeID=" + usertypeID +
                '}';
    }
}
