package com.kiran.softwaredevelopers.taskmanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kiran.softwaredevelopers.taskmanager.model.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository appRepository;
    private MutableLiveData<FirebaseUser> mutableLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        appRepository = new LoginRepository(application);
        mutableLiveData = appRepository.getMutableLiveData();
    }

    public void register(String name, String email, String password,String dob, int age){
        appRepository.register(name, email, password,dob,age);
    }
    public void login(String email, String password){
        appRepository.login(email, password);
    }

    public MutableLiveData<FirebaseUser> getMutableLiveData() {
        return mutableLiveData;
    }
}
