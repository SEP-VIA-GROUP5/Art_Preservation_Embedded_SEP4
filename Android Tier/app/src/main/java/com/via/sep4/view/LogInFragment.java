package com.via.sep4.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.R;
import com.via.sep4.model.User;
import com.via.sep4.viewModel.LogInViewModel;

public class LogInFragment extends Fragment {


    EditText logEmail, logPassword;
    Button loginBtn;
    TextView toRegister;

    private LogInViewModel viewModel;
    private FirebaseAuth auth;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        checkUser(user);
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(LogInViewModel.class);
        navController = Navigation.findNavController(view);

        initializeViews(view);
        setupViews();
    }


    private void initializeViews(View view) {
        logEmail = view.findViewById(R.id.emailEt);
        logPassword = view.findViewById(R.id.passwordEt);
        loginBtn = view.findViewById(R.id.signInBtn);
        toRegister = view.findViewById(R.id.createAccountView);

    }

    private void setupViews() {
        loginBtn.setOnClickListener(v -> {
            viewModel.attemptLogin(new User(logEmail.getText().toString(), logPassword.getText().toString()));
            auth.signInWithEmailAndPassword(logEmail.getText().toString(), logPassword.getText().toString())
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isComplete()) {
                                Log.v("login error", task.getException().toString());
                                Snackbar.make(v, task.getException().toString(), Snackbar.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = auth.getCurrentUser();
                                updateUI(user);
                            }
                        }
                    });
        });

        toRegister.setOnClickListener(v -> {
            NavHostFragment.findNavController(LogInFragment.this).navigate(R.id.action_signIn_fragment_to_signup_fragment);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    private void updateUI(FirebaseUser user) {
        auth.updateCurrentUser(user);
        checkUser(user);
    }

    private void checkUser(FirebaseUser user) {
        if (user != null) {
            getActivity().onBackPressed();
        }
    }
}

















