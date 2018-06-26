package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.student.SyllabusNode;

import java.util.List;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.ViewHolder> {

    private static final String TAG = SyllabusAdapter.class.getName();
    private List<SyllabusNode> syllabusNodeList ;
    private Context mContext;
    int lastPosition = -1;

    public SyllabusAdapter(List<SyllabusNode> syllabusNodeList, Context mContext) {
        this.syllabusNodeList = syllabusNodeList;

        this.mContext = mContext;

        Log.d(TAG, "SyllabusAdapter: No Of Items" + syllabusNodeList.size());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView syllabusTitle;
        TextView syllabusDesc;
        TextView syllabusDate;
        TextView syllabusUploader;
        Button btnFileDownload;
        CardView mainCardView;


        ViewHolder(View v){
            super(v);
            mainCardView = v.findViewById(R.id.mainCardView);
            syllabusTitle= v.findViewById(R.id.tv_syllabus_title);
            syllabusDesc= v.findViewById(R.id.tv_syllabus_desc);
            syllabusDate = v.findViewById(R.id.tv_syllabus_date);
            syllabusUploader = v.findViewById(R.id.tv_syllabus_uploader);
            btnFileDownload = v.findViewById(R.id.btnFileDownload);
        }
    }

    @Override
    public SyllabusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_syllabus, parent, false);
        SyllabusAdapter.ViewHolder vh = new SyllabusAdapter.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(SyllabusAdapter.ViewHolder holder, int position) {

        SyllabusNode  syllabusNode = syllabusNodeList.get(position);

       // Log.d(TAG, singleTopic.getTopic() + " : onBindViewHolder: " + singleTopic.getDesc());

        holder.syllabusTitle.setText(syllabusNode.getTitle());
        holder.syllabusDesc.setText(syllabusNode.getDescription());
        holder.syllabusDate.setText(syllabusNode.getDate());
        holder.syllabusUploader.setText(syllabusNode.getUsertypeID());

        //holder.mainCardView.animate().alpha(1.0f).setDuration(500);

    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + syllabusNodeList.size());
        return syllabusNodeList.size();
    }


}
