package com.via.sep4.repository;


import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.via.sep4.livedata.FirebaseUserLiveData;
import com.via.sep4.model.User;

public class UserRepository {

    private static UserRepository instance;

    private final FirebaseAuth fAuth;

    private final MutableLiveData<String> error = new MutableLiveData<>("");
    private MutableLiveData<String> authenticationMessage = new MutableLiveData<>("");
    //    private final FirebaseFirestore fStore;
    String userID;

    private final FirebaseUserLiveData currentFirebaseUser;

    private UserRepository() {
        currentFirebaseUser = new FirebaseUserLiveData();
        //  fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void register(User user) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createUserWithEmail:success");
            }
        });
    }

    public void login(User userLog) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(userLog.getEmail(), userLog.getPassword())
                .addOnCompleteListener(result -> {
                    if (result.isSuccessful()) {
                        Log.i(TAG, "Login Successfully!");
                    } else {
                        Log.i(TAG, "Error login!");
                        error.setValue("Invalid email/password combination");
                    }
                });
    }
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public FirebaseUserLiveData getCurrentFirebaseUser() {
        return currentFirebaseUser;
    }

    public LiveData<String> getErrorMessage() {
        return error;
    }
}
