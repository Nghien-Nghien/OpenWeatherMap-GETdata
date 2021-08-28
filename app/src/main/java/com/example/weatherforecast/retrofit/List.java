package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class List {
    @SerializedName("dt")
    public long date;

    @SerializedName("temp")
    public Temp temp;

    @SerializedName("weather")
    public ArrayList<Weather> weather;

    public long getDate() {
        return date;
    }

    public Temp getTemp() {
        return temp;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}
