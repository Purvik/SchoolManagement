package com.ourwork.schoolmanagement.singleton.response.admin;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 30-07-2018.
 */
public class AdminAssignmentRespData  extends ParentResponse {

    AdminAssignmentResp data;

    public AdminAssignmentResp getData() {
        return data;
    }

    public void setData(AdminAssignmentResp data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdminAssignmentRespData{" +
                "data=" + data +
                '}';
    }
}
