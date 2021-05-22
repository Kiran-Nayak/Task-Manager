package com.kiran.softwaredevelopers.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.view.MainActivity;
import com.kiran.softwaredevelopers.taskmanager.viewmodels.AddTaskViewModel;


public class AddTasksFragment extends Fragment {

    AddTaskViewModel addTaskViewModel;
    EditText etAddTasks;

    public AddTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addTaskViewModel = ViewModelProviders.of(this).get(AddTaskViewModel.class);
        addTaskViewModel.getMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    etAddTasks.setText("");
                    Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_tasks, container, false);

        etAddTasks = view.findViewById(R.id.etAddTasks);
        Button btnAddTask = view.findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAddTasks.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else {
                    addTaskViewModel.insert(etAddTasks.getText().toString());
                }
            }
        });


        return view;
    }
}