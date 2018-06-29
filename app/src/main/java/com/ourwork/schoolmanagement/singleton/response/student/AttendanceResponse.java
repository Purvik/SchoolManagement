
package com.ourwork.schoolmanagement.singleton.response.student;

import java.util.List;


@SuppressWarnings("unused")
public class AttendanceResponse {


    private List<AttendanceNode> attendances;

    private List<Object> errors;

    public List<AttendanceNode> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceNode> attendances) {
        this.attendances = attendances;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "AttendanceResponse{" +
                "attendances=" + attendances +
                ", errors=" + errors +
                '}';
    }
}
