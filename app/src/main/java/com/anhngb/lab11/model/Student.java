package com.anhngb.lab11.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String Name;
    @SerializedName("email")
    private String Email;
    @SerializedName("phone")
    private String Phone;
    @SerializedName("gender")
    private String Gender;

    public Student(int id, String name, String email, String phone, String gender) {
        Id = id;
        Name = name;
        Email = email;
        Phone = phone;
        Gender = gender;
    }

    public Student(Student student){
        Id = student.Id;
        Name = student.Name;
        Email = student.Email;
        Phone = student.Phone;
        Gender = student.Gender;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @NonNull
    @Override
    public String toString() {
        return getName() + ": " + getEmail() + " - " + getPhone() + " - " + getGender();
    }
}
