package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.HomeWorkNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HomeWorkNode> homeWorkNodeArrayList;

    public HomeworkAdapter(Context mContext, ArrayList<HomeWorkNode> homeWorkNodeArrayList) {
        this.mContext = mContext;
        this.homeWorkNodeArrayList = homeWorkNodeArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, uploader, deadline;
        Button btnDownload;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            desc = v.findViewById(R.id.desc);
            uploader = v.findViewById(R.id.uploader);
            deadline = v.findViewById(R.id.deadline);
            btnDownload = v.findViewById(R.id.btnDownload);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_homework, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeWorkNode homeWorkNode = homeWorkNodeArrayList.get(position);

        holder.title.setText(homeWorkNode.getTitle());
        holder.desc.setText(homeWorkNode.getDesc());
        holder.uploader.setText(homeWorkNode.getUploader());
        holder.deadline.setText(homeWorkNode.getDeadline());

        if (homeWorkNode.isHasAttachment()) {
            holder.btnDownload.setTextColor(mContext.getResources().getColor(R.color.green));
            holder.btnDownload.setClickable(true);
            holder.btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"DOWNLOAD FILE FUNCTION" , Toast.LENGTH_LONG).show();
                }
            });
        }else{
            holder.btnDownload.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.btnDownload.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return homeWorkNodeArrayList.size();
    }


}
