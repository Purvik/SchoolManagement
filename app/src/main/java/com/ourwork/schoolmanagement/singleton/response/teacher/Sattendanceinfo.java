
package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Sattendanceinfo {

    @SerializedName("class")
    private String mClass;
    @SerializedName("date")
    private String mDate;
    @SerializedName("day")
    private String mDay;
    @SerializedName("section")
    private String mSection;

    public String getmClass() {
        return this.mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

}
