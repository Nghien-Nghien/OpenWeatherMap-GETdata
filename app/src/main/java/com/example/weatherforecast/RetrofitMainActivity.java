package com.example.weatherforecast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.core.Contract;
import com.example.weatherforecast.core.RetrofitMainPresenter;

import java.util.List;

public class RetrofitMainActivity extends AppCompatActivity implements Contract.View {
    private Contract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private RetrofitExampleAdapter mRetrofitExampleAdapter;

    public TextView tvPlace;
    public Button btGet;
    public EditText etPlace;
    public ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

        onButtonClickListener();

        mPresenter = new RetrofitMainPresenter(RetrofitMainActivity.this);

        mRetrofitExampleAdapter = new RetrofitExampleAdapter(RetrofitMainActivity.this);
        mRecyclerView.setAdapter(mRetrofitExampleAdapter);
    }

    public void onButtonClickListener () {
        btGet.setOnClickListener(view -> mPresenter.callWeatherMapApi(etPlace.getText().toString()));
    }

    @Override
    public void onResponseSuccessful(String place, List<RetrofitExampleItem> mRetrofitExampleList) {
        tvPlace.setText(place);
        mRetrofitExampleAdapter.refreshExampleList(mRetrofitExampleList);
    }

    @Override
    public void onResponseFail() {
        tvPlace.setText(getResources().getString(R.string.error));
    }

    @Override
    public void onFailure(String errorCode) {
        Toast.makeText(RetrofitMainActivity.this, errorCode, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPlaceIsEmpty() {
        Toast.makeText(RetrofitMainActivity.this, "Please input any place", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        tvPlace.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        tvPlace.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}