package com.ourwork.schoolmanagement.apicall;


import com.ourwork.schoolmanagement.singleton.request.LoginRequest;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.ParentTeacherRequest;
import com.ourwork.schoolmanagement.singleton.response.LoginResp;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.MarkResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiCall {

    @POST(AppConstant.URL_LOGIN)
    Call<LoginResp> login(@Body LoginRequest loginRequest);

    @POST(AppConstant.URL_SYLLABUS)
    Call<SyllabusResponseData> syllabus(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_ASSIGNMENT)
    Call<AssignmentResponseData> assignment(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_STUDENT_ATTENDANCE)
    Call<AttendanceResponseData> student_attendance(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_EXAM_SCHEDULE)
    Call<ExamScheduleResponseData> exam_schedule(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_MARKS)
    Call<MarkResponseData> mark(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_CLASS_LIST)
    Call<TeacherClassNodeResponseData> get_class_list(@Body ParentTeacherRequest parentTeacherRequest);

   /* @GET(AppConstant.URL_FORGOT_PASSWORD)
    Call<Boolean> forgotPassword(@Query("email") String email);

    @POST(AppConstant.URL_CHANGE_PASSWORD)
    Call<Boolean> changePassword(@Header("token") String token,
                                 @Body ChangePasswordRequest changePasswordRequest);

    @POST(AppConstant.URL_LOGOUT)
    Call<Void> logout(@Header("token") String token);

    @GET(AppConstant.URL_DASHBOARD)
    Call<List<Department>> getDashboard(@Header("token") String token);

    @GET(AppConstant.URL_NOTES)
    Call<Notes> getNotes(@Query("departmentId") String departmentId);

    @GET(AppConstant.URL_NOTICES)
    Call<NoticeResp> getNotices(@Query("departmentId") String departmentId);

    @POST(AppConstant.URL_REGISTER_TOKEN)
    Call<Boolean> registerToken(@Body RegisterTokenRequest registerTokenRequest);*/
}
