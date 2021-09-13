package com.example.weatherforecast.core;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.weatherforecast.RetrofitExampleItem;
import com.example.weatherforecast.model.ListResponse;
import com.example.weatherforecast.model.WeatherMapAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMainPresenter implements Contract.Presenter {
    private final Contract.View mView;
    private final Contract.Model model = new Model();

    public RetrofitMainPresenter(Contract.View mView) {
        this.mView = mView;
    }

    @Override
    public void callWeatherMapApi(String getPlaceQuery) {
        List<RetrofitExampleItem> mRetrofitExampleList = new ArrayList<>();

        if (getPlaceQuery.equals("")) {
            mView.onPlaceIsEmpty();
        } else {
            mView.showProgressBar();

            String placeFormatted = getPlaceQuery.toLowerCase().replaceAll("\\s", "");

            Call<WeatherMapAPI> call = model.initRetrofitCallApi().getDataInApi(placeFormatted);

            call.enqueue(new Callback<WeatherMapAPI>() {
                @Override
                public void onResponse(@NonNull Call<WeatherMapAPI> call, @NonNull Response<WeatherMapAPI> response) {
                    if (response.isSuccessful()) {
                        WeatherMapAPI cityName = response.body();
                        String place = cityName.getCity().getName();

                        List<ListResponse> listArray = response.body().getList();
                        for (int i = 0; i < listArray.size(); i++) {
                            ListResponse list = listArray.get(i);

                            long date = list.getDate();
                            @SuppressLint("SimpleDateFormat") String transformedDate = new SimpleDateFormat("dd MMM yyyy  hh:mm").format(new Date(date * 1000));

                            double tempAvr = (list.getTemp().getMin() + list.getTemp().getMax()) / 2;
                            int transformedTempAvr = (int) tempAvr;

                            String weatherDescription = list.getWeather().get(0).getDescription();

                            String weatherIcon = list.getWeather().get(0).getIcon();

                            mRetrofitExampleList.add(new RetrofitExampleItem(transformedDate, transformedTempAvr, weatherDescription, weatherIcon));
                        }
                        mView.hideProgressBar();
                        mView.onResponseSuccessful(place, mRetrofitExampleList);

                    } else {
                        mView.hideProgressBar();
                        mView.onResponseFail();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<WeatherMapAPI> call, @NonNull Throwable throwable) {
                    mView.hideProgressBar();
                    mView.onFailure(throwable.getMessage());
                }
            });
        }
    }
}
