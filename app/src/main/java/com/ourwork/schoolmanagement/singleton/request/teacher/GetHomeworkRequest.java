
package com.ourwork.schoolmanagement.singleton.request.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetHomeworkRequest {

     
    private Long classesID;
     
    private Long defaultschoolyearID;
     
    private Long loginuserID;
    @SerializedName("school_id")
    private Long schoolId;
     
    private Long usertypeID;

    public Long getClassesID() {
        return classesID;
    }

    public void setClassesID(Long classesID) {
        this.classesID = classesID;
    }

    public Long getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public void setDefaultschoolyearID(Long defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public Long getLoginuserID() {
        return loginuserID;
    }

    public void setLoginuserID(Long loginuserID) {
        this.loginuserID = loginuserID;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(Long usertypeID) {
        this.usertypeID = usertypeID;
    }

}
