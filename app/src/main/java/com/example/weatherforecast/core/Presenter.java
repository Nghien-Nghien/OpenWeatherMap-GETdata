package com.example.weatherforecast.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherforecast.R;
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

public class Presenter extends AppCompatActivity implements Contract.Presenter, Contract.onGetDataInApi {
    private final Contract.onGetDataInApi mOnGetDataInApi;
    private final Contract.View mView;
    private final Model model = new Model();

    public List<RetrofitExampleItem> mRetrofitExampleList = new ArrayList<>();
    public TextView tvPlace;

    public Presenter(Contract.onGetDataInApi mOnGetDataInApi, Contract.View mView) {
        this.mOnGetDataInApi = mOnGetDataInApi;
        this.mView = mView;
    }

    @Override
    public void getDataInApi(Context context) {
        mRetrofitExampleList.clear();

        Call<WeatherMapAPI> call = model.initRetrofitCallApi().getDataInApi();

        call.enqueue(new Callback<WeatherMapAPI>() {
            @Override
            public void onResponse(@NonNull Call<WeatherMapAPI> call, @NonNull Response<WeatherMapAPI> response) {
                if (response.isSuccessful()) {
                    WeatherMapAPI cityName = response.body();
                    assert cityName != null;
                    tvPlace.setText(cityName.getCity().getName());

                    assert response.body() != null;
                    List<ListResponse> listArray = response.body().getList();
                    for (int i = 0; i < listArray.size(); i++) {
                        ListResponse list = listArray.get(i);

                        long date = list.getDate();
                        @SuppressLint("SimpleDateFormat") String transformedDate = new SimpleDateFormat("dd MMM yyyy  hh:mm").format(new Date(date * 1000));

                        double tempAvr = (list.getTemp().getMin() + list.getTemp().getMax()) / 2;
                        int transformedTempAvr = (int) tempAvr;

                        String weatherDescription = list.getWeather().get(0).getDescription();

                        String weatherIcon = list.getWeather().get(0).getIcon();

                        mRetrofitExampleList.add(new RetrofitExampleItem(transformedDate,transformedTempAvr,weatherDescription,weatherIcon));
                    }

                    mOnGetDataInApi.onSuccessful(tvPlace, mRetrofitExampleList);

                } else {
                    tvPlace.setText(getResources().getString(R.string.error));
                    mOnGetDataInApi.onFailure(tvPlace);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherMapAPI> call, @NonNull Throwable throwable) {
                tvPlace.setText(throwable.getMessage());
            }
        });
    }

    @Override
    public void onSuccessful(TextView tvPlace, List<RetrofitExampleItem> mRetrofitExampleList) {
        mView.onGetDataSuccessful(tvPlace, mRetrofitExampleList);
    }

    @Override
    public void onFailure(TextView tvPlace) {
        mView.onGetDataFailure(tvPlace);
    }
}
