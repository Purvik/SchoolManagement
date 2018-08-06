
package com.ourwork.schoolmanagement.singleton.request;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class NoticeBoardRequest {


    private Long defaultschoolyearID;
    @SerializedName("school_id")
    private Long schoolId;

    public Long getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public void setDefaultschoolyearID(Long defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

}
