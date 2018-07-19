
package com.ourwork.schoolmanagement.singleton.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class TeacherAdminResp implements Serializable{

    protected String email;

    protected String gender;

    protected String lang;

    protected Boolean loggedin;

    protected String loginuserID;

    protected String name;

    protected String photo;

    @SerializedName("school_id")
    protected String schoolId;

    protected String username;

    protected String usertype;

    protected String usertypeID;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String getLoginuserID() {
        return loginuserID;
    }

    public void setLoginuserID(String loginuserID) {
        this.loginuserID = loginuserID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(String usertypeID) {
        this.usertypeID = usertypeID;
    }

    @Override
    public String toString() {
        return "TeacherAdminResp{" +
                "email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", lang='" + lang + '\'' +
                ", loggedin=" + loggedin +
                ", loginuserID='" + loginuserID + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", username='" + username + '\'' +
                ", usertype='" + usertype + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
