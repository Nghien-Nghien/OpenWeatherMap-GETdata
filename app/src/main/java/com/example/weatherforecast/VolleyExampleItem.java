package com.example.weatherforecast;

public class VolleyExampleItem {
    private String mDate;
    private Double mTempAvr;
    private String mWeather;

    public VolleyExampleItem(String date, Double tempavr, String weather) {
        mDate = date;
        mTempAvr = tempavr;
        mWeather = weather;
    }

    public String getmDate() {
        return mDate;
    }

    public Double getmTempAvr() {
        return mTempAvr;
    }

    public String getmWeather() {
        return mWeather;
    }
}
