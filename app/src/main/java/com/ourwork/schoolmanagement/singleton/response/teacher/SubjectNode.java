
package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class SubjectNode {


    private String classes;

    private String classesID;
    @SerializedName("classes_numeric")
    private String classesNumeric;
    @SerializedName("create_date")
    private String createDate;
    @SerializedName("create_userID")
    private String createUserID;
    @SerializedName("create_username")
    private String createUsername;
    @SerializedName("create_usertype")
    private String createUsertype;

    private String finalmark;
    @SerializedName("modify_date")
    private String modifyDate;

    private String note;

    private String passmark;
    @SerializedName("school_id")
    private String schoolId;

    private String studentmaxID;

    private String subject;
    @SerializedName("subject_author")
    private String subjectAuthor;
    @SerializedName("subject_code")
    private String subjectCode;

    private String subjectID;

    private String teacherID;
    @SerializedName("teacher_name")
    private String teacherName;

    private String type;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getClassesNumeric() {
        return classesNumeric;
    }

    public void setClassesNumeric(String classesNumeric) {
        this.classesNumeric = classesNumeric;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getCreateUsertype() {
        return createUsertype;
    }

    public void setCreateUsertype(String createUsertype) {
        this.createUsertype = createUsertype;
    }

    public String getFinalmark() {
        return finalmark;
    }

    public void setFinalmark(String finalmark) {
        this.finalmark = finalmark;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassmark() {
        return passmark;
    }

    public void setPassmark(String passmark) {
        this.passmark = passmark;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getStudentmaxID() {
        return studentmaxID;
    }

    public void setStudentmaxID(String studentmaxID) {
        this.studentmaxID = studentmaxID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectAuthor() {
        return subjectAuthor;
    }

    public void setSubjectAuthor(String subjectAuthor) {
        this.subjectAuthor = subjectAuthor;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
