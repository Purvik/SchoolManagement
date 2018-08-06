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
import android.widget.Toast;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.teacher.TeacherSyllabusNode;

import java.util.List;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class TeacherSyllabusAdapter extends RecyclerView.Adapter<TeacherSyllabusAdapter.ViewHolder> {

    private static final String TAG = TeacherSyllabusAdapter.class.getName();
    private List<TeacherSyllabusNode> syllabusNodeList ;
    private Context mContext;
    int lastPosition = -1;

    public TeacherSyllabusAdapter(List<TeacherSyllabusNode> syllabusNodeList, Context mContext) {
        this.syllabusNodeList = syllabusNodeList;

        this.mContext = mContext;

        Log.d(TAG, "SyllabusAdapter: No Of Items" + syllabusNodeList.size());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView syllabusTitle;
        TextView syllabusDesc;
        TextView syllabusClassName;
        TextView syllabusSubName;
        Button btnFileDownload;
        CardView mainCardView;


        ViewHolder(View v){
            super(v);
            mainCardView = v.findViewById(R.id.mainCardView);
            syllabusTitle= v.findViewById(R.id.tv_syllabus_title);
            syllabusDesc= v.findViewById(R.id.tv_syllabus_desc);
            syllabusClassName = v.findViewById(R.id.tv_class_name);
            syllabusSubName = v.findViewById(R.id.tv_subject_name);
            btnFileDownload = v.findViewById(R.id.btnFileDownload);
        }
    }

    @Override
    public TeacherSyllabusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_syllabus_teacher, parent, false);
        TeacherSyllabusAdapter.ViewHolder vh = new TeacherSyllabusAdapter.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(TeacherSyllabusAdapter.ViewHolder holder, int position) {

        TeacherSyllabusNode  syllabusNode = syllabusNodeList.get(position);

       // Log.d(TAG, singleTopic.getTopic() + " : onBindViewHolder: " + singleTopic.getDesc());

        holder.syllabusTitle.setText(syllabusNode.getTitle());
        holder.syllabusDesc.setText(syllabusNode.getDescription());
        holder.syllabusSubName.setText(syllabusNode.getSubject());
        holder.syllabusClassName.setText(syllabusNode.getClassName());

        if (syllabusNode.getFile().length() != 0) {

            holder.btnFileDownload.setTextColor(mContext.getResources().getColor(R.color.green));
            holder.btnFileDownload.setClickable(true);
            holder.btnFileDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"DOWNLOAD FILE FUNCTION" , Toast.LENGTH_LONG).show();
                }
            });
        }else{
            holder.btnFileDownload.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.btnFileDownload.setClickable(false);
        }


        //holder.mainCardView.animate().alpha(1.0f).setDuration(500);

    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + syllabusNodeList.size());
        return syllabusNodeList.size();
    }


}
