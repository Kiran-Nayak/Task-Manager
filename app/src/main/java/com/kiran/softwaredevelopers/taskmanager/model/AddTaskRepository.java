package com.kiran.softwaredevelopers.taskmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddTaskRepository {

    private Application application;
    private MutableLiveData<FirebaseUser> mutableLiveData;

    public AddTaskRepository(Application application) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    public void insert(String task){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("task", task);
        hashMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());


        FirebaseDatabase.getInstance().getReference()
                .child("tasks")
                .push().setValue(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            mutableLiveData.postValue(FirebaseAuth.getInstance().getCurrentUser());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }
}
