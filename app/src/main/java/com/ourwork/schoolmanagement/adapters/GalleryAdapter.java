package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ourwork.schoolmanagement.R;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 31-05-2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<Integer> imageList;
    private Context mContext;
    private onItemClickListner listner;

    public GalleryAdapter(ArrayList<Integer> imageList, Context mContext, onItemClickListner listner) {
        this.imageList = imageList;
        this.mContext = mContext;
        this.listner = listner;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_gallery_image, parent, false);
        GalleryAdapter.ViewHolder vh = new GalleryAdapter.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, final int position) {
        holder.img_thumb.setImageResource(imageList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public interface onItemClickListner {
        public void OnItemClick(int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumb;

        ViewHolder(View v) {
            super(v);
            img_thumb = v.findViewById(R.id.img_thumb);
        }
    }

}
