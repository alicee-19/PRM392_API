package com.anhngb.lab11.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.anhngb.lab11.ui.main.MainActivity;
import com.anhngb.lab11.R;
import com.anhngb.lab11.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();
    private final OnStudentActionListener actionListener;

    // Constructor with action listener
    public StudentListAdapter(OnStudentActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void updateList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bindView(studentList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        TextView tvName, tvEmail, tvPhone, tvGender;
        ImageButton imageButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvGender = itemView.findViewById(R.id.tvGender);
            imageButton = itemView.findViewById(R.id.imageButton);

            imageButton.setOnClickListener(this);
        }

        void bindView(Student student) {
            tvName.setText(student.getName());
            tvEmail.setText(student.getEmail());
            tvPhone.setText(student.getPhone());
            tvGender.setText(student.getGender());
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Student student = studentList.get(position);
                int itemId = item.getItemId();
                if (itemId == R.id.action_popup_edit) {
                    actionListener.onEdit(student);
                    return true;
                } else if (itemId == R.id.action_popup_delete) {
                    actionListener.onDelete(student);
                    return true;
                }
            }
            return false;
        }
    }

    // Interface for actions
    public interface OnStudentActionListener {
        void onEdit(Student student);
        void onDelete(Student student);
    }
}
