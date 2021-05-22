package com.kiran.softwaredevelopers.taskmanager.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kiran.softwaredevelopers.taskmanager.datamodels.UserDetails;
import com.kiran.softwaredevelopers.taskmanager.model.UserRepository;

import java.util.ArrayList;

public class UserDetailsViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private MutableLiveData<UserDetails> userDetailsMutableLiveData;
    private MutableLiveData<ArrayList<UserDetails>> listMutableLiveData;

    public UserDetailsViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        userDetailsMutableLiveData = userRepository.getMutableLiveData();
        listMutableLiveData = userRepository.getListMutableLiveData();
    }

    public void userDetails(String email){
        userRepository.userDetails(email);
        userRepository.allUserDetails();
    }

    public MutableLiveData<UserDetails> getUserDetailsMutableLiveData() {
        return userDetailsMutableLiveData;
    }

    public MutableLiveData<ArrayList<UserDetails>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
