package com.ourwork.schoolmanagement.activities.teacher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNode;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherClassNodeResponseData;
import com.ourwork.schoolmanagement.utils.AlertMessage;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 16-07-2018.
 */
public class AddSyllabusActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    EditText edtTitle, edtDesc; /*, edtDeadlineDate;*/
    private Context mContext;
    private Toolbar toolbar;
    private StudentParentResp studentParentResp;
    private ProgressDialog pDialog;
    private Spinner classSpinner;/*, sectionSpinner, subjectSpinner;*/
    private Button btnUpload;

    private List<TeacherClassNode> teacherClassNodeList;

    /*private List<SectionListNode> sectionListNodeList;

    private List<SubjectNode> subjectNodeList;*/

    private List<String> classList; /*,sectinList, subjectList;*/
    private TeacherClassNode selectedTeacherClassNode;

    /*private SectionListNode selectedSectionListNode;
    private SubjectNode selectedSubjectNode;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_syllabus);

        mContext = AddSyllabusActivity.this;

        findViewById();

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null)
            studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");

        if (studentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

            pDialog = new ProgressDialog(this);
            pDialog.setMessage("loading class list...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();

            TeacherListRequest teacherListRequest = new TeacherListRequest();
            teacherListRequest.setSchoolId(studentParentResp.getSchoolId());

            /*
             * call to get class list
             * */
            Call<TeacherClassNodeResponseData> call = apiCall.class_list(teacherListRequest);
            call.enqueue(new Callback<TeacherClassNodeResponseData>() {
                @Override
                public void onResponse(Call<TeacherClassNodeResponseData> call, Response<TeacherClassNodeResponseData> response) {

                    if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                        teacherClassNodeList = response.body().getData().getClasses();

                        if (teacherClassNodeList.size() != 0) {

                            classList = new ArrayList<String>();
                            classList.add("Select Class");

                            for (TeacherClassNode teacherClassNode : teacherClassNodeList) {
                                String className = teacherClassNode.getClasses();
                                classList.add(className);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    mContext, android.R.layout.simple_spinner_item, classList);

                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

                            classSpinner.setAdapter(adapter);
                            classSpinner.setSelection(0, false);
                            classSpinner.setOnItemSelectedListener(AddSyllabusActivity.this);


                        } else {
                            Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_NO_DATA, Toast.LENGTH_LONG).show();
                        }

                        edtTitle.clearFocus();

                        if (pDialog.isShowing())
                            pDialog.dismiss();


                    } else {

                        if (pDialog.isShowing())
                            pDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Call<TeacherClassNodeResponseData> call, Throwable t) {

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();


                }
            });


        } else {
            //Not the Teacher login Disable

            AlertMessage.showMessage(mContext, "Pro-Pathshala Says..", "Not Authorised to Access this feature", "Exit");
        }

        //Select File intent Call
        findViewById(R.id.btnSelectFileToUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_GET_CONTENT);/*, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);*/
                i.setType("*/*");
                startActivityForResult(i, 100);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edtTitle.getText().toString();
                String description = edtDesc.getText().toString();
                //String deadlinedate = edtDeadlineDate.getText().toString();
                Date parsedDate = new Date();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");

                /*try {
                    parsedDate = formatter.parse(deadlinedate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/


                if (selectedTeacherClassNode == null ||/*
                        selectedSectionListNode == null ||
                        selectedSubjectNode == null ||*/
                        title.length() == 0 ||
                        description.length() == 0 /*||
                        parsedDate.toString() == ""*/
                         ) {

                    AlertMessage.showMessage(mContext, "Pro-Pathshala Says..", "Enter Proper Details", "RETRY");
                } else{

                    Log.e("Btn Click",  title +"|" + description + " | " + parsedDate.toString());


                }


            }
        });


    }

    private void findViewById() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add New Syllabus");

        classSpinner = findViewById(R.id.spinnerClassList);
        classSpinner.setFocusable(true);
        classSpinner.setFocusableInTouchMode(true);
        classSpinner.requestFocus();

       /* sectionSpinner = findViewById(R.id.spinnerSectionList);
        subjectSpinner = findViewById(R.id.spinnerSubjectList);*/

        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDescription);
        /*edtDeadlineDate = findViewById(R.id.edtDeadlineDate);*/

        btnUpload = findViewById(R.id.btnUpload);
        /*if (addItemType .equalsIgnoreCase("assignment")) {
            btnUpload.setText(getResources().getString(R.string.btn_label_assignment_upload));
            toolbar.setTitle("Add New AdminAssignmentListNode");
        }else{
            btnUpload.setText(getResources().getString(R.string.btn_label_homework_upload));
            toolbar.setTitle("Add New Homework");
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //the image URI
            /*Uri selectedImage = data.getData();
            File file = new File(getRealPathFromURI(selectedImage));*/

            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;

            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = mContext.getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
            }

            ((TextView)findViewById(R.id.tvUploadFileName)).setText(displayName);


            //calling the upload file method after choosing the file
           // uploadFile(selectedImage, "My Image");
        }
    }

    private void uploadFile(Uri fileUri, String desc) {

        //creating a file
        File file = new File(getRealPathFromURI(fileUri));


        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);

        /*//creating a call and calling the upload image method
        Call<MyResponse> call = api.uploadImage(requestFile, descBody);

        //finally performing the call
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (!response.body().error) {
                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/
    }

    /*
     * This method is fetching the absolute path of the image file
     * if you want to upload other kind of files like .pdf, .docx
     * you need to make changes on this method only
     * Rest part will be the same
     * */
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*
     * Spinner Overssiden Methos for Selection Events
     * */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {

            //when Class is get selected
            case R.id.spinnerClassList:

                if (adapterView.getSelectedItemPosition() == 0) {

                    //sectionSpinner.setAdapter(null);

                    Toast.makeText(mContext, "Select Class from list", Toast.LENGTH_SHORT).show();


                } /*else {

                    selectedTeacherClassNode = teacherClassNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    Log.e("class list", "onItemSelected: " + selectedTeacherClassNode.toString());

                    *//*
                     * Call GetSection API to load the section list
                     * *//*
                    pDialog = new ProgressDialog(this);
                    pDialog.setMessage("loading section list...");
                    pDialog.setCanceledOnTouchOutside(false);
                    pDialog.show();

                    GetSectionRequest getSectionRequest = new GetSectionRequest();
                    getSectionRequest.setSchoolId(studentParentResp.getSchoolId());
                    getSectionRequest.setClassesID(selectedTeacherClassNode.getClassesID());

                    Call<SectionListResponseData> sectionListCall = apiCall.section_list(getSectionRequest);
                    sectionListCall.enqueue(new Callback<SectionListResponseData>() {
                        @Override
                        public void onResponse(Call<SectionListResponseData> call, Response<SectionListResponseData> response) {

                            if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                                sectionListNodeList = response.body().getData().getData();

                                if (sectionListNodeList.size() != 0) {

                                    sectinList = new ArrayList<String>();
                                    sectinList.add("Select Section");

                                    for (SectionListNode sectionListNode : sectionListNodeList) {
                                        String sectionName = sectionListNode.getSection();
                                        sectinList.add(sectionName);
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                            mContext, android.R.layout.simple_spinner_item, sectinList);

                                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

                                    sectionSpinner.setAdapter(adapter);
                                    sectionSpinner.setSelection(0, false);
                                    sectionSpinner.setOnItemSelectedListener(AddSyllabusActivity.this);


                                } else {
                                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_NO_DATA, Toast.LENGTH_LONG).show();
                                }

                                if (pDialog.isShowing())
                                    pDialog.dismiss();

                            } else {

                                if (pDialog.isShowing())
                                    pDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<SectionListResponseData> call, Throwable t) {

                            if (pDialog.isShowing())
                                pDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                        }
                    });

                }*/

                break;

            //when Section is get selected
            /*case R.id.spinnerSectionList:

                if (adapterView.getSelectedItemPosition() == 0) {

                    subjectSpinner.setAdapter(null);
                    Toast.makeText(mContext, "Select Section from list", Toast.LENGTH_SHORT).show();


                } else {

                    selectedSectionListNode = sectionListNodeList.get(adapterView.getSelectedItemPosition() - 1);

                    *//*
                     * call get Subject list API
                     * *//*
                    pDialog = new ProgressDialog(this);
                    pDialog.setMessage("loading subject list...");
                    pDialog.setCanceledOnTouchOutside(false);
                    pDialog.show();

                    GetSubjectRequest getSubjectRequest = new GetSubjectRequest();
                    getSubjectRequest.setUsertypeID(Long.parseLong(studentParentResp.getUsertypeID()));
                    getSubjectRequest.setLoginuserID(studentParentResp.getLoginuserID());
                    getSubjectRequest.setSchoolId(studentParentResp.getSchoolId());
                    getSubjectRequest.setClassesID(selectedTeacherClassNode.getClassesID());
                    getSubjectRequest.setDefaultschoolyearID("1");

                    Log.e("GetSubjectList", "onItemSelected: " + getSubjectRequest.toString());

                    Call<SubjectNodeResponseData> subjectListCall = apiCall.subject_list(getSubjectRequest);
                    subjectListCall.enqueue(new Callback<SubjectNodeResponseData>() {

                        @Override
                        public void onResponse(Call<SubjectNodeResponseData> call, Response<SubjectNodeResponseData> response) {

                            Log.e("GetSubjectList", "onResponse: " + response.code());

                            if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                                subjectNodeList = response.body().getData().getSubjects();

                                if (subjectNodeList.size() != 0) {

                                    subjectList = new ArrayList<String>();
                                    subjectList.add("Select Subject");

                                    for (SubjectNode subjectNode : subjectNodeList) {
                                        String sectionName = subjectNode.getSubject();
                                        subjectList.add(sectionName);
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                            mContext, android.R.layout.simple_spinner_item, subjectList);

                                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

                                    subjectSpinner.setAdapter(adapter);
                                    subjectSpinner.setSelection(0, false);
                                    subjectSpinner.setOnItemSelectedListener(AddSyllabusActivity.this);


                                } else {
                                    Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_NO_DATA, Toast.LENGTH_LONG).show();
                                }

                                if (pDialog.isShowing())
                                    pDialog.dismiss();

                            } else {

                                //Response code invalid
                                if (pDialog.isShowing())
                                    pDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<SubjectNodeResponseData> call, Throwable t) {

                            if (pDialog.isShowing())
                                pDialog.dismiss();

                            Toast.makeText(getApplicationContext(), t.getMessage() + "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                        }
                    });
                }

                break;*/

            /*case R.id.spinnerSubjectList:

                if (adapterView.getSelectedItemPosition() == 0) {
                    Toast.makeText(mContext, "Select Subject from list", Toast.LENGTH_SHORT).show();
                } else {

                    selectedSubjectNode = subjectNodeList.get(adapterView.getSelectedItemPosition() - 1);

                }

                break;*/


        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
