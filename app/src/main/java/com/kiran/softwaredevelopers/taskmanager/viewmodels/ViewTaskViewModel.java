package com.kiran.softwaredevelopers.taskmanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kiran.softwaredevelopers.taskmanager.datamodels.Tasks;
import com.kiran.softwaredevelopers.taskmanager.model.ViewTaskRepository;

import java.util.ArrayList;

public class ViewTaskViewModel extends AndroidViewModel {

    private ViewTaskRepository viewTaskRepository;
    private MutableLiveData<ArrayList<Tasks>> mutableLiveData;

    public ViewTaskViewModel(@NonNull Application application) {
        super(application);
        viewTaskRepository = new ViewTaskRepository(application);
        mutableLiveData = viewTaskRepository.getTasksMutableLiveData();
    }

    public void getTasks(String email){
        viewTaskRepository.getTasks(email);

    }

    public MutableLiveData<ArrayList<Tasks>> getMutableLiveData() {
        return mutableLiveData;
    }

}
