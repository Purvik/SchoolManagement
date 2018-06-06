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

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.activities.AssignmentActivity;
import com.ourwork.schoolmanagement.activities.AttendanceActivity;
import com.ourwork.schoolmanagement.activities.HomeworkActivity;
import com.ourwork.schoolmanagement.activities.MainActivity;
import com.ourwork.schoolmanagement.activities.SyllabusActivity;
import com.ourwork.schoolmanagement.activities.TimeTableActivity;

/**
 * Created by Purvik Rana on 24-05-2018.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private String[] mTitles;
    private TypedArray mImgIds;
    private Context mContext;

    public HomeMenuAdapter(String[] mTitles, TypedArray mImgIds, Context mContext) {
        this.mTitles = mTitles;
        this.mImgIds = mImgIds;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.grid_image);
            mTextView = v.findViewById(R.id.grid_text);

        }
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

    }

    @Override
    public int getItemCount() {
        Log.d("Adapter", "getItemCount: " + mTitles.length);
        return mTitles.length;
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
                    mContext.startActivity(intent);

                    break;

                case "Attendance":

                    intent = new Intent(mContext, AttendanceActivity.class);
                    mContext.startActivity(intent);

                    break;


            }


        }
    }
}
