package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.EventNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class EventNodeAdapter extends RecyclerView.Adapter<EventNodeAdapter.ViewHolder> {

    Context mContext;
    ArrayList<EventNode> eventNodeArrayList;

    public EventNodeAdapter(Context mContext, ArrayList<EventNode> eventNodeArrayList) {
        this.mContext = mContext;
        this.eventNodeArrayList = eventNodeArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, notice, fromdate, fromTime, todate, toTime;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_notice_title);
            notice = v.findViewById(R.id.tv_notice_details);
            fromdate = v.findViewById(R.id.tv_notice_from_date);
            fromTime = v.findViewById(R.id.tv_notice_from_time);
            todate = v.findViewById(R.id.tv_notice_to_date);
            toTime = v.findViewById(R.id.tv_notice_to_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        EventNode eventNode= eventNodeArrayList.get(position);

        holder.title.setText(eventNode.getTitle());
        holder.notice.setText(eventNode.getDetails());
        holder.fromdate.setText(eventNode.getFdate());
        holder.fromTime.setText(eventNode.getFtime());
        holder.todate.setText(eventNode.getTdate());
        holder.toTime.setText(eventNode.getTtime());
    }

    @Override
    public int getItemCount() {
        return eventNodeArrayList.size();
    }


}
