package com.ourwork.schoolmanagement.singleton;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Purvik Rana on 23-05-2018.
 */

public class StudentUserProfile implements Serializable{

    private int reg_no;
    private String section;
    private String gender;
    private String religion;
    private String state;
    private String phone_no;
    private String username;
    private int roll;
    private String DOB;
    private String blood_group;
    private String email;
    private String address;
    private String country;
    private String class_no;


    public StudentUserProfile() {
    }

    public StudentUserProfile(int reg_no, String section, String gender, String religion, String state, String phone_no, String username, int roll, String DOB, String blood_group, String email, String address, String country,String class_no) {
        this.reg_no = reg_no;
        this.section = section;
        this.gender = gender;
        this.religion = religion;
        this.state = state;
        this.phone_no = phone_no;
        this.username = username;
        this.roll = roll;
        this.DOB = DOB;
        this.blood_group = blood_group;
        this.email = email;
        this.address = address;
        this.country = country;
        this.class_no = class_no;
    }

    public int getReg_no() {
        return reg_no;
    }

    public void setReg_no(int reg_no) {
        this.reg_no = reg_no;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    @Override
    public String toString() {
        return "StudentUserProfile{" +
                "reg_no=" + reg_no +
                ", section='" + section + '\'' +
                ", gender='" + gender + '\'' +
                ", religion='" + religion + '\'' +
                ", state='" + state + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", username='" + username + '\'' +
                ", roll=" + roll +
                ", DOB=" + DOB +
                ", blood_group='" + blood_group + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", class='" + class_no+ '\'' +
                '}';
    }
}
