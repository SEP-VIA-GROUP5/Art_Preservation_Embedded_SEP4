package com.via.sep4.ViewModel;

import androidx.lifecycle.ViewModel;

import com.via.sep4.Repository.DataRepository;

public class DataViewModel extends ViewModel {
    DataRepository repository;

    public DataViewModel(){
        repository = DataRepository.getInstance();

    }

}
