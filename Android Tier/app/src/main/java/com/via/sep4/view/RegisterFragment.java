package com.via.sep4.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.via.sep4.R;
import com.via.sep4.viewModel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private EditText name;
    private EditText username;
    private EditText email;
    private EditText phoneNum;
    private EditText password;
    private EditText passwordRepeat;
    private Button signup;
    private TextView signIn;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_signup, container, false);
        name = v.findViewById(R.id.nameEt);
        username = v.findViewById(R.id.usernameEt);
        email = v.findViewById(R.id.emailEt);
        phoneNum = v.findViewById(R.id.phoneNoEt);
        password = v.findViewById(R.id.passwordEt);
        passwordRepeat = v.findViewById(R.id.confirmPasswordEt);
        signup = v.findViewById(R.id.signUpBtn);
        signIn = v.findViewById(R.id.signinView);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RegisterFragment.this).navigate(R.id.action_signup_fragment_to_signIn_fragment);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}