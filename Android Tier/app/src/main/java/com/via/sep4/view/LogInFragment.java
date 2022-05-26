package com.via.sep4.view;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.NavigationBlock;
import com.via.sep4.R;
import com.via.sep4.model.User;
import com.via.sep4.viewModel.LogInViewModel;

import java.util.Objects;

public class LogInFragment extends Fragment {


    EditText logEmail, logPassword;
    Button loginBtn ;
    TextView toRegister, forgotPass;

    private LogInViewModel viewModel;
    private FirebaseAuth auth;
    private NavController navController;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_fragment, container, false);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
      // TODO checkthe next commented method, is the same in Register Fragment but there it s working.
        //  ((NavigationBlock) getActivity()).setDrawerEnabled(false);

        checkUser(user);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(LogInViewModel.class);
        navController = Navigation.findNavController(view);
        initView(view);
       // ((NavigationBlock) getActivity()).setDrawerEnabled(false);

    }

    private void initView(View view) {
        logEmail = view.findViewById(R.id.emailEt);
        logPassword = view.findViewById(R.id.passwordEt);
        loginBtn = view.findViewById(R.id.signInBtn);
        toRegister = view.findViewById(R.id.createAccountView);
        forgotPass = view.findViewById(R.id.forgpasBtn);




        FirebaseUser user;
        setupViews();

    }

    private void setupViews() {




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetEmail = new EditText(view.getContext());
                AlertDialog.Builder passwordreesetdiag = new AlertDialog.Builder(view.getContext());
                passwordreesetdiag.setTitle("Password Reset");
                passwordreesetdiag.setMessage(" Enter your email address. ");
                passwordreesetdiag.setView(resetEmail);
                passwordreesetdiag.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //take the email and send reset link
                        String Email = resetEmail.getText().toString();
                        auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(view, "An email has been sent to reset the password", Snackbar.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Snackbar.make(view, "Something when wrong ", Snackbar.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
                passwordreesetdiag.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordreesetdiag.create().show();
            }


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

















