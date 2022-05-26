package com.via.sep4.viewModel;

import androidx.lifecycle.ViewModel;

import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.repository.DataRepository;
import com.via.sep4.repository.SettingsRepository;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {
    DataRepository repository;
    SettingsRepository setRepository;

    public DataViewModel() {
        repository = DataRepository.getInstance();
    }

    public ArrayList<Room> getRooms() {
        return repository.getAllRooms();
    }

    public Room getSingleRoom(int id) {
        return repository.getSingleRoom(id);
    }

    public Metrics getMetricsSingleRoom(int number) {
        return repository.getMetricsSingleRoom(number);
    }

    public String getMetricsByRoomString(int id){
        return repository.getMetricsByRoomString(id);
    }

    public int deleteRoom(int id) {
        return repository.deleteARoom(id);
    }

    public int addARoom(JSONObject jsonParam){
        return repository.addSingleRoom(jsonParam);
    }

    public int addMetricsToRoom(int id){
        return repository.addMetricsToRoom(id);
    }

    public Room deliveryRoom(Room room){
        return room;
    }


    public void setNormsAndNotification(Room room, int minTemp, int maxTemp, int minHum, int maxHum, int minCO2, int maxC02)
    { }










}
