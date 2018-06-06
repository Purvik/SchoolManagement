package com.ourwork.schoolmanagement.singleton;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class AssignmentNode {

    private String title;
    private String desc;
    private String deadline;
    private String uploader;
    private boolean hasAttachment;
    private String downloadUrl;

    public AssignmentNode() {
    }

    public AssignmentNode(String title, String desc, String deadline, String uploader, boolean hasAttachment, String downloadUrl) {
        this.title = title;
        this.desc = desc;
        this.deadline = deadline;
        this.uploader = uploader;
        this.hasAttachment = hasAttachment;
        this.downloadUrl = downloadUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public String getDonwloadUrl() {
        return downloadUrl;
    }

    public void setDonwloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "HomeWorkNode{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", deadline='" + deadline + '\'' +
                ", uploader='" + uploader + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
