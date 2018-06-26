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
import com.ourwork.schoolmanagement.activities.AttendanceActivity;
import com.ourwork.schoolmanagement.activities.ExamScheduleActivity;
import com.ourwork.schoolmanagement.activities.GalleryActivity;
import com.ourwork.schoolmanagement.activities.HomeworkActivity;
import com.ourwork.schoolmanagement.activities.MainActivity;
import com.ourwork.schoolmanagement.activities.MarksActivity;
import com.ourwork.schoolmanagement.activities.NoticeActivity;
import com.ourwork.schoolmanagement.activities.SyllabusActivity;
import com.ourwork.schoolmanagement.activities.TimeTableActivity;
import com.ourwork.schoolmanagement.singleton.response.LoginResponse;
import com.ourwork.schoolmanagement.utils.AppConstant;
import com.ourwork.schoolmanagement.utils.SquareCardView;

/**
 * Created by Purvik Rana on 24-05-2018.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private String[] mTitles;
    private TypedArray mImgIds;
    private Context mContext;
    private LoginResponse mLoginResponse;

    public HomeMenuAdapter(String[] mTitles, TypedArray mImgIds, Context mContext, LoginResponse mLoginResponse) {
        this.mTitles = mTitles;
        this.mImgIds = mImgIds;
        this.mContext = mContext;
        this.mLoginResponse = mLoginResponse;
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
        holder.mImageView.setOnClickListener(new ClickListener(mTitles[position]));

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


                case "Syllabus":

                    intent = new Intent(mContext, SyllabusActivity.class);
                    intent.putExtra("loginResponse", mLoginResponse);
                    mContext.startActivity(intent);

                    break;

                case "Time Table":

                    intent = new Intent(mContext, TimeTableActivity.class);
                    mContext.startActivity(intent);

                    break;

                case "Home Work":

                    intent = new Intent(mContext, HomeworkActivity.class);
                    mContext.startActivity(intent);

                    break;

                case "Assignments":

                    intent = new Intent(mContext, AssignmentActivity.class);
                    intent.putExtra("loginResponse", mLoginResponse);
                    mContext.startActivity(intent);

                    break;

                case "Attendance":

                    intent = new Intent(mContext, AttendanceActivity.class);
                    intent.putExtra("loginResponse", mLoginResponse);
                    mContext.startActivity(intent);

                    break;

                case "Exam Schedule":

                    intent = new Intent(mContext, ExamScheduleActivity.class);
                    intent.putExtra("loginResponse", mLoginResponse);
                    mContext.startActivity(intent);

                    break;


                case "Results":

                    intent = new Intent(mContext, MarksActivity.class);
                    mContext.startActivity(intent);

                    break;


                case "Notifications":

                    intent = new Intent(mContext, NoticeActivity.class);
                    mContext.startActivity(intent);

                    break;

                case "Gallery":

                    /*intent = new Intent(mContext, GalleryActivity.class);
                    mContext.startActivity(intent);*/


                    Toast.makeText(mContext, ""+ AppConstant.APP_NOT_DEVELOPED_YET, Toast.LENGTH_LONG).show();

                    break;


            }


        }
    }
}
