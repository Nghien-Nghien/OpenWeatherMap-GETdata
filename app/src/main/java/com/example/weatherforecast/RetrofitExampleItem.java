package com.example.weatherforecast;

public class RetrofitExampleItem {
    private String mDate;
    private Integer mTempAvr;
    private String mWeather;
    private String mIcon;

    public RetrofitExampleItem(String date, Integer tempavr, String weather, String icon) {
        mDate = date;
        mTempAvr = tempavr;
        mWeather = weather;
        mIcon = icon;
    }

    public String getmDate() {
        return mDate;
    }

    public Integer getmTempAvr() {
        return mTempAvr;
    }

    public String getmWeather() {
        return mWeather;
    }

    public String getmIcon() {
        return mIcon;
    }
}
