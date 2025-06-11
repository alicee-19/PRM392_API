package com.anhngb.lab11.data.repository;

import com.anhngb.lab11.data.api.APIClient;
import com.anhngb.lab11.data.api.StudentService;

public class StudentReporitory {
    public static StudentService getStudentService() {
        return APIClient.getClient().create(StudentService.class);
    }
}
