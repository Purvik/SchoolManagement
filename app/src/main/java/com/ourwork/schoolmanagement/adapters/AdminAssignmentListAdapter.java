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
import com.ourwork.schoolmanagement.singleton.response.admin.AdminAssignmentListNode;

import java.util.List;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class AdminAssignmentListAdapter extends RecyclerView.Adapter<AdminAssignmentListAdapter.ViewHolder> {

    Context mContext;
    List<AdminAssignmentListNode> assignmentNodeList;

    public AdminAssignmentListAdapter(Context mContext, List<AdminAssignmentListNode> assignmentNodeList) {
        this.mContext = mContext;
        this.assignmentNodeList = assignmentNodeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mainCardView;
        TextView title, desc, uploader, deadline, subject;
        Button btnDownload;

        public ViewHolder(View v) {
            super(v);
            mainCardView = v.findViewById(R.id.mainCardView);
            title = v.findViewById(R.id.title);
            subject = v.findViewById(R.id.subject);
            desc = v.findViewById(R.id.desc);
            uploader = v.findViewById(R.id.uploader);
            deadline = v.findViewById(R.id.deadline);
            btnDownload = v.findViewById(R.id.btnDownload);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_assignment, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AdminAssignmentListNode assignmentNode = assignmentNodeList.get(position);

        holder.title.setText(assignmentNode.getTitle());
        holder.subject.setText(assignmentNode.getSubject());
        holder.desc.setText(assignmentNode.getDescription());
        holder.uploader.setText(assignmentNode.getTeacherName());
        holder.deadline.setText(assignmentNode.getDeadlinedate());

        if (assignmentNode.getFile().length() != 0) {
            holder.btnDownload.setText(mContext.getResources().getString(R.string.btn_label_download));
            holder.btnDownload.setTextColor(mContext.getResources().getColor(R.color.green));
            holder.btnDownload.setClickable(true);
            holder.btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"DOWNLOAD FILE FUNCTION" , Toast.LENGTH_LONG).show();
                }
            });
        }else{
            holder.btnDownload.setText(mContext.getResources().getString(R.string.btn_label_no_download));
            holder.btnDownload.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.btnDownload.setClickable(false);
        }

        //holder.mainCardView.animate().alpha(1.0f).setDuration(500);
    }

    @Override
    public int getItemCount() {
        return assignmentNodeList.size();
    }


}
