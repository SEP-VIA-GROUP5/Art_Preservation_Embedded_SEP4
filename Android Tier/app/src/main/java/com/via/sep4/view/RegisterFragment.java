package com.via.sep4.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.User;
import com.via.sep4.viewModel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    private RegisterViewModel mViewModel;
    private EditText name;
    private EditText username;
    private EditText email;
    private EditText phoneNum;
    private EditText password;
    private EditText passwordRepeat;
    private Button signup;
    private TextView signIn;
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";   //verify email

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

        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();
                String usernameString = username.getText().toString();
                String emailString = email.getText().toString();
                String phone = phoneNum.getText().toString();
                String passwordString = password.getText().toString();
                String passwordStringRepeat = passwordRepeat.getText().toString();

                if (emailValid(emailString)) {
                    if (passwordSame(passwordString, passwordStringRepeat)) {
                        if (passwordLength(passwordString, passwordStringRepeat)) {
                            try {
                                auth.createUserWithEmailAndPassword(emailString, passwordString)
                                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                User newUser;
                                                if (phone.equals("") || nameString.equals("") || usernameString.equals("")) {
                                                    newUser = new User(emailString, passwordString);
                                                } else {
                                                    newUser = new User(emailString, passwordString, nameString, phone);
                                                }

                                                dbRef = db.getReference("FullName");
                                                dbRef.child(DataHandler.changeDotToComaEmail(emailString)).setValue(nameString);
                                                dbRef = db.getReference("Phone");
                                                dbRef.child(DataHandler.changeDotToComaEmail(emailString)).setValue(phone);
                                                dbRef = db.getReference("Username");
                                                dbRef.child(DataHandler.changeDotToComaEmail(emailString)).setValue(usernameString);

                                                mViewModel.register(newUser);
                                                Snackbar.make(view, R.string.R_success, Snackbar.LENGTH_SHORT).show();
                                                getActivity().onBackPressed();
                                            }
                                        });
                            } catch (Exception e) {
                                Log.e("register error", e.getMessage());
                            }
                        } else {
                            Snackbar.make(view, R.string.R_passwordLengthE, Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(view, R.string.R_passwordNotMachE, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(view, R.string.R_emailNotValidE, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        db = FirebaseDatabase.getInstance(getString(R.string.firebase_dbLink));
    }

    private boolean passwordSame(String text1, String text2) {
        return text1.equals(text2);
    }

    private boolean emailValid(String emailAddress) {
        return emailAddress.matches(REGEX_EMAIL);
    }

    private boolean passwordLength(String text1, String text2) {
        return text1.length() >= 6 && text2.length() >= 6;
    }
}