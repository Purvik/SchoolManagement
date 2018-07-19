
package com.ourwork.schoolmanagement.singleton.response.teacher;

@SuppressWarnings("unused")
public class SectionListNode {


    private String section;

    private String sectionID;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    @Override
    public String toString() {
        return "SectionListNode{" +
                "section='" + section + '\'' +
                ", sectionID='" + sectionID + '\'' +
                '}';
    }
}
