package com.kiran.softwaredevelopers.taskmanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kiran.softwaredevelopers.taskmanager.model.AddTaskRepository;

public class AddTaskViewModel extends AndroidViewModel {
    AddTaskRepository addTaskRepository;

    MutableLiveData<FirebaseUser> mutableLiveData;

    public AddTaskViewModel(@NonNull Application application) {
        super(application);

        addTaskRepository = new AddTaskRepository(application);

        this.mutableLiveData = addTaskRepository.getMutableLiveData();
    }

    public void insert(String task){
        addTaskRepository.insert(task);
    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }
}
