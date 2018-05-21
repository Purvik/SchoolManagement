package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;

import java.lang.reflect.Type;

/**
 * Created by Purvik Rana on 19-05-2018.
 */

public class CustomGrid extends BaseAdapter {

    private static final String TAG = "CustomGrid.java";
    private Context mContext;
    private final String[] titles;
    private final TypedArray Imageids;

    public CustomGrid(Context mContext, String[] titles, TypedArray imageids) {
        this.mContext = mContext;
        this.titles = titles;
        this.Imageids = imageids;
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

            //grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single_item, null);
            TextView textView = grid.findViewById(R.id.grid_text);
            ImageView imageView = grid.findViewById(R.id.grid_image);
            textView.setText(titles[position]);

            Log.d(TAG, "getView: " + Imageids.getString(position));
            imageView.setImageDrawable(Imageids.getDrawable(position));

        } else {
            grid = convertView;
        }

        return grid;
    }
}
