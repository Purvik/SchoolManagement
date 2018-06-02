package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;

/**
 * Created by Purvik Rana on 01-06-2018.
 */

public class AttendanceDataAdapter extends RecyclerView.Adapter<AttendanceDataAdapter.ViewHolder> {

    Context mContext;
    String[] array;

    public AttendanceDataAdapter(Context mContext, String[] array) {
        this.mContext = mContext;
        this.array = array;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textview);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_attendance_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(array[position]);
    }



    @Override
    public int getItemCount() {
        return array.length;
    }



}
