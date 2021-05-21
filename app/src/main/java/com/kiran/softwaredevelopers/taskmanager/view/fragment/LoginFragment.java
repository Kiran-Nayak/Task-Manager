package com.kiran.softwaredevelopers.taskmanager.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseUser;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.view.LoginActivity;
import com.kiran.softwaredevelopers.taskmanager.viewmodels.LoginViewModel;

public class LoginFragment extends Fragment {

    LoginActivity loginActivity;
    LoginViewModel loginViewModel;
    ProgressDialog progressDialog;


    public LoginFragment() {
        // Required empty public constructor
    }
    public LoginFragment(LoginActivity context) {
        this.loginActivity = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Please Wait...");

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    Toast.makeText(getContext(), "Logged-in Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvRegister = view.findViewById(R.id.tvRegister);
        EditText etLoginEmail = view.findViewById(R.id.etLoginEmail);
        EditText etLoginPassword = view.findViewById(R.id.etLoginPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (!(etLoginEmail.getText().toString().isEmpty() ||
                etLoginPassword.getText().toString().isEmpty())) {
                    progressDialog.show();
                    loginViewModel.login(etLoginEmail.getText().toString()
                            , etLoginPassword.getText().toString());
                }else {
                    Toast.makeText(getContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivity.onClick();
            }
        });
        return view;
    }
}


