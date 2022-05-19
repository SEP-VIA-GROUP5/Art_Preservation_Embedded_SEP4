package com.via.sep4.view;

import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.via.sep4.R;
import com.via.sep4.model.User;
import com.via.sep4.viewModel.LogInViewModel;

public class LogInFragment extends Fragment {


    EditText logEmail, logPassword;
    Button loginBtn;
    TextView toRegister;

    private LogInViewModel viewModel;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment, container, false);
    }
    public onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
        {
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
                Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
               // navigate   navController.navigate(R.id.);
            });

            toRegister.setOnClickListener(v -> {
                // navController.navigate(R.id.action);
            });

        }






    }

















}