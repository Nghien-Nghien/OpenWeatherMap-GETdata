package com.example.weatherforecast;

public class ExampleItem {
    private String mDate;
    private Double mTempAvr;
    private String mWeather;

    public ExampleItem(String date, Double tempavr, String weather) {
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
