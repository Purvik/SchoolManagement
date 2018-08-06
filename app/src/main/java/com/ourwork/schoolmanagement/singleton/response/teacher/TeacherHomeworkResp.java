package com.ourwork.schoolmanagement.singleton.response.teacher;

import java.util.List;

/**
 * Created by Purvik Rana on 28-07-2018.
 */
public class TeacherHomeworkResp {

    List<Object> errors;
    List<TeacherHomeworkNode> homeworks;

    public List<Object> getErrors(){
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<TeacherHomeworkNode> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<TeacherHomeworkNode> homeworks) {
        this.homeworks = homeworks;
    }
}
