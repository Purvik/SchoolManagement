
package com.ourwork.schoolmanagement.singleton.response.teacher;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class TeacherSyllabusNode {

    @SerializedName("class_name")
    private String className;

    private String classesID;

    private String description;

    private String file;

    private String originalfile;

    private String subject;

    private String subjectID;

    private String syllabusID;

    private String title;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassesID() {
        return classesID;
    }

    public void setClassesID(String classesID) {
        this.classesID = classesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getOriginalfile() {
        return originalfile;
    }

    public void setOriginalfile(String originalfile) {
        this.originalfile = originalfile;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSyllabusID() {
        return syllabusID;
    }

    public void setSyllabusID(String syllabusID) {
        this.syllabusID = syllabusID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
