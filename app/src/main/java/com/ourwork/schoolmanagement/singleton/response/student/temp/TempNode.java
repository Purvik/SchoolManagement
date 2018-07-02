
package com.ourwork.schoolmanagement.singleton.response.student.temp;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

import java.util.List;


@SuppressWarnings("unused")
public class TempNode   {

    
    private List<Object> errors;
    
    private List<Exam> exams;
    
    private List<Mark> marks;
    
    private Student student;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
