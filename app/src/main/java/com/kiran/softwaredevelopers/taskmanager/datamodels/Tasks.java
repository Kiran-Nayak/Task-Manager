package com.kiran.softwaredevelopers.taskmanager.datamodels;

public class Tasks {
    private String email;
    private String task;

    public Tasks() {
    }

    public Tasks(String email, String task) {
        this.email = email;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
