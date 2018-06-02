package com.ourwork.schoolmanagement.singleton;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SingleTopic {

    private String topic;
    private String desc;

    public SingleTopic() {
    }

    public SingleTopic(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
