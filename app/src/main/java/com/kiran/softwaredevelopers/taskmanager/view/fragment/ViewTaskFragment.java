package com.kiran.softwaredevelopers.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.adapter.RecyclerViewAdapter;
import com.kiran.softwaredevelopers.taskmanager.adapter.ViewTaskAdapter;
import com.kiran.softwaredevelopers.taskmanager.datamodels.Tasks;
import com.kiran.softwaredevelopers.taskmanager.view.MainActivity;
import com.kiran.softwaredevelopers.taskmanager.viewmodels.ViewTaskViewModel;

import java.util.ArrayList;

public class ViewTaskFragment extends Fragment {

    String foundEmail = null;
    ViewTaskViewModel viewTaskViewModel;
    RecyclerView viewTasks;

    public ViewTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewTaskViewModel = ViewModelProviders.of(this).get(ViewTaskViewModel.class);
        viewTaskViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<Tasks>>() {
            @Override
            public void onChanged(ArrayList<Tasks> tasks) {
                if (tasks != null){
                    viewTasks.setAdapter(new ViewTaskAdapter(getContext(),tasks));
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_view_task, container, false);
        String email = getArguments().getString("email");
        Log.d("TAG", "onCreateView: "+email);
        if (email != null){
            foundEmail = email;
        }
        Log.d("TAG", "onCreateView: "+foundEmail);
        if (foundEmail != null){
            viewTaskViewModel.getTasks(foundEmail);
        }

        viewTasks = view.findViewById(R.id.viewTasks);
        viewTasks.setHasFixedSize(false);
        viewTasks.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}