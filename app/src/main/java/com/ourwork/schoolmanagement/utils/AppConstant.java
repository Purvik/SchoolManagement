package com.ourwork.schoolmanagement.utils;

/**
 * Created by Admin on 23-10-2017.
 */

public class AppConstant {

    public static final String PREFS_FILE_NAME_PARAM = "ENOTICE.PREF";

    public static final int ONE_SECOND = 1000;
    public static final int REQUEST_CALL_PHONE = 100;
    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_FAIL = 500;
    public static final int RESPONSE_CODE_TOKEN_EXPIRED = 401;
    public static final String API_RESPONSE_FAILURE = "API Call Response Failure";
    public static final String APP_NOT_DEVELOPED_YET = "Not Developed Yet !";
    public static final String APP_RESPONSE_NO_DATA = "No Data Found!";

    public static final String CONTENT_TYPE = "application/json";

    public static final String UI_DATE_FORMAT = "MM/dd/yyyy";
    public static final String UI_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm a";
    public static final String WEB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DEFAULT_DEPT_ID = "00000000-0000-0000-0000-000000000000";

    // Share pref constants
    public static final String IS_LOGIN = "isLogin";
    public static final String USERID = "user_id";
    public static final String USERNAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    public static final String FCM_TOKEN = "fcm_token";
    public static final String DEVICE_ID = "device_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "ph_no";
    public static final String ADDRESS = "addr";
    public static final String CITY = "city";
    public static final String ZIPCODE = "zipcode";
    public static final String STATE_ID = "state_id";
    public static final String COUNTRY_ID = "contry_id";
    public static final String DEPT_ID = "desp_id";
    public static final String DEPT_NAME = "desp_name";


    public static final String URL_BASE = "http://propathshala.vrithub.in/api/";
    public static final String URL_LOGIN="signin";
    public static final String URL_SYLLABUS="syllabus";
    public static final String URL_ASSIGNMENT="assignment";
    public static final String URL_STUDENT_ATTENDANCE="student_attendance";
    public static final String URL_EXAM_SCHEDULE= "examschedule";
    public static final String URL_MARKS = "mark";

    public static final String URL_CLASS_LIST = "classes";
    public static final String URL_SECTION_LIST = "getsection";
    public static final String URL_SUBJECT_LIST = "subject";

    public static final String URL_STUDENT_LIST_FOR_ATTENDANCE = "student_list_for_attendance";
    public static final String URL_HOMEWORK = "homework";
    public static final String URL_TEACHER_LIST = "teacher";
    public static final String URL_UPLOAD_ASSIGNMENT = "add_assignment";
    public static final String URL_UPLOAD_HOMEWORK= "add_homework";



    public static final int[] leapYearMonthDays = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};
    public static final int[] normalYearMonthDays = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};


    public static final String URL_FORGOT_PASSWORD="Global/ForgotPassword";
    public static final String URL_CHANGE_PASSWORD="Global/ChangePassword";
    public static final String URL_LOGOUT="Global/Logout";
    public static final String URL_DASHBOARD="Dashboard";
    public static final String URL_NOTES="Global/GetNotes";
    public static final String URL_NOTICES="Global/GetNotices";
    public static final String URL_REGISTER_TOKEN="Notification/RegisterToken";

}
