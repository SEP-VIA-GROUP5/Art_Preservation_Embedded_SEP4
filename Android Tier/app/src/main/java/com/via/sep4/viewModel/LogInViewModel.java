package com.via.sep4.viewModel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.model.User;
import com.via.sep4.repository.UserRepository;

public class LogInViewModel extends ViewModel {

    private UserRepository repository;

    public LogInViewModel() {
        repository = UserRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser() {
        return repository.getCurrentFirebaseUser();
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public void attemptLogin(User userLog) {
        repository.login(userLog);
    }

}