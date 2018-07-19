
package com.ourwork.schoolmanagement.singleton.request.admin;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class TeacherListRequest {

    @SerializedName("school_id")
    private String schoolId;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

}
