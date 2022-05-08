package com.via.sep4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {


    private static DataApi dataApi;


    private static final String BASE_URL = "http://localhost:8080/SEP4/rooms/{{id}}";


    public static DataApi getDataApi() {

        if (dataApi == null) {
            dataApi = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(DataApi.class);
        }
        return dataApi;
    }


}
