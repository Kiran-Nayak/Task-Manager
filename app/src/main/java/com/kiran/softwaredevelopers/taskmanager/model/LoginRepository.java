package com.kiran.softwaredevelopers.taskmanager.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginRepository {

    private Application application;
    private MutableLiveData<FirebaseUser> mutableLiveData;

    public LoginRepository(Application application) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }

    public void register(String name, String email, String password, String dob,int age){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mutableLiveData.postValue(FirebaseAuth.getInstance().getCurrentUser());
                            insertIntoDatabase(name,email,dob,age);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mutableLiveData.postValue(null);
                Log.d("TAG", "onFailure: "+e.getMessage());
            }
        });
    }

    public void login(String email, String password){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mutableLiveData.postValue(FirebaseAuth.getInstance().getCurrentUser());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mutableLiveData.postValue(null);
                Log.d("TAG", "onFailure: "+e.getMessage());
            }
        });
    }

    private void insertIntoDatabase(String name, String email, String dob, int age) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("dob",dob);
        hashMap.put("age",String.valueOf(age));
        FirebaseDatabase.getInstance().getReference().child("users").push()
                .setValue(hashMap);
    }

}
