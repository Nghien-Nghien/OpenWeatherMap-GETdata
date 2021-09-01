package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherMapAPI {
    @SerializedName("city")
    public City city;

    @SerializedName("list")
    public ArrayList<ListResponse> list;

    public City getCity() {
        return city;
    }

    public ArrayList<ListResponse> getList() {
        return list;
    }
}
