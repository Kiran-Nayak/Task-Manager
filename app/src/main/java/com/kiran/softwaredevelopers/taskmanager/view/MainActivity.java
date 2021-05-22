package com.kiran.softwaredevelopers.taskmanager.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.listener.ClickedListener;
import com.kiran.softwaredevelopers.taskmanager.listener.RecyclerClickedListener;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.AddTasksFragment;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.HomeFragment;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.SettingsFragment;
import com.kiran.softwaredevelopers.taskmanager.view.fragment.ViewTaskFragment;

public class MainActivity extends AppCompatActivity implements ClickedListener, RecyclerClickedListener {

    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);

        }else {
            setTheme(R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportActionBar().hide();

        switchCompat = findViewById(R.id.switch2);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        HomeFragment homeFragment = new HomeFragment(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,homeFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,homeFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,new SettingsFragment()).addToBackStack(null).commit();
                        return true;

                }
                return false;
            }
        });

    }

    public void hide(){
        switchCompat.setVisibility(View.GONE);
    }



    @Override
    public void onClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new AddTasksFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onClicked(Bundle bundle) {
        ViewTaskFragment viewTaskFragment = new ViewTaskFragment();
        viewTaskFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, viewTaskFragment).addToBackStack(null).commit();
        Log.d("TAG", "onClicked: Go to ViewTasks");
    }
}