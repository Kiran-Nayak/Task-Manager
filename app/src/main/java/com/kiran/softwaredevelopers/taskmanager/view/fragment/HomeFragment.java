package com.kiran.softwaredevelopers.taskmanager.view.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.adapter.RecyclerViewAdapter;
import com.kiran.softwaredevelopers.taskmanager.datamodels.UserDetails;
import com.kiran.softwaredevelopers.taskmanager.view.MainActivity;
import com.kiran.softwaredevelopers.taskmanager.viewmodels.UserDetailsViewModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    UserDetailsViewModel userDetailsViewModel;
    TextView tvName;
    TextView tvEmail;
    TextView tvAge;
    TextView tvDOB;
    RecyclerView recyclerView;
    CardView cardView;
    FloatingActionButton floatingActionButton;
    MainActivity context;




    public HomeFragment() {
        // Required empty public constructor
    }
    public HomeFragment(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        userDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);
        userDetailsViewModel.getUserDetailsMutableLiveData().observe(this, new Observer<UserDetails>() {
            @Override
            public void onChanged(UserDetails userDetails) {
                if (userDetails != null) {
                    cardView.setVisibility(View.VISIBLE);
                    tvName.setText(userDetails.getName());
                    tvEmail.setText(userDetails.getEmail());
                    tvAge.setText(userDetails.getAge());
                    tvDOB.setText(userDetails.getDob());
                }
            }
        });
        userDetailsViewModel.getListMutableLiveData().observe(this, new Observer<ArrayList<UserDetails>>() {
            @Override
            public void onChanged(ArrayList<UserDetails> userDetails) {
                if (userDetails != null){
                    recyclerView.setAdapter(new RecyclerViewAdapter(getContext(),userDetails,context));
                }
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        cardView = view.findViewById(R.id.cardView);

        cardView.setVisibility(View.GONE);

        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvAge = view.findViewById(R.id.tvAge);
        tvDOB = view.findViewById(R.id.tvDOB);
        recyclerView = view.findViewById(R.id.list);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onClick();
            }
        });

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userDetailsViewModel.userDetails(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("email",tvEmail.getText().toString());
                context.onClicked(bundle);
            }
        });

        return view;
    }


}