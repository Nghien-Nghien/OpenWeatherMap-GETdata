package com.example.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }
}
