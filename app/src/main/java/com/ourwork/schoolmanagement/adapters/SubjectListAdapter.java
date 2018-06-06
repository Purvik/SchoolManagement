package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.InnerMarkNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 06-06-2018.
 *
 */

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<InnerMarkNode> innerMarkNodeArrayList;


    public SubjectListAdapter(Context mContext, ArrayList<InnerMarkNode> innerMarkNodeArrayList) {
        this.mContext = mContext;
        this.innerMarkNodeArrayList = innerMarkNodeArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView subjectName, marks, obtainedMarks, highestMarks;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectname);
            marks = itemView.findViewById(R.id.marks);
            obtainedMarks = itemView.findViewById(R.id.obtainedMarks);
            highestMarks = itemView.findViewById(R.id.highestMarks);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_subject_mark, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        InnerMarkNode innerMarkNode = innerMarkNodeArrayList.get(position);
        holder.subjectName.setText(innerMarkNode.getSubjectName());
        holder.marks.setText(String.valueOf(innerMarkNode.getMarks()));
        holder.obtainedMarks.setText(String.valueOf(innerMarkNode.getObtainedMarks()));
        holder.highestMarks.setText(String.valueOf(innerMarkNode.getHighestMarks()));

    }

    @Override
    public int getItemCount() {
        return innerMarkNodeArrayList.size();
    }


}
