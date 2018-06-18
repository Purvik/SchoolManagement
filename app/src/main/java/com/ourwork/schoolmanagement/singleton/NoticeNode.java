package com.ourwork.schoolmanagement.singleton;

/**
 * Created by Purvik Rana on 12-06-2018.
 */

public class NoticeNode {

    private String title;
    private String notice;
    private String date;

    public NoticeNode() {
    }

    public NoticeNode(String title, String notice, String date) {
        this.title = title;
        this.notice = notice;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NoticeNode{" +
                "title='" + title + '\'' +
                ", notice='" + notice + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
