package com.example.weatherforecast.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("daily?units=metric&cnt=7&appid=60c6fbeb4b93ac653c492ba806fc346d&q=saigon")
    Call<WeatherMapAPI> getDataInApi();
}
