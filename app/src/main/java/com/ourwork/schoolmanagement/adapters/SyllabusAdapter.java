package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
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


        ViewHolder(View v){
            super(v);
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



        /*holder.name.setText(tempSingleClass.getName());
        holder.prof.setText(tempSingleClass.getProf());
        holder.place.setText(tempSingleClass.getPlace());

        //holder.startTime.setText(tempSingleClass.getStartTime());
        //holder.endTime.setText(tempSingleClass.getEndTime());

        holder.startTime.setText(TimeUtils.getTime(tempSingleClass.getStartTime()));
        holder.endTime.setText(TimeUtils.getTime(tempSingleClass.getEndTime()));

        Log.d(TAG, "onBindViewHolder: " + tempSingleClass.getLength());


        if (tempSingleClass.getLength() > 1) {

            holder.itemView.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (mContext.getResources().getDimensionPixelSize(R.dimen.class_item_height)) * 2));

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.itemView.getLayoutParams();
            params.setMargins(0,0,0,16);

            holder.itemView.setLayoutParams(params);
        }*/

    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + syllabusNodeList.size());
        return syllabusNodeList.size();
    }


}
