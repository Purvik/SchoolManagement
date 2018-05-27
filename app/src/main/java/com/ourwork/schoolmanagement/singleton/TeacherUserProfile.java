package com.ourwork.schoolmanagement.singleton;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 25-05-2018.
 */

public class TeacherUserProfile implements Serializable{

    private String name;
    private String designation;
    private String gender;
    private String religion;
    private String phone_no;
    private String username;
    private String DOB;
    private String DOJ;
    private String email;
    private String address;
    private String photo_url;

    public TeacherUserProfile() {
    }

    public TeacherUserProfile(String name, String designation, String gender, String religion, String phone_no, String username, String DOB, String DOJ, String email, String address, String photo_url) {
        this.name = name;
        this.designation = designation;
        this.gender = gender;
        this.religion = religion;
        this.phone_no = phone_no;
        this.username = username;
        this.DOB = DOB;
        this.DOJ = DOJ;
        this.email = email;
        this.address = address;
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public String toString() {
        return "TeacherUserProfile{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", gender='" + gender + '\'' +
                ", religion='" + religion + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", username='" + username + '\'' +
                ", DOB='" + DOB + '\'' +
                ", DOJ='" + DOJ + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }
}
