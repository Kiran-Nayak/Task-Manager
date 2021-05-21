package com.kiran.softwaredevelopers.taskmanager.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.listener.ClickedListener;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.LoginFragment;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements ClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new LoginFragment(LoginActivity.this)).commit();
    }


    @Override
    public void onClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new RegisterFragment())
                .addToBackStack(null).commit();
    }
}