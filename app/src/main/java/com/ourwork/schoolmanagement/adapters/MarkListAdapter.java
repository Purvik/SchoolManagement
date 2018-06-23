package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.InnerMarkNode;
import com.ourwork.schoolmanagement.singleton.MarkNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 06-06-2018.
 */

public class MarkListAdapter extends RecyclerView.Adapter<MarkListAdapter.ViewHolder> {

    private static final String TAG = MarkListAdapter.class.getName();
    Context mContext;
    ArrayList<MarkNode> markNodeArrayList;
    ArrayList<InnerMarkNode> innerMarkNodeArrayList;
    float totalMarks = 0, count = 0, avgMarks = 0;

    public MarkListAdapter(Context mContext, ArrayList<MarkNode> markNodeArrayList) {
        this.mContext = mContext;
        this.markNodeArrayList = markNodeArrayList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView examTitle;
        RecyclerView subRecyclerView;
        TextView totalMarks;
        TextView avgMarks;

        ViewHolder(View v){
            super(v);
            examTitle= v.findViewById(R.id.tv_examTitle);
            subRecyclerView= v.findViewById(R.id.subjectRecyclerView);
            totalMarks = v.findViewById(R.id.total_marks);
            avgMarks = v.findViewById(R.id.avg_marks);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_marks, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MarkNode markNode = markNodeArrayList.get(position);
        count = markNode.getInnderMarkNodeArrayList().size();
        //innerMarkNodeArrayList = markNode.getInnderMarkNodeArrayList();
        Log.d(TAG, "onBindViewHolder: " + count);

        holder.examTitle.setText(markNode.getExamTitle());


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        holder.subRecyclerView.setLayoutManager(mLayoutManager);
        innerMarkNodeArrayList = markNodeArrayList.get(position).getInnderMarkNodeArrayList();

        SubjectListAdapter subjectListAdapter = new SubjectListAdapter(mContext, innerMarkNodeArrayList);
        holder.subRecyclerView.setAdapter(subjectListAdapter);

        count = markNodeArrayList.get(position).getInnderMarkNodeArrayList().size();
        totalMarks = 0;
        avgMarks = 0;

        for (int i =0 ; i < count ; i++) {
            totalMarks += innerMarkNodeArrayList.get(i).getObtainedMarks();
        }

        holder.totalMarks.setText("Total:\t" +  totalMarks /*String.valueOf(markNode.getTotalMarks())*/);
        holder.avgMarks.setText("Avg:\t" + (totalMarks / count)/*String.valueOf(markNode.getAvgMarks())*/);

    }

    @Override
    public int getItemCount() {
        return markNodeArrayList.size();
    }


}
