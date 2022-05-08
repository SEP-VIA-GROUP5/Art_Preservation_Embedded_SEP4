package com.via.sep4;

import android.widget.TextView;

public class DataResponse {

    private int temperature;
    private int CO2;
    private int humidity;

    public Data getData()
    {
return new Data(temperature,CO2,humidity);

    }


}
