package com.anhngb.lab11.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anhngb.lab11.data.api.StudentService;
import com.anhngb.lab11.model.Student;
import com.anhngb.lab11.data.repository.StudentReporitory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentViewModel extends ViewModel {
    private final MutableLiveData<List<Student>> studentsLiveData = new MutableLiveData<>();
    private final StudentService service = StudentReporitory.getStudentService();

    public LiveData<List<Student>> getStudents() {
        return studentsLiveData;
    }

    public void fetchStudents() {
        service.getAllStudents().enqueue(new Callback<Student[]>() {
            @Override
            public void onResponse(Call<Student[]> call, Response<Student[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    studentsLiveData.setValue(Arrays.asList(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Student[]> call, Throwable t) {
                studentsLiveData.setValue(Collections.emptyList()); // or null
            }
        });
    }

    public void createStudent(Student student, Runnable onSuccess, Runnable onFailure) {
        service.createStudent(student).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null && response.body() == 1) {
                    onSuccess.run();
                    fetchStudents();
                } else {
                    onFailure.run();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                onFailure.run();
            }
        });
    }

    public void updateStudent(Student student, Runnable onSuccess, Runnable onFailure) {
        service.updateStudent(student.getId(), student).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null && response.body() == 1) {
                    onSuccess.run();
                    fetchStudents();
                } else {
                    onFailure.run();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                onFailure.run();
            }
        });
    }

    public void deleteStudent(int id, Runnable onSuccess, Runnable onFailure) {
        service.deleteStudent(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (Boolean.TRUE.equals(response.body())) {
                    onSuccess.run();
                    fetchStudents();
                } else {
                    onFailure.run();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                onFailure.run();
            }
        });
    }
}
