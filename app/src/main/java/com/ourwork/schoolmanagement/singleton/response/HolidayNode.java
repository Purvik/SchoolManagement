
package com.ourwork.schoolmanagement.singleton.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class HolidayNode implements Parcelable {

    @SerializedName("create_date")
    private String createDate;

    private String details;

    private String fdate;

    private String holidayID;

    private String photo;
    @SerializedName("school_id")
    private String schoolId;

    private String schoolyearID;

    private String tdate;

    private String title;

    protected HolidayNode(Parcel in) {
        createDate = in.readString();
        details = in.readString();
        fdate = in.readString();
        holidayID = in.readString();
        photo = in.readString();
        schoolId = in.readString();
        schoolyearID = in.readString();
        tdate = in.readString();
        title = in.readString();
    }

    public static final Creator<HolidayNode> CREATOR = new Creator<HolidayNode>() {
        @Override
        public HolidayNode createFromParcel(Parcel in) {
            return new HolidayNode(in);
        }

        @Override
        public HolidayNode[] newArray(int size) {
            return new HolidayNode[size];
        }
    };

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(String holidayID) {
        this.holidayID = holidayID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolyearID() {
        return schoolyearID;
    }

    public void setSchoolyearID(String schoolyearID) {
        this.schoolyearID = schoolyearID;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createDate);
        parcel.writeString(details);
        parcel.writeString(fdate);
        parcel.writeString(holidayID);
        parcel.writeString(photo);
        parcel.writeString(schoolId);
        parcel.writeString(schoolyearID);
        parcel.writeString(tdate);
        parcel.writeString(title);
    }
}
