
package com.ourwork.schoolmanagement.singleton.response.student;

import java.util.List;


@SuppressWarnings("unused")
public class HomeworkResponse {


    private List<Object> errors;

    private List<HomeworkNode> homeworks;

    private HomeworkSectionNode section;

    private String set;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<HomeworkNode> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<HomeworkNode> homeworks) {
        this.homeworks = homeworks;
    }

    public HomeworkSectionNode getSection() {
        return section;
    }

    public void setSection(HomeworkSectionNode section) {
        this.section = section;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

}
