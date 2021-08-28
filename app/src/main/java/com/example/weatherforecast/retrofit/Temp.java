package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

public class Temp {
    @SerializedName("min")
    public Double min;

    @SerializedName("max")
    public Double max;

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}
