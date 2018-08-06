package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.AssignmentActivity;
import com.ourwork.schoolmanagement.activities.HomeworkActivity;
import com.ourwork.schoolmanagement.activities.NoticeBoardActivity;
import com.ourwork.schoolmanagement.activities.SyllabusActivity;
import com.ourwork.schoolmanagement.activities.TimeTableActivity;
import com.ourwork.schoolmanagement.activities.admin.AdminAssignmentListActivity;
import com.ourwork.schoolmanagement.activities.admin.AdminAttendanceActivity;
import com.ourwork.schoolmanagement.activities.admin.StudentListActivity;
import com.ourwork.schoolmanagement.activities.admin.SubjectListActivity;
import com.ourwork.schoolmanagement.activities.admin.TeacherListActivity;
import com.ourwork.schoolmanagement.activities.teacher.TeacherAttendanceActivity;
import com.ourwork.schoolmanagement.singleton.response.StudentParentResp;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.ourwork.schoolmanagement.utils.SquareCardView;

/**
 * Created by Purvik Rana on 24-05-2018.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private String[] mTitles;
    private TypedArray mImgIds;
    private Context mContext;
    private StudentParentResp mStudentParentResp;

    public HomeMenuAdapter(String[] mTitles, TypedArray mImgIds, Context mContext, StudentParentResp mStudentParentResp) {
        this.mTitles = mTitles;
        this.mImgIds = mImgIds;
        this.mContext = mContext;
        this.mStudentParentResp = mStudentParentResp;
    }

    @Override
    public HomeMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.grid_single_item_new, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mImageView.setImageDrawable(mImgIds.getDrawable(position));
        holder.mTextView.setText(mTitles[position]);
        holder.itemView.setOnClickListener(new ClickListener(mTitles[position]));

        //holder.mainCardView.animate().alpha(1.0f).setDuration(250);

    }

    @Override
    public int getItemCount() {
        Log.d("Adapter", "getItemCount: " + mTitles.length);
        return mTitles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
        public SquareCardView mainCardView;

        public ViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.grid_image);
            mTextView = v.findViewById(R.id.grid_text);
            mainCardView = v.findViewById(R.id.mainCardView);

        }
    }

    public class ClickListener implements View.OnClickListener {

        String title;

        public ClickListener(String title) {
            this.title = title;
        }

        @Override
        public void onClick(View view) {

            Intent intent;

            switch (title) {

                case "Teachers":

                    if (mStudentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                        intent = new Intent(mContext, TeacherListActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);

                    }else{
                        Toast.makeText(mContext, "" + AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();
                    }

                    break;

                case "Students":

                    if (mStudentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                        intent = new Intent(mContext, StudentListActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);

                    }else{
                        Toast.makeText(mContext, "" + AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();
                    }


                    break;

                case "Subjects":

                    if (mStudentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                        intent = new Intent(mContext, SubjectListActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);

//                        Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(mContext, "" + AppConstant.APP_USER_NOT_ACCESS, Toast.LENGTH_SHORT).show();
                    }


                    break;


                case "Syllabus":

                    intent = new Intent(mContext, SyllabusActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);

                    break;

                case "Time Table":

                    intent = new Intent(mContext, TimeTableActivity.class);
                    mContext.startActivity(intent);

                    break;

                case "Home Work":

                    intent = new Intent(mContext, HomeworkActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);

                    break;

                case "Assignments":

                    if (mStudentParentResp.getUsertype() . equalsIgnoreCase( "admin")) {
                        intent = new Intent(mContext, AdminAssignmentListActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);
                    }else {
                        intent = new Intent(mContext, AssignmentActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);
                    }
                    break;

                case "Attendance":


                    if (mStudentParentResp.getUsertype().equalsIgnoreCase("student")) {

                        //Student Login
                        /*intent = new Intent(mContext, StudentAttendanceActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);*/

                        Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    } else if (mStudentParentResp.getUsertype().equalsIgnoreCase("teacher")) {

                        //Teacher Login
                        intent = new Intent(mContext, TeacherAttendanceActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);

                        //Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    } else if (mStudentParentResp.getUsertype().equalsIgnoreCase("admin")) {

                        //Teacher Login
                        intent = new Intent(mContext, AdminAttendanceActivity.class);
                        intent.putExtra("loginResponse", mStudentParentResp);
                        mContext.startActivity(intent);

                        //Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    }else {

                        //Admin Login
                        Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    }


                    break;

                case "Behaviour":

                    /*intent = new Intent(mContext, ExamScheduleActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;

                case "Parents Meeting":

                    /*intent = new Intent(mContext, ExamScheduleActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;

                case "Notice Board":

                    intent = new Intent(mContext, NoticeBoardActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);

                    //Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;


                case "Exam Schedule":

                    /*intent = new Intent(mContext, ExamScheduleActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;


                case "Results":

                    /*intent = new Intent(mContext, MarksActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;

                case "Fees Payment":

                    /*intent = new Intent(mContext, MarksActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;


                case "Notifications":

                    /*intent = new Intent(mContext, NoticeBoardActivity.class);
                    intent.putExtra("loginResponse", mStudentParentResp);
                    mContext.startActivity(intent);*/

                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;

                case "Gallery":

                    /*intent = new Intent(mContext, GalleryActivity.class);
                    mContext.startActivity(intent);*/


                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;

                case "Chat":

                    /*intent = new Intent(mContext, GalleryActivity.class);
                    mContext.startActivity(intent);*/


                    Toast.makeText(mContext, "" + AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_SHORT).show();

                    break;


            }


        }
    }
}
