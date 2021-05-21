package com.kiran.softwaredevelopers.taskmanager.view.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseUser;
import com.kiran.softwaredevelopers.taskmanager.R;
import com.kiran.softwaredevelopers.taskmanager.viewmodels.LoginViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private long dob;
    ProgressDialog progressDialog;

    public RegisterFragment() {
        // Required empty public constructor
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
                    Toast.makeText(getContext(), "User Created", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        EditText etRegisterName = view.findViewById(R.id.etRegisterName);
        EditText etRegisterEmail = view.findViewById(R.id.etRegisterEmail);
        EditText etRegisterPassword = view.findViewById(R.id.etRegisterPassword);
        EditText etRegisterConfirmPassword = view.findViewById(R.id.etRegisterConfirmPassword);
        EditText etRegisterDOB = view.findViewById(R.id.etRegisterDOB);

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                dob = myCalendar.getTimeInMillis();

                etRegisterDOB.setText(sdf.format(myCalendar.getTime()));
            }
        };


        etRegisterDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etRegisterName.getText().toString().isEmpty() ||
                        etRegisterPassword.getText().toString().isEmpty() ||
                        etRegisterConfirmPassword.getText().toString().isEmpty() || 
                        etRegisterDOB.getText().toString().isEmpty() ||
                        etRegisterEmail.getText().toString().isEmpty())) {
                    progressDialog.show();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                    int age = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis())) - Integer.parseInt(simpleDateFormat.format(dob));
                    loginViewModel.register(etRegisterName.getText().toString(), etRegisterEmail.getText().toString(), etRegisterPassword.getText().toString()
                            , etRegisterDOB.getText().toString(), age);
                }else {
                    Toast.makeText(getContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}