package com.via.sep4.viewModel;

import androidx.lifecycle.ViewModel;

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