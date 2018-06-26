package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.HomeWorkNode;
import com.ourwork.schoolmanagement.singleton.response.student.ExamScheduleNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class ExamScheduleAdapter extends RecyclerView.Adapter<ExamScheduleAdapter.ViewHolder> {

    Context mContext;
    List<ExamScheduleNode> examScheduleNodeList;

    public ExamScheduleAdapter(Context mContext, List<ExamScheduleNode> examScheduleNodeList) {
        this.mContext = mContext;
        this.examScheduleNodeList = examScheduleNodeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mainCardView;
        TextView examName, classname, subject, date, time, roomno;


        public ViewHolder(View v) {
            super(v);

            mainCardView = v.findViewById(R.id.mainCardView);
            examName= v.findViewById(R.id.exam_name);
            classname = v.findViewById(R.id.classname);
            subject = v.findViewById(R.id.subject);
            date = v.findViewById(R.id.date);
            time = v.findViewById(R.id.time);
            roomno = v.findViewById(R.id.roomno);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_exam_schedule, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ExamScheduleNode examScheduleNode = examScheduleNodeList.get(position);

        holder.examName.setText(examScheduleNode.getExam());
        holder.classname.setText(examScheduleNode.getClasses());
        holder.subject.setText(examScheduleNode.getSubject());
        holder.date.setText(examScheduleNode.getEdate());
        holder.time.setText(examScheduleNode.getExamfrom() + " - " + examScheduleNode.getExamto());
        holder.roomno.setText(examScheduleNode.getRoom());

        //holder.mainCardView.animate().alpha(1.0f).setDuration(500);

    }

    @Override
    public int getItemCount() {
        return examScheduleNodeList.size();
    }


}
