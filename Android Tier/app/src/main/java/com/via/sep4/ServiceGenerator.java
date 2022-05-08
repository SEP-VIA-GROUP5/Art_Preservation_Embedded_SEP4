package com.via.sep4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static DataApi dataApi;

    public static DataApi getDataApi()
    {
        if(dataApi== null)
        {
            dataApi= new Retrofit.Builder().baseUrl(jdbc:postgresql://localhost/Data Tier).addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataApi.class);
        }
        return dataApi;

    }


}
