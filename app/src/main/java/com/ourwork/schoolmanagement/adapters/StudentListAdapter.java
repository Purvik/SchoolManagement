package com.ourwork.schoolmanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.singleton.response.teacher.StudentListNode;

import java.util.List;

/**
 * Created by Purvik Rana on 21-07-2018.
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private Context mContext;
    private List<StudentListNode> studentListNodes;

    public StudentListAdapter(Context mContext, List<StudentListNode> studentListNodes) {
        this.mContext = mContext;
        this.studentListNodes = studentListNodes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView idStudent, nameStudent;
        Button btnMarkAttendance;

        public ViewHolder(View v) {
            super(v);

            idStudent = v.findViewById(R.id.tvStudentId);
            nameStudent = v.findViewById(R.id.tvStudentName);
            btnMarkAttendance = v.findViewById(R.id.btnMarkAttendance);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_student_list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StudentListNode studentListNode = studentListNodes.get(position);

        holder.idStudent.setText(studentListNode.getStudentID());
        holder.nameStudent.setText(studentListNode.getName());
        if(holder.btnMarkAttendance.getVisibility() == View.VISIBLE)
            holder.btnMarkAttendance.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return studentListNodes.size();
    }


}
