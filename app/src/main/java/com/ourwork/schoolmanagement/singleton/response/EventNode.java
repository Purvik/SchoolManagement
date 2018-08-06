
package com.ourwork.schoolmanagement.singleton.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class EventNode implements Parcelable{

    @SerializedName("create_date")
    private String createDate;

    private String details;

    private String eventID;

    private String fdate;

    private String ftime;

    private String photo;
    @SerializedName("school_id")
    private String schoolId;

    private String tdate;

    private String title;

    private String ttime;

    protected EventNode(Parcel in) {
        createDate = in.readString();
        details = in.readString();
        eventID = in.readString();
        fdate = in.readString();
        ftime = in.readString();
        photo = in.readString();
        schoolId = in.readString();
        tdate = in.readString();
        title = in.readString();
        ttime = in.readString();
    }

    public static final Creator<EventNode> CREATOR = new Creator<EventNode>() {
        @Override
        public EventNode createFromParcel(Parcel in) {
            return new EventNode(in);
        }

        @Override
        public EventNode[] newArray(int size) {
            return new EventNode[size];
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

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
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

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createDate);
        parcel.writeString(details);
        parcel.writeString(eventID);
        parcel.writeString(fdate);
        parcel.writeString(ftime);
        parcel.writeString(photo);
        parcel.writeString(schoolId);
        parcel.writeString(tdate);
        parcel.writeString(title);
        parcel.writeString(ttime);
    }
}
