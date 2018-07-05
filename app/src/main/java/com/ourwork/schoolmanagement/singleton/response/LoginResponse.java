
package com.ourwork.schoolmanagement.singleton.response;

import java.io.Serializable;

@SuppressWarnings("unused")
public class LoginResponse implements Serializable{


    private String classesID;

    private String sectionID;

    private String defaultschoolyearID;
    
    private String email;
    
    private String lang;
    
    private Boolean loggedin;
    
    private String loginuserID;
    
    private String name;
    
    private String photo;

    private String school_id;
    
    private String username;
    
    private String usertype;
    
    private String usertypeID;

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

    public String getDefaultschoolyearID() {
        return defaultschoolyearID;
    }

    public void setDefaultschoolyearID(String defaultschoolyearID) {
        this.defaultschoolyearID = defaultschoolyearID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
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
        return "LoginResponse{" +
                "defaultschoolyearID='" + defaultschoolyearID + '\'' +
                ", email='" + email + '\'' +
                ", lang='" + lang + '\'' +
                ", loggedin=" + loggedin +
                ", loginuserID='" + loginuserID + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", username='" + username + '\'' +
                ", usertype='" + usertype + '\'' +
                ", usertypeID='" + usertypeID + '\'' +
                '}';
    }
}
