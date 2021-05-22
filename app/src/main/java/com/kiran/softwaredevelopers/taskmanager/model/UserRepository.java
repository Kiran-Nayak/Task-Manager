package com.kiran.softwaredevelopers.taskmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kiran.softwaredevelopers.taskmanager.datamodels.UserDetails;

import java.util.ArrayList;

public class UserRepository {
    private Application application;
    private MutableLiveData<UserDetails> mutableLiveData;
    private MutableLiveData<ArrayList<UserDetails>> listMutableLiveData;

    public UserRepository(Application application) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
    }

    public void userDetails(String email){
        FirebaseDatabase.getInstance().getReference().child("users")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                        if (userDetails.getEmail().equals(email)){
                            mutableLiveData.postValue(userDetails);
                            break;
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mutableLiveData.postValue(null);
            }
        });
    }

    public void allUserDetails(){

        ArrayList<UserDetails> userDetailsArrayList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                            if (!(userDetails.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))){
                                userDetailsArrayList.add(userDetails);
                            }
                        }
                        listMutableLiveData.postValue(userDetailsArrayList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        mutableLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<UserDetails> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<ArrayList<UserDetails>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
