package com.kiran.softwaredevelopers.taskmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kiran.softwaredevelopers.taskmanager.datamodels.Tasks;

import java.util.ArrayList;

public class ViewTaskRepository {

    private Application application;
    private MutableLiveData<ArrayList<Tasks>> tasksMutableLiveData;

    public ViewTaskRepository(Application application) {
        this.application = application;
        tasksMutableLiveData = new MutableLiveData<>();
    }

    public void getTasks(String email){
        FirebaseDatabase.getInstance().getReference()
                .child("tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Tasks> tasks1 = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Tasks tasks = dataSnapshot.getValue(Tasks.class);
                            if (tasks != null && tasks.getEmail().equals(email)){
                                tasks1.add(tasks);

                            }

                        }
                        tasksMutableLiveData.postValue(tasks1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        tasksMutableLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<ArrayList<Tasks>> getTasksMutableLiveData() {
        return tasksMutableLiveData;
    }
}
