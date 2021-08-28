package com.example.weatherforecast;

import com.example.weatherforecast.retrofit.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RetrofitExampleAdapter mRetrofitExampleAdapter;
    private ArrayList<RetrofitExampleItem> mRetrofitExampleList;

    public TextView tvPlace;
    public Button btGet;
    public EditText etPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity_main);

        mRecyclerView = findViewById(R.id.rvItems);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvPlace = findViewById(R.id.tvPlace);
        btGet = findViewById(R.id.btGet);
        etPlace = findViewById(R.id.etPlace);

        mRetrofitExampleList = new ArrayList<>();

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPlace.getText().toString().equals("")) {
                    Toast.makeText(RetrofitMainActivity.this, " Please input any place ", Toast.LENGTH_SHORT).show();
                } else {
                    String getPlace = etPlace.getText().toString().toLowerCase().replaceAll("\\s", "");
                    getDataInApi(getPlace);
                }
            }
        });
    }

    public void getDataInApi(String getPlace) {
        mRetrofitExampleList.clear(); //Avoid duplicating data displayed on RecyclerView everytime do request

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Call<DataInApi> call = apiInterface.getDataInApi(getPlace);

        call.enqueue(new Callback<DataInApi>() {
            @Override
            public void onResponse(Call<DataInApi> call, Response<DataInApi> response) {
                if (response.isSuccessful()) {

                    DataInApi cityName = response.body();
                    tvPlace.setText(cityName.getCity().getName());

                    ArrayList<List> listArray = response.body().getList();
                    for (int i = 0; i < listArray.size(); i++) {
                        List list = listArray.get(i);

                        long date = list.getDate();
                        String transformedDate = new SimpleDateFormat("dd MMM yyyy  hh:mm").format(new Date(date * 1000));

                        double tempAvr = (list.getTemp().getMin() + list.getTemp().getMax()) / 2;
                        int transformedTempAvr = (int) tempAvr;

                        String weatherDescription = list.getWeather().get(0).getDescription();

                        String weatherIcon = list.getWeather().get(0).getIcon();

                        mRetrofitExampleList.add(new RetrofitExampleItem(transformedDate, transformedTempAvr, weatherDescription, weatherIcon));
                    }

                    mRetrofitExampleAdapter = new RetrofitExampleAdapter(RetrofitMainActivity.this, mRetrofitExampleList);
                    mRecyclerView.setAdapter(mRetrofitExampleAdapter);
                } else {
                    tvPlace.setText(" Error ");
                    Toast.makeText(RetrofitMainActivity.this, " Maybe the place that you just typed don't exist on server or your character be wrong. Please recheck! ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DataInApi> call, Throwable throwable) {
                tvPlace.setText(throwable.getMessage());
            }
        });
    }
}