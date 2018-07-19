package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.admin.TeacherNode;

import java.util.List;

/**
 * Created by Purvik Rana on 12-07-2018.
 */
public class ExpandableTeacherListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<TeacherNode> teacherNodeList;

    public ExpandableTeacherListViewAdapter(Context context, List<TeacherNode> teacherNodeList) {
        this.context = context;
        this.teacherNodeList = teacherNodeList;
    }


    @Override
    public int getGroupCount() {
        return teacherNodeList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.teacherNodeList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        TeacherNode teacherNode = teacherNodeList.get(groupPosition);
        if (view == null) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.explv_header, null);
        }

        TextView lblListHeader = view.findViewById(R.id.explvHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(teacherNode.getName());

        SwitchCompat activeStatus = view.findViewById(R.id.switchActiveStatus);
        activeStatus.setClickable(false);
        int intActiveStatus = Integer.parseInt(teacherNode.getActive());
        if (intActiveStatus == 1) {
            activeStatus.setChecked(true);
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {


        TeacherNode teacherNode = this.teacherNodeList.get(groupPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.explv_teacher_details, null);
        }

        TextView tvDOJ =view.findViewById(R.id.tvDOJ);
        TextView tvDesignation =view.findViewById(R.id.tvDesignation);
        TextView tvPhone =view.findViewById(R.id.tvPhone);
        TextView tvEmail =view.findViewById(R.id.tvEmail);
        View activeView = view.findViewById(R.id.activeView);

        tvDOJ.setText(teacherNode.getJod());
        tvDesignation.setText(teacherNode.getDesignation());
        tvPhone.setText(teacherNode.getPhone());
        tvEmail.setText(teacherNode.getEmail());

        int intActiveStatus = Integer.parseInt(teacherNode.getActive());
        if (intActiveStatus == 1) {
            activeView.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else{
            activeView.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
        return view;


    }


    @Override
    public int getChildrenCount(int i) {
        return 1;
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.teacherNodeList.get(groupPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
