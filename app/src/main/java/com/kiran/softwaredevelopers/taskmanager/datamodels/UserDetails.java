package com.kiran.softwaredevelopers.taskmanager.datamodels;

public class UserDetails {
    String name;
    String email;
    String age;
    String dob;

    public UserDetails() {
    }

    public UserDetails(String name, String email, String age, String dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
