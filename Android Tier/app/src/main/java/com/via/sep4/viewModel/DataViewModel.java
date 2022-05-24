package com.via.sep4.viewModel;

import androidx.lifecycle.ViewModel;

import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.repository.DataRepository;

public class DataViewModel extends ViewModel {
    DataRepository repository;

    public DataViewModel(){
        repository = DataRepository.getInstance();
    }

    public String getRooms(){
        return repository.connectHttpRooms();
    }

    public Room getSingleRoom(int id){
        return repository.getSingleRoom(id);
    }

    public Metrics getMetricsSingleRoom(int number){
        return repository.getMetricsSingleRoom(number);
    }
}
