package com.via.sep4.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.via.sep4.DataHandler;
import com.via.sep4.R;

public class AccountFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    private TextView nameText;
    private TextView usernameText;
    private TextView emailText;
    private TextView phoneText;
    private Button saveButton, changePasswordBtn;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        nameText = v.findViewById(R.id.nameEt);
        usernameText = v.findViewById(R.id.usernameEt);
        emailText = v.findViewById(R.id.MinH);
        phoneText = v.findViewById(R.id.phoneNoEt);
        saveButton = v.findViewById(R.id.saveBtn);
        changePasswordBtn = v.findViewById(R.id.changePass);
        loadInfo();
        editTextListener();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance(getString(R.string.firebase_dbLink));
                String emailString = user.getEmail();
                dbRef = db.getReference("Username/")
                        .child(DataHandler.changeDotToComaEmail(emailString));
                dbRef.setValue(usernameText.getText().toString());
                dbRef = db.getReference("FullName/")
                        .child(DataHandler.changeDotToComaEmail(emailString));
                dbRef.setValue(nameText.getText().toString());
                dbRef = db.getReference("Phone/")
                        .child(DataHandler.changeDotToComaEmail(emailString));
                dbRef.setValue(phoneText.getText().toString());
                Snackbar.make(v, getString(R.string.account_save_ok), Snackbar.LENGTH_SHORT).show();
            }
        });


        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText resetPassword = new EditText(view.getContext());

                AlertDialog.Builder passwordreesetdiag = new AlertDialog.Builder(view.getContext());

                passwordreesetdiag.setTitle("Password Reset");
                passwordreesetdiag.setMessage(" Enter the new password ");
                passwordreesetdiag.setView(resetPassword);
                passwordreesetdiag.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //take the email and send reset link
                        String newPassword = resetPassword.getText().toString();

                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                // a message to be created
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               //a message to be created
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



        return v;
    }

    private void loadInfo() {
        db = FirebaseDatabase.getInstance(getString(R.string.firebase_dbLink));
        String emailString = user.getEmail();
        emailText.setText(emailString);

        dbRef = db.getReference("Username/")
                .child(DataHandler.changeDotToComaEmail(emailString));
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String usernameString = String.valueOf(task.getResult().getValue());
                usernameText.setText(usernameString);
            }
        });
        dbRef = db.getReference("Phone/")
                .child(DataHandler.changeDotToComaEmail(emailString));
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String phone = String.valueOf(task.getResult().getValue());
                phoneText.setText(phone);
            }
        });
        dbRef = db.getReference("FullName/")
                .child(DataHandler.changeDotToComaEmail(emailString));
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String name = String.valueOf(task.getResult().getValue());
                nameText.setText(name);
            }
        });
        dbRef = db.getReference("Email/")
                .child(DataHandler.changeDotToComaEmail(emailString));
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String email = String.valueOf(task.getResult().getValue());
                String emailFull = "Email : " + email;
                emailText.setHint(emailFull);
            }
        });
    }

    private void editTextListener(){
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                saveButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                saveButton.setEnabled(true);
            }
        });
        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                saveButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                saveButton.setEnabled(true);
            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                saveButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                saveButton.setEnabled(true);
            }
        });
    }
}