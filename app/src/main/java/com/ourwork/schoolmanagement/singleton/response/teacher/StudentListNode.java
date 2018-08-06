
package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class StudentListNode {

    @SerializedName("class_name")
    private String className;

    private String classesID;

    private String name;

    private String sectionID;
    @SerializedName("section_name")
    private String sectionName;

    private String studentID;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}
