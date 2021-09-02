package com.example.weatherforecast;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.retrofit.APIInterface;
import com.example.weatherforecast.retrofit.ListResponse;
import com.example.weatherforecast.retrofit.WeatherMapAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMainActivity extends AppCompatActivity {
    private RetrofitExampleAdapter mRetrofitExampleAdapter;
    private List<RetrofitExampleItem> mRetrofitExampleList;

    public TextView tvPlace;
    public Button btGet;
    public EditText etPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity_main);

        RecyclerView mRecyclerView = findViewById(R.id.rvItems);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvPlace = findViewById(R.id.tvPlace);
        btGet = findViewById(R.id.btGet);
        etPlace = findViewById(R.id.etPlace);


        mRetrofitExampleAdapter = new RetrofitExampleAdapter(RetrofitMainActivity.this);
        mRecyclerView.setAdapter(mRetrofitExampleAdapter);

        buttonClickListener();
    }

    public void getDataInApi(String getPlace) {
        mRetrofitExampleList = new ArrayList<>();

        APIInterface apiInterface = retrofit().create(APIInterface.class);

        Call<WeatherMapAPI> call = apiInterface.getDataInApi(getPlace);

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
                    mRetrofitExampleAdapter.refreshExampleList(mRetrofitExampleList);

                } else {
                    tvPlace.setText(getResources().getString(R.string.error));
                    Toast.makeText(RetrofitMainActivity.this, " Maybe the place that you just typed don't exist on server or your character be wrong. Please recheck! ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherMapAPI> call, @NonNull Throwable throwable) {
                tvPlace.setText(throwable.getMessage());
            }
        });
    }

    public void buttonClickListener () {
        btGet.setOnClickListener(view -> {
            if (etPlace.getText().toString().equals("")) {
                Toast.makeText(RetrofitMainActivity.this, " Please input any place ", Toast.LENGTH_SHORT).show();
            } else {
                String getPlace = etPlace.getText().toString().toLowerCase().replaceAll("\\s", "");
                getDataInApi(getPlace);
            }
        });
    }

    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}