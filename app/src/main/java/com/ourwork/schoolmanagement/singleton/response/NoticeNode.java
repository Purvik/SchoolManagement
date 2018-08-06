
package com.ourwork.schoolmanagement.singleton.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class NoticeNode implements Parcelable {

    @SerializedName("create_date")
    private String createDate;

    private String date;

    private String notice;

    private String noticeID;
    @SerializedName("school_id")
    private String schoolId;

    private String schoolyearID;

    private String title;

    protected NoticeNode(Parcel in) {
        createDate = in.readString();
        date = in.readString();
        notice = in.readString();
        noticeID = in.readString();
        schoolId = in.readString();
        schoolyearID = in.readString();
        title = in.readString();
    }

    public static final Creator<NoticeNode> CREATOR = new Creator<NoticeNode>() {
        @Override
        public NoticeNode createFromParcel(Parcel in) {
            return new NoticeNode(in);
        }

        @Override
        public NoticeNode[] newArray(int size) {
            return new NoticeNode[size];
        }
    };

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(String noticeID) {
        this.noticeID = noticeID;
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
        parcel.writeString(date);
        parcel.writeString(notice);
        parcel.writeString(noticeID);
        parcel.writeString(schoolId);
        parcel.writeString(schoolyearID);
        parcel.writeString(title);
    }
}
