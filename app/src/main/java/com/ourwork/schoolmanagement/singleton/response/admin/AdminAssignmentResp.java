
package com.ourwork.schoolmanagement.singleton.response.admin;

import org.json.JSONObject;

import java.util.List;


@SuppressWarnings("unused")
public class AdminAssignmentResp {


    private List<AdminAssignmentListNode> assignments;

    private List<Object> errors;

    private JSONObject sections;

    public List<AdminAssignmentListNode> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AdminAssignmentListNode> assignments) {
        this.assignments = assignments;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public JSONObject getSections() {
        return sections;
    }

    public void setSections(JSONObject sections) {
        this.sections = sections;
    }
}
