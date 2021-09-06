package com.example.weatherforecast;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.core.Contract;
import com.example.weatherforecast.core.Presenter;

import java.util.List;

public class RetrofitMainActivity extends AppCompatActivity implements Contract.View {
    private Presenter mPresenter;
    private RecyclerView mRecyclerView;

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

        mPresenter = new Presenter(null, this);

        buttonClickListener();
    }

    public void buttonClickListener () {
        btGet.setOnClickListener(view -> {
            if (etPlace.getText().toString().equals("")) {
                Toast.makeText(RetrofitMainActivity.this, " Please input any place ", Toast.LENGTH_SHORT).show();
            } else {
                //String getPlace = etPlace.getText().toString().toLowerCase().replaceAll("\\s", "");
                mPresenter.getDataInApi(getApplicationContext());
            }
        });
    }

    @Override
    public void onGetDataSuccessful(TextView tvPlace, List<RetrofitExampleItem> mRetrofitExampleList) {
        RetrofitExampleAdapter mRetrofitExampleAdapter = new RetrofitExampleAdapter(RetrofitMainActivity.this, mRetrofitExampleList);
        mRecyclerView.setAdapter(mRetrofitExampleAdapter);
    }

    @Override
    public void onGetDataFailure(TextView tvPlace) {
    }
}