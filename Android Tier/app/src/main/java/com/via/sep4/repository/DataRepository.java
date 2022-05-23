package com.via.sep4.repository;

import androidx.lifecycle.MutableLiveData;

public class DataRepository {

    private static DataRepository instance;
    private final MutableLiveData<String> errors = new MutableLiveData<>("");

    public static DataRepository getInstance(){
        if (instance == null){
            instance = new DataRepository();
        }
        return instance;
    }

    public void getHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
