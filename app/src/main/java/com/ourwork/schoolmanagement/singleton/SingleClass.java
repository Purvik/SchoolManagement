package com.ourwork.schoolmanagement.singleton;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class SingleClass implements Serializable {

    private int id;
    private String name;
    private String prof;
    private String place;
    private int day;
    private int startTime;
    private int endTime;
    private int length;

    public SingleClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getLength() {
        return (getEndTime() - getStartTime());
    }

    public void setLength(int length) {
        this.length = length;
    }
}
