package com.ourwork.schoolmanagement.singleton;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SingleSubjectDetails {

    private String subname;
    private ArrayList<SingleTopic> topiclist;

    public SingleSubjectDetails() {
    }

    public SingleSubjectDetails(String subname, ArrayList<SingleTopic> topiclist) {
        this.subname = subname;
        this.topiclist = topiclist;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public ArrayList<SingleTopic> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(ArrayList<SingleTopic> topiclist) {
        this.topiclist = topiclist;
    }

    @Override
    public String toString() {
        return "SingleSubjectDetails{" +
                "subname='" + subname + '\'' +
                ", topiclist=" + topiclist +
                '}';
    }
}
