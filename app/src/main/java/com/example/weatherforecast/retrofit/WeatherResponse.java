package com.example.weatherforecast.retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
