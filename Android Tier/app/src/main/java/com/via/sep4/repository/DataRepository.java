package com.via.sep4.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//import com.via.sep4.Data;
//import com.via.sep4.DataApi;
//import com.via.sep4.DataResponse;
import com.via.sep4.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class DataRepository {

    private static DataRepository instance;


    /*private final MutableLiveData<Data> requestData;

    private DataRepository()
    {requestData = new MutableLiveData<>();}

    public static synchronized  DataRepository getInstance()
    {if(instance == null)
    {instance = new DataRepository();}
    return instance;

    }

    public LiveData<Data> getData()
    {return requestData;}


    public void requestData (Data data)
    {
        DataApi dataApi = ServiceGenerator.getDataApi();
        Call<DataResponse> call = dataApi.getData();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.isSuccessful())
                {
                    requestData.setValue(response.body().getData());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.i("Retrofit","Something went wrong :(");
            }
        });

    }

*/




}
