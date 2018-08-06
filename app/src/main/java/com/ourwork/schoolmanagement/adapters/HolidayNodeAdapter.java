package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.HolidayNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class HolidayNodeAdapter extends RecyclerView.Adapter<HolidayNodeAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HolidayNode> holidayNodesList;

    public HolidayNodeAdapter(Context mContext, ArrayList<HolidayNode> holidayNodesList) {
        this.mContext = mContext;
        this.holidayNodesList = holidayNodesList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, notice, fromDate, toDate;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_notice_title);
            notice = v.findViewById(R.id.tv_notice_details);
            fromDate = v.findViewById(R.id.tv_notice_from_date);
            toDate = v.findViewById(R.id.tv_notice_to_date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_holiday, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HolidayNode holidayNode= holidayNodesList.get(position);

        holder.title.setText(holidayNode.getTitle());
        holder.notice.setText(holidayNode.getDetails());
        holder.fromDate.setText(holidayNode.getFdate());
        holder.toDate.setText(holidayNode.getTdate());
    }

    @Override
    public int getItemCount() {
        return holidayNodesList.size();
    }


}
