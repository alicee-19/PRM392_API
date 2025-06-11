package com.anhngb.lab11.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.anhngb.lab11.R;
import com.anhngb.lab11.ui.adapter.StudentListAdapter;
import com.anhngb.lab11.model.Student;
import com.anhngb.lab11.viewmodel.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentListAdapter adapter;
    private FloatingActionButton fabAdd;
    private StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabAdd = findViewById(R.id.action_popup_add);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new StudentListAdapter(new StudentListAdapter.OnStudentActionListener() {
            @Override
            public void onEdit(Student student) {
                showDialog(R.id.action_popup_edit, student);
            }
            @Override
            public void onDelete(Student student) {
                showDialog(R.id.action_popup_delete, student);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        observeData();
        // Fetch student list
        viewModel.fetchStudents();

        fabAdd.setOnClickListener(v -> showDialog(R.id.action_popup_add, (Student) null));
    }

    private void observeData() {
        viewModel.getStudents().observe(this, students -> {
            if (students != null) {
                adapter.updateList(students);
            }
        });
    }

    //Dùng dialog cho các chức năng Thêm, Sửa, Xóa
    private void showDialog(int actionId, Student student) {
        StudentDialog.show(this, actionId, student, new StudentDialog.StudentDialogListener() {
            @Override
            public void onSave(Student student) {
                if (actionId == R.id.action_popup_add) {
                    viewModel.createStudent(student, () -> showToast("Đã thêm"), () -> showToast("Thêm thất bại"));
                } else {
                    viewModel.updateStudent(student, () -> showToast("Đã cập nhật"), () -> showToast("Cập nhật thất bại"));
                }
            }

            @Override
            public void onDelete(Student student) {
                viewModel.deleteStudent(student.getId(), () -> showToast("Đã xóa"), () -> showToast("Xóa thất bại"));
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}