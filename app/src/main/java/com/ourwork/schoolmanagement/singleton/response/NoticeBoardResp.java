
package com.ourwork.schoolmanagement.singleton.response;

import java.util.List;

@SuppressWarnings("unused")
public class NoticeBoardResp {

    
    private List<Object> errors;
    
    private List<EventNode> events;
    
    private List<HolidayNode> holidays;
    
    private List<NoticeNode> notices;

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<EventNode> getEvents() {
        return events;
    }

    public void setEvents(List<EventNode> events) {
        this.events = events;
    }

    public List<HolidayNode> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayNode> holidays) {
        this.holidays = holidays;
    }

    public List<NoticeNode> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticeNode> notices) {
        this.notices = notices;
    }

    @Override
    public String toString() {
        return "NoticeBoardResp{" +
                "errors=" + errors +
                ", events=" + events +
                ", holidays=" + holidays +
                ", notices=" + notices +
                '}';
    }
}
