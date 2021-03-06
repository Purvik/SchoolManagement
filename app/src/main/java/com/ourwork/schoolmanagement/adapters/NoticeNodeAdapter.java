package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.NoticeNode;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 05-06-2018.
 */

public class NoticeNodeAdapter extends RecyclerView.Adapter<NoticeNodeAdapter.ViewHolder> {

    Context mContext;
    ArrayList<NoticeNode> noticeNodeArrayList;

    public NoticeNodeAdapter(Context mContext, ArrayList<NoticeNode> noticeNodeArrayList) {
        this.mContext = mContext;
        this.noticeNodeArrayList = noticeNodeArrayList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, notice, date;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_notice_title);
            notice = v.findViewById(R.id.tv_notice_details);
            date = v.findViewById(R.id.tv_notice_date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notice, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NoticeNode noticeNode= noticeNodeArrayList.get(position);

        holder.title.setText(noticeNode.getTitle());
        holder.notice.setText(noticeNode.getNotice());
        holder.date.setText(noticeNode.getDate());
    }

    @Override
    public int getItemCount() {
        return noticeNodeArrayList.size();
    }


}
