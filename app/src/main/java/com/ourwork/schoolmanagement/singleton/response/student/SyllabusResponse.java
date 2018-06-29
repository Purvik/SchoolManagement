
package com.ourwork.schoolmanagement.singleton.response.student;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

import java.util.List;


@SuppressWarnings("unused")
public class SyllabusResponse {

       
    private List<Object> errors;
       
    private String set;
       
    private List<SyllabusNode> syllabuss;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public List<SyllabusNode> getSyllabusNodes() {
        return syllabuss;
    }

    public void setSyllabusNodes(List<SyllabusNode> syllabusNodes) {
        this.syllabuss = syllabusNodes;
    }

    @Override
    public String toString() {
        return "SyllabusResponse{" +
                "errors=" + errors +
                ", set='" + set + '\'' +
                ", syllabusNodes=" + syllabuss +
                '}';
    }
}
