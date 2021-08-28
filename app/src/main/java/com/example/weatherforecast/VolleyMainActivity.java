package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VolleyMainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private VolleyExampleAdapter mVolleyExampleAdapter;
    private ArrayList<VolleyExampleItem> mVolleyExampleList;
    private RequestQueue mRequestQueue;

    public TextView tvPlace;
    public Button btGet;
    public EditText etPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_activity_main);

        mRecyclerView = findViewById(R.id.rvItems);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvPlace = findViewById(R.id.tvPlace);
        btGet = findViewById(R.id.btGet);
        etPlace = findViewById(R.id.etPlace);

        mVolleyExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPlace.getText().toString().equals("")) {
                    Toast.makeText(VolleyMainActivity.this, " Please input any place ", Toast.LENGTH_SHORT).show();
                } else {
                    String getPlace = etPlace.getText().toString().toLowerCase().replaceAll("\\s", "");
                    parseJSON(getPlace);
                }
            }
        });
    }

    public void parseJSON(String getPlace) {
        mVolleyExampleList.clear();
        String baseUrl = "https://api.openweathermap.org/data/2.5/forecast/daily?units=metric&cnt=7&appid=60c6fbeb4b93ac653c492ba806fc346d&q=";
        String URL = baseUrl + getPlace;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tvPlace.setText(response.getJSONObject("city").getString("name"));
                            JSONArray jsonArray = response.getJSONArray("list");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject list = jsonArray.getJSONObject(i);
                                JSONObject tempINlist = list.getJSONObject("temp");

                                JSONArray weatherArray = list.getJSONArray("weather");
                                JSONObject dataWeather = weatherArray.getJSONObject(0);

                                long date = list.getLong("dt");
                                String transformedDate = new SimpleDateFormat("dd MMM yyyy  hh:mm").format(new Date(date * 1000));
                                double tempAvr = (tempINlist.getDouble("min") + tempINlist.getDouble("max")) / 2;
                                String weather = dataWeather.getString("description");

                                mVolleyExampleList.add(new VolleyExampleItem(transformedDate, tempAvr, weather));
                            }

                            mVolleyExampleAdapter = new VolleyExampleAdapter(VolleyMainActivity.this, mVolleyExampleList);
                            mRecyclerView.setAdapter(mVolleyExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(VolleyMainActivity.this, " Maybe the place that you just typed don't exist on server or your character be wrong. Please recheck! ", Toast.LENGTH_LONG).show();

            }
        });

        mRequestQueue.add(request);
    }
}