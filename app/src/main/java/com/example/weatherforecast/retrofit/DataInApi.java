package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataInApi {
    @SerializedName("city")
    public City city;

    @SerializedName("list")
    public ArrayList<List> list;

    public City getCity() {
        return city;
    }

    public ArrayList<List> getList() {
        return list;
    }
}
