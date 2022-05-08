package com.via.sep4;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface DataApi {

    @GET("/{{id}}") Call<DataResponse>  getData();


}
