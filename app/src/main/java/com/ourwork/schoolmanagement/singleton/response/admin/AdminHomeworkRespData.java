package com.ourwork.schoolmanagement.singleton.response.admin;

import com.ourwork.schoolmanagement.singleton.response.ParentResponse;

/**
 * Created by Purvik Rana on 02-08-2018.
 */
public class AdminHomeworkRespData extends ParentResponse {

    AdminHomeworkResp data;

    public AdminHomeworkResp getData() {
        return data;
    }

    public void setData(AdminHomeworkResp data) {
        this.data = data;
    }
}
