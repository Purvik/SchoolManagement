
package com.ourwork.schoolmanagement.singleton.response.teacher;

@SuppressWarnings("unused")
public class TeacherClassNode {


    private String classes;

    private String classesID;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    @Override
    public String toString() {
        return "TeacherClassNode{" +
                "classes='" + classes + '\'' +
                ", classesID='" + classesID + '\'' +
                '}';
    }
}
