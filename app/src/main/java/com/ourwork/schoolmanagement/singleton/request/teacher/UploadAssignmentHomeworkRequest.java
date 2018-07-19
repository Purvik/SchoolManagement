package com.ourwork.schoolmanagement.singleton.request.teacher;

import java.io.File;

/**
 * Created by Purvik Rana on 18-07-2018.
 */
public class UploadAssignmentHomeworkRequest {

    private String title;

    private String description;
    private String deadlinedate;
    private String classesID;
    private String sectionID;
    private String subjectID;
    private String school_id;
    private String usertypeID;
    private String userID;
    private File file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadlinedate() {
        return deadlinedate;
    }

    public void setDeadlinedate(String deadlinedate) {
        this.deadlinedate = deadlinedate;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(String usertypeID) {
        this.usertypeID = usertypeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UploadAssignmentHomeworkRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadlinedate='" + deadlinedate + '\'' +
                ", classesID='" + classesID + '\'' +
                ", sectionID='" + sectionID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", school_id='" + school_id + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                ", userID='" + userID + '\'' +
                ", file=" + file.getAbsolutePath() +
                '}';
    }
}
