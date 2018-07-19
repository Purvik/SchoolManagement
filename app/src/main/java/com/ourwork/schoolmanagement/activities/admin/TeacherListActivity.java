package com.ourwork.schoolmanagement.activities.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.ExpandableTeacherListViewAdapter;
import com.ourwork.schoolmanagement.singleton.request.admin.TeacherListRequest;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.singleton.response.admin.TeacherListResponseData;
import com.ourwork.schoolmanagement.singleton.response.admin.TeacherNode;
import com.ourwork.schoolmanagement.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ourwork.schoolmanagement.MyApplication.apiCall;

/**
 * Created by Purvik Rana on 12-07-2018.
 */
public class TeacherListActivity extends AppCompatActivity {


    private static final String TAG = "tList";
    StudentParentResp studentParentResp;
    private ProgressDialog pDialog;
    ExpandableListView explv;
    private Toolbar toolbar;
    private AdView mAdView;
//    private TextView tvEmptyList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        //tvEmptyList = findViewById(R.id.emptyTextView);
        explv = findViewById(R.id.explv);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List Of Teachers");
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: " + getSupportActionBar());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Load Ads
        mAdView = findViewById(R.id.adView);
//        mAdView.setVisibility(View.GONE);
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getResources().getString(R.string.ads_test_device_id)).build();


        if(getIntent() != null)
            studentParentResp = (StudentParentResp) getIntent().getExtras().getSerializable("loginResponse");




        pDialog = new ProgressDialog(this);
        pDialog.setMessage("loading teacher list...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        TeacherListRequest teacherListRequest = new TeacherListRequest();
        teacherListRequest.setSchoolId(studentParentResp.getSchoolId());

        Call<TeacherListResponseData> call = apiCall.teacher_list(teacherListRequest);
        call.enqueue(new Callback<TeacherListResponseData>() {
            @Override
            public void onResponse(Call<TeacherListResponseData> call, Response<TeacherListResponseData> response) {

                Log.e(TAG, "onResponse: " + response.code() );
                Log.e(TAG, "onResponse: "+ response.body().getData().getTeachers().size());


                if (response.code() == AppConstant.RESPONSE_CODE_OK) {

                    List<TeacherNode> teacherNodeList = response.body().getData().getTeachers();

                    ExpandableTeacherListViewAdapter expandableTeacherListViewAdapter = new ExpandableTeacherListViewAdapter(TeacherListActivity.this, teacherNodeList);

                    explv.setAdapter(expandableTeacherListViewAdapter);

                    if (pDialog.isShowing())
                        pDialog.dismiss();

                } else {

                    explv.setVisibility(View.GONE);
                    /*tvEmptyList.setText(getString(R.string.empty_listview_message));
                    tvEmptyList.setVisibility(View.VISIBLE);*/
                }
                
            }

            @Override
            public void onFailure(Call<TeacherListResponseData> call, Throwable t) {

                explv.setVisibility(View.GONE);
                /*tvEmptyList.setText(getString(R.string.error_server));
                tvEmptyList.setVisibility(View.VISIBLE);*/

                Toast.makeText(getApplicationContext(), "" + AppConstant.API_RESPONSE_FAILURE, Toast.LENGTH_LONG).show();

                if (pDialog.isShowing())
                    pDialog.dismiss();

                Log.e(TAG, "onFailure: "+ t.getMessage() );

            }
        });



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
}
