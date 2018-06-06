package com.ourwork.schoolmanagement.singleton;

/**
 * Created by Purvik Rana on 06-06-2018.
 */

public class InnerMarkNode{

    private String subjectName;
    private float obtainedMarks = 0;
    private float highestMarks = 0;
    private float marks = 0;

    public InnerMarkNode() {
    }

    public InnerMarkNode(String subjectName, float obtainedMarks, float highestMarks, float marks) {
        this.subjectName = subjectName;
        this.obtainedMarks = obtainedMarks;
        this.highestMarks = highestMarks;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public float getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(float obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public float getHighestMarks() {
        return highestMarks;
    }

    public void setHighestMarks(float highestMarks) {
        this.highestMarks = highestMarks;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "InnerMarkNode{" +
                "subjectName='" + subjectName + '\'' +
                ", obtainedMarks=" + obtainedMarks +
                ", highestMarks=" + highestMarks +
                ", marks=" + marks +
                '}';
    }
}
