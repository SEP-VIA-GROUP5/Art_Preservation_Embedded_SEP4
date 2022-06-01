package com.via.sep4.viewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.via.sep4.model.CO2;
import com.via.sep4.model.Humidity;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.Temperature;
import com.via.sep4.repository.DataRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {
    DataRepository repository;

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

    public String getMetricsByRoomString(int id) {
        return repository.getMetricsByRoomString(id);
    }

    public int deleteRoom(int id) {
        return repository.deleteARoom(id);
    }

    public int addARoom(JSONObject jsonParam) {
        return repository.addSingleRoom(jsonParam);
    }

    public int addMetricsToRoom(int id) {
        return repository.addMetricsToRoom(id);
    }

    public Room deliveryRoom(Room room) {
        return room;
    }

    public void addTempNorm (Temperature temperature, int max)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", temperature.getId());
            jsonObject.put("max", max);
            jsonObject.put("temperature", temperature.getTemperature());
        } catch (JSONException e){
            Log.d("set norms on temp", e.getMessage());
        }
        repository.setTempNorm(jsonObject, max);
    }

    public void addHumNorm (Humidity humidity, int maxHum)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", humidity.getId());
            jsonObject.put("max", maxHum);
            jsonObject.put("humidity", humidity.getHumidity());
        } catch (JSONException e){
            Log.d("set norms on hum", e.getMessage());
        }
        repository.setHumNorm(jsonObject, maxHum);
    }

    public void addCO2Norm (CO2 co2, int maxCo2)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", co2.getId());
            jsonObject.put("max", maxCo2);
            jsonObject.put("co2", co2.getCo2());
        } catch (JSONException e){
            Log.d("set norms on co2", e.getMessage());
        }
        repository.setCO2Norm(jsonObject, maxCo2);
    }

    public int setAllNorms(int id, int temMax, int humMax, int co2Max){
        return repository.setAllNorms(id, temMax, humMax, co2Max);
    }

}
