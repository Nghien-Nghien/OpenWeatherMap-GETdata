package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btVolley;
    Button btRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btVolley = findViewById(R.id.btVolley);
        btRetrofit = findViewById(R.id.btRetrofit);

        btVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVolley();
            }
        });

        btRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRetrofit();
            }
        });
    }

    public void openVolley() {
        Intent intent = new Intent(this, VolleyMainActivity.class);
        startActivity(intent);
    }

    public void openRetrofit() {
        Intent intent = new Intent(this, RetrofitMainActivity.class);
        startActivity(intent);
    }
}