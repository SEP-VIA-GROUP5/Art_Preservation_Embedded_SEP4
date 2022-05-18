package com.via.sep4.viewModel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.via.sep4.model.User;
import com.via.sep4.repository.UserRepository;

public class RegisterViewModel extends ViewModel {


    private final UserRepository repository;

    public RegisterViewModel() {
        repository = UserRepository.getInstance();
    }

    public void register(User user) {
        repository.register(user);
    }

    public void reset() {

    }
}