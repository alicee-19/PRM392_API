package com.anhngb.lab11.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anhngb.lab11.R;
import com.anhngb.lab11.model.Student;

public class StudentDialog {
    public interface StudentDialogListener {
        void onSave(Student student);
        void onDelete(Student student);
    }

    public static void show(Context context, int actionId, Student student, StudentDialogListener listener) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.student_action_dialog);

        // Bind views
        EditText etName = dialog.findViewById(R.id.etName);
        EditText etEmail = dialog.findViewById(R.id.etEmail);
        EditText etPhone = dialog.findViewById(R.id.etPhone);
        Spinner ddGender = dialog.findViewById(R.id.ddGender);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnClear = dialog.findViewById(R.id.btnClear);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);

        // Setup dropdown
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.genders,
                android.R.layout.simple_spinner_item
        );
        ddGender.setAdapter(genderAdapter);

        if (actionId == R.id.action_popup_add || actionId == R.id.action_popup_edit) {
            txtTitle.setText(actionId == R.id.action_popup_add ? "Thêm học sinh" : "Chỉnh sửa học sinh");
            btnYes.setText(actionId == R.id.action_popup_add ? "Thêm" : "Cập nhật");

            if (student != null) {
                etName.setText(student.getName());
                etEmail.setText(student.getEmail());
                etPhone.setText(student.getPhone());
                ddGender.setSelection(student.getGender().equals("Male") ? 0 :
                        student.getGender().equals("Female") ? 1 : 2);
            }

            btnYes.setOnClickListener(v -> {
                Student s = new Student(
                        student != null ? student.getId() : 0,
                        etName.getText().toString(),
                        etEmail.getText().toString(),
                        etPhone.getText().toString(),
                        ddGender.getSelectedItem().toString()
                );
                listener.onSave(s);
                dialog.dismiss();
            });
        } else if (actionId == R.id.action_popup_delete) {
            txtTitle.setText("Bạn có chắc muốn xóa học sinh này?");
            etName.setVisibility(View.GONE);
            etEmail.setVisibility(View.GONE);
            etPhone.setVisibility(View.GONE);
            ddGender.setVisibility(View.GONE);
            btnYes.setText("Có");
            btnClear.setText("Không");

            btnYes.setOnClickListener(v -> {
                listener.onDelete(student);
                dialog.dismiss();
            });
        }

        btnClear.setOnClickListener(v -> dialog.dismiss());
        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
