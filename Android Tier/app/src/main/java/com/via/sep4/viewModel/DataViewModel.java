package com.via.sep4.viewModel;

import androidx.lifecycle.ViewModel;

import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.repository.DataRepository;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {
    DataRepository repository;

    public DataViewModel() {
        repository = DataRepository.getInstance();
    }

    public ArrayList<Room> getRooms() {
        return repository.connectHttpRooms();
    }

    public Room getSingleRoom(int id) {
        return repository.getSingleRoom(id);
    }

    public Metrics getMetricsSingleRoom(int number) {
        return repository.getMetricsSingleRoom(number);
    }

    public int deleteRoom(int id) {
        return repository.deleteARoom(id);
    }

    public int addARoom(JSONObject jsonParam){
        return repository.addSingleRoom(jsonParam);
    }
}
