package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;

/**
 * Created by Purvik Rana on 24-05-2018.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private String[] mTitles;
    private TypedArray mImgIds;
    private Context mContext;

    public HomeMenuAdapter(String[] mTitles, TypedArray mImgIds, Context mContext) {
        this.mTitles = mTitles;
        this.mImgIds = mImgIds;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            mImageView = v.findViewById(R.id.grid_image);
            mTextView = v.findViewById(R.id.grid_text);

        }
    }

    @Override
    public HomeMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.grid_single_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mImageView.setImageDrawable(mImgIds.getDrawable(position));

        holder.mTextView.setText(mTitles[position]);



    }

    @Override
    public int getItemCount() {
        Log.d("Adapter", "getItemCount: " + mTitles.length);
        return mTitles.length;
    }
}
