package com.example.weatherforecast;

public class RetrofitExampleItem {
    private final String mDate;
    private final Integer mTempAvr;
    private final String mWeather;
    private final String mIcon;

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
