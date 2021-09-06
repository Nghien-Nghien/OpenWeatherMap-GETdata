package com.example.weatherforecast.core;

import com.example.weatherforecast.model.APIInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model implements Contract.Model {

    @Override
    public APIInterface initRetrofitCallApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        return apiInterface;
    }
}
