package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListResponse {
    @SerializedName("dt")
    public long date;

    @SerializedName("temp")
    public Temp temp;

    @SerializedName("weather")
    public ArrayList<WeatherResponse> weather;

    public long getDate() {
        return date;
    }

    public Temp getTemp() {
        return temp;
    }

    public ArrayList<WeatherResponse> getWeather() {
        return weather;
    }
}
