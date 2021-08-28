package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }
}
