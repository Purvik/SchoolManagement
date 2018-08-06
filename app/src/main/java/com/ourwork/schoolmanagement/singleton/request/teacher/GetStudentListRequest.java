
package com.ourwork.schoolmanagement.singleton.request.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetStudentListRequest {

    
    private Long classesID;
    
    private Long defaultschoolyearID;
    @SerializedName("school_id")
    private Long schoolId;
    
    private Long sectionID;

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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }

    @Override
    public String toString() {
        return "GetStudentListRequest{" +
                "classesID=" + classesID +
                ", defaultschoolyearID=" + defaultschoolyearID +
                ", schoolId=" + schoolId +
                ", sectionID=" + sectionID +
                '}';
    }
}
