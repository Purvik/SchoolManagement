
package com.ourwork.schoolmanagement.singleton.response.student;


@SuppressWarnings("unused")
public class AssignmentSectionNode {

    
    private String category;
    
    private String classesID;
    
    private String section;
    
    private String sectionID;
    
    private String teacherID;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "AssignmentSectionNode{" +
                "category='" + category + '\'' +
                ", classesID='" + classesID + '\'' +
                ", section='" + section + '\'' +
                ", sectionID='" + sectionID + '\'' +
                ", teacherID='" + teacherID + '\'' +
                '}';
    }
}
