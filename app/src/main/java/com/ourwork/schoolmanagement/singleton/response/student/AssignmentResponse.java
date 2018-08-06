
package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.admin.AdminAssignmentListNode;

import java.util.List;


@SuppressWarnings("unused")
public class AssignmentResponse {


    private List<AdminAssignmentListNode> assignments;

    private List<Object> errors;

    private AssignmentSectionNode section;

    private String set;

    public List<AdminAssignmentListNode> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AdminAssignmentListNode> assignmentNodes) {
        this.assignments = assignmentNodes;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public AssignmentSectionNode getSection() {
        return section;
    }

    public void setSection(AssignmentSectionNode sectionNode) {
        this.section = sectionNode;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "AssignmentResponse{" +
                "assignmentNodes=" + assignments +
                ", errors=" + errors +
                ", sectionNode=" + section +
                ", set='" + set + '\'' +
                '}';
    }
}
