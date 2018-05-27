package com.ourwork.schoolmanagement.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 25-05-2018.
 */

public class ProfileListAdapter extends BaseAdapter {

    private Activity activity;
    //private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private static ArrayList title,values;
    private static LayoutInflater inflater = null;

    public ProfileListAdapter(Activity a, ArrayList titleArray, ArrayList valueArray) {
        activity = a;
        title = titleArray;
        values = valueArray;

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return title.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            v = inflater.inflate(R.layout.profile_list_view_item, null);
        }else{
            v = convertView;
        }

        TextView titleText = v.findViewById(R.id.titleText); // title
        titleText.setText(title.get(position).toString());

        TextView valueText = v.findViewById(R.id.valueText); // title
        valueText.setText(values.get(position).toString());

        return v;

    }

}
