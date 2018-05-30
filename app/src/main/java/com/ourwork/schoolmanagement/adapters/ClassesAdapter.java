package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.SingleClass;
import com.ourwork.schoolmanagement.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Purvik Rana on 30-05-2018.
 */

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {

    private static final String TAG = ClassesAdapter.class.getName();
    private ArrayList<SingleClass> passedDataset, useDataset;
    private int day;
    private Context mContext;

    public ClassesAdapter(ArrayList<SingleClass> dataset, int day, Context mContext) {
        this.passedDataset = dataset;
        useDataset = new ArrayList<>();
        (new TimeUtils()).setTimes();

        for (int i =0; i < dataset.size(); i++) {

            SingleClass temp = dataset.get(i);
            if (temp.getDay() == day) {
                useDataset.add(temp);
            }
        }


        this.day = day;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView startTime;
        TextView endTime;
        TextView name;
        TextView prof;
        TextView place;

        ViewHolder(View v){
            super(v);
            startTime= v.findViewById(R.id.startTime);
            endTime= v.findViewById(R.id.endTime);
            name= v.findViewById(R.id.name);
            prof= v.findViewById(R.id.prof);
            place= v.findViewById(R.id.place);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SingleClass tempSingleClass = useDataset.get(position);
        holder.name.setText(tempSingleClass.getName());
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
        }

    }

    @Override
    public int getItemCount() {
        return useDataset.size();
    }

    /*private static class TimeUtils{

        private static final String TAG = ClassesAdapter.class.getName();
        static HashMap<Integer,String> times = new HashMap<>();

        public HashMap<Integer, String> getTimes() {
            return times;
        }

        public void setTimes() {
            times.put(0, "12 AM");
            times.put(1, "1 AM");
            times.put(2, "2 AM");
            times.put(3, "3 AM");
            times.put(4, "4 AM");
            times.put(5, "5 AM");
            times.put(6, "6 AM");
            times.put(7, "7 AM");
            times.put(8, "8 AM");
            times.put(9, "9 AM");
            times.put(10, "10 AM");
            times.put(11, "11 AM");
            times.put(12, "12 PM");
            times.put(13, "1 PM");
            times.put(14, "2 PM");
            times.put(15, "3 PM");
            times.put(16, "4 PM");
            times.put(17, "5 PM");
            times.put(18, "6 PM");
            times.put(19, "7 PM");
            times.put(20, "8 PM");
            times.put(21, "9 PM");
            times.put(22, "10 PM");
            times.put(23, "11 PM");
        }

        public static String getTime(int time){

            if (time >= 0 && time <= 23) {

                Log.d(TAG, time +" int || getTime: " + times.get(time));
                return times.get(time);
            }else{
                return "?";
            }

        }
    }*/


}
