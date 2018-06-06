package com.ourwork.schoolmanagement.singleton;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 06-06-2018.
 */

public class MarkNode {



    private String examTitle;
    private float totalMarks = 0;
    private float avgMarks = 0;
    private ArrayList<InnerMarkNode> innerMarkNodeArrayList;

    public MarkNode() {
    }

    public MarkNode(String examTitle, float totalMarks, float avgMarks, ArrayList<InnerMarkNode> innerMarkNodeArrayList) {
        this.examTitle = examTitle;
        this.totalMarks = totalMarks;
        this.avgMarks = avgMarks;
        this.innerMarkNodeArrayList = innerMarkNodeArrayList;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public float getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(float totalMarks) {
        this.totalMarks = totalMarks;
    }

    public float getAvgMarks() {
        return avgMarks;
    }

    public void setAvgMarks(float avgMarks) {
        this.avgMarks = avgMarks;
    }

    public ArrayList<InnerMarkNode> getInnderMarkNodeArrayList() {
        return innerMarkNodeArrayList;
    }

    public void setInnderMarkNodeArrayList(ArrayList<InnerMarkNode> innderMarkNodeArrayList) {
        this.innerMarkNodeArrayList = innderMarkNodeArrayList;
    }

    @Override
    public String toString() {
        return "MarkNode{" +
                "examTitle='" + examTitle + '\'' +
                ", totalMarks=" + totalMarks +
                ", avgMarks=" + avgMarks +
                ", innerMarkNodeArrayList=" + innerMarkNodeArrayList +
                '}';
    }
}
