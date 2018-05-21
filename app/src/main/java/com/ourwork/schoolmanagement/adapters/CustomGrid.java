package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;

/**
 * Created by Purvik Rana on 19-05-2018.
 */

public class CustomGrid extends BaseAdapter {

    private Context mContext;
    private final String[] titles;
    private final int[] Imageids;

    public CustomGrid(Context mContext, String[] titles, int[] imageids) {
        this.mContext = mContext;
        this.titles = titles;
        Imageids = imageids;
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single_item, null);
            TextView textView = grid.findViewById(R.id.grid_text);
            ImageView imageView = grid.findViewById(R.id.grid_image);
            textView.setText(titles[position]);
            imageView.setImageResource(Imageids[position]);

        } else {
            grid = convertView;
        }

        return grid;
    }
}
