package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.SingleSubjectDetails;
import com.ourwork.schoolmanagement.singleton.SingleTopic;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private static final String TAG = TopicsAdapter.class.getName();
    private ArrayList<SingleSubjectDetails> passedTopicList;
    private ArrayList<SingleTopic> topicArrayList;
    //private SingleSubjectDetails singleSubjectDetails;
    private int subject;
    private Context mContext;

    public TopicsAdapter(ArrayList<SingleTopic> passedTopicList, int subject, Context mContext) {
        this.topicArrayList = passedTopicList;
        this.subject = subject;
        this.mContext = mContext;
        //this.singleSubjectDetails = passedTopicList.get(subject);
        //this.topicArrayList = singleSubjectDetails.getTopiclist();

        //Log.d(TAG, "TopicsAdapter: " + singleSubjectDetails.getSubname());
        Log.d(TAG, "TopicsAdapter: " + topicArrayList.toString() );

        //(new TimeUtils()).setTimes();

        /*for (int i =0; i < dataset.size(); i++) {

            SingleClass temp = dataset.get(i);
            if (temp.getDay() == day) {
                useDataset.add(temp);
            }
        }*/



    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView topicTitle;
        TextView topicDesc;


        ViewHolder(View v){
            super(v);
            topicTitle= v.findViewById(R.id.tv_topic_title);
            topicDesc= v.findViewById(R.id.tv_topic_desc);
        }
    }

    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_syllabus, parent, false);
        TopicsAdapter.ViewHolder vh = new TopicsAdapter.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(TopicsAdapter.ViewHolder holder, int position) {

        SingleTopic singleTopic = topicArrayList.get(position);

        Log.d(TAG, singleTopic.getTopic() + " : onBindViewHolder: " + singleTopic.getDesc());

        holder.topicTitle.setText(singleTopic.getTopic());
        holder.topicDesc.setText(singleTopic.getDesc());


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
        Log.d(TAG, "getItemCount: " + topicArrayList.size());
        return topicArrayList.size();
    }


}
