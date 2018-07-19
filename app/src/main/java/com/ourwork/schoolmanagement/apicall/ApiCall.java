package com.ourwork.schoolmanagement.apicall;


import com.ourwork.schoolmanagement.singleton.request.LoginRequest;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.request.student.MarkStudentRequest;
import com.ourwork.schoolmanagement.singleton.request.student.ParentStudentRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSectionRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetStudentListRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.GetSubjectRequest;
import com.ourwork.schoolmanagement.singleton.request.teacher.ParentTeacherRequest;
import com.ourwork.schoolmanagement.singleton.response.ResponseLogin;
import com.ourwork.schoolmanagement.singleton.response.admin.TeacherListResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.AssignmentResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.AttendanceResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.HomeworkResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.MarkResponseData;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.AssignmentUploadResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListResponse;
import com.ourwork.schoolmanagement.singleton.response.teacher.SectionListResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.StudentAttendanceResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.SubjectNodeResponseData;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCall {


    /*
    * Student API
    * */

    @POST(AppConstant.URL_LOGIN)
    Call<ResponseLogin> login_student_parent(@Body LoginRequest loginRequest);

    /*@POST(AppConstant.URL_LOGIN)
    Call<ResponseLogin> login_teacher_admin(@Body LoginRequest loginRequest);*/

    @POST(AppConstant.URL_SYLLABUS)
    Call<SyllabusResponseData> syllabus(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_ASSIGNMENT)
    Call<AssignmentResponseData> assignment(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_STUDENT_ATTENDANCE)
    Call<AttendanceResponseData> student_attendance(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_EXAM_SCHEDULE)
    Call<ExamScheduleResponseData> exam_schedule(@Body ParentStudentRequest parentStudentRequest);

    @POST(AppConstant.URL_HOMEWORK)
    Call<HomeworkResponseData> homework(@Body ParentStudentRequest parentStudentRequest);


    /*
    * Teacher APIs OLD API LIST
    * */

    @POST(AppConstant.URL_MARKS)
    Call<MarkResponseData> mark(@Body MarkStudentRequest markStudentRequest);

    @POST(AppConstant.URL_CLASS_LIST)
    Call<TeacherClassNodeResponseData> get_class_list(@Body ParentTeacherRequest parentTeacherRequest);

    @POST(AppConstant.URL_SECTION_LIST)
    Call<SectionListResponse> get_section_list(@Body GetSectionRequest getSectionRequest);

    @POST(AppConstant.URL_STUDENT_LIST_FOR_ATTENDANCE)
    Call<StudentAttendanceResponseData> student_list_for_attendance(@Body GetStudentListRequest getStudentListRequest);




    /*
    * TEACHER API
    * */
    @POST(AppConstant.URL_CLASS_LIST)
    Call<TeacherClassNodeResponseData> class_list(@Body TeacherListRequest teacherListRequest);

    @POST(AppConstant.URL_SECTION_LIST)
    Call<SectionListResponseData> section_list (@Body GetSectionRequest getSectionRequest);

    @POST(AppConstant.URL_SUBJECT_LIST)
    Call<SubjectNodeResponseData> subject_list (@Body GetSubjectRequest getSubjectRequest);

    @POST(AppConstant.URL_UPLOAD_ASSIGNMENT)
    @FormUrlEncoded
    Call<AssignmentUploadResponseData> upload_assignment(@FieldMap Map<String, String> params);

    @POST(AppConstant.URL_UPLOAD_HOMEWORK)
    @FormUrlEncoded
    Call<AssignmentUploadResponseData> upload_homework(@FieldMap Map<String, String> params);

    /*
    * ADMIN API
    * */
    @POST(AppConstant.URL_TEACHER_LIST)
    Call<TeacherListResponseData> teacher_list (@Body TeacherListRequest teacherListRequest);






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
