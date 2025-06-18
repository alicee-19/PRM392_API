package com.anhngb.lab11.data.api;

import com.anhngb.lab11.model.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {
    String STUDENTS = "/api/Students/Student"; // API endpoints
    @GET(STUDENTS)
    Call<Student[]> getAllStudents();

    @GET(STUDENTS + "/{id}")
    Call<Student> getStudentById(@Path("id") Object id);

    @POST(STUDENTS)
    Call<Integer> createStudent(@Body Student student);

    @PUT(STUDENTS + "/{id}")
    Call<Integer> updateStudent(@Path("id") Object id, @Body Student student);

    @DELETE(STUDENTS + "/{id}")
    Call<Boolean> deleteStudent(@Path("id") Object id);
}
