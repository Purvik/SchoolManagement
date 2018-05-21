package com.ourwork.schoolmanagement.singleton;

import java.io.Serializable;

/**
 * Created by Purvik Rana on 18-05-2018.
 */

public class AccountUser implements Serializable{

    private String username;
    private String password;
    private String usertype;

    public AccountUser() {
    }

    public AccountUser(String username, String password, String usertype) {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }
}
