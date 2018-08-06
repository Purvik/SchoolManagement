package com.ourwork.schoolmanagement.singleton.response.admin;

import java.util.List;

/**
 * Created by Purvik Rana on 02-08-2018.
 */
public class AdminHomeworkResp {

    List<Object> errors;
    List<AdminHomeworkNode> homeworks;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<AdminHomeworkNode> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<AdminHomeworkNode> homeworks) {
        this.homeworks = homeworks;
    }
}
