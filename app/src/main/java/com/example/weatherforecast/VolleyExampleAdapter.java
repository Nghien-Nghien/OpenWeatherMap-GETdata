package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VolleyExampleAdapter extends RecyclerView.Adapter<VolleyExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<VolleyExampleItem> mExampleList;

    public VolleyExampleAdapter(Context context, ArrayList<VolleyExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.volley_example_item, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        VolleyExampleItem item = mExampleList.get(position);

        String date = item.getmDate();
        Double tempAvr = item.getmTempAvr();
        String weather = item.getmWeather();

        holder.mDate.setText("Date: " + date);
        holder.mTempAvr.setText("Temp Avr: " + tempAvr + "°C");
        holder.mWeather.setText("Weather: " + weather);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mTempAvr;
        public TextView mWeather;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.tvDate);
            mTempAvr = itemView.findViewById(R.id.tvTempAvr);
            mWeather = itemView.findViewById(R.id.tvWeather);
        }
    }
}
