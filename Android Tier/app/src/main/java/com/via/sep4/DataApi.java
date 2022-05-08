package com.via.sep4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataApi {

    @GET("jdbc:postgresql://localhost/Data Tier/{Data}") Call<DataResponse> getData

}
