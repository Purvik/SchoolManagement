
package com.ourwork.schoolmanagement.singleton.request.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetSectionRequest {


    private String classesID;
    @SerializedName("school_id")
    private String schoolId;

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "GetSectionRequest{" +
                "classesID='" + classesID + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
