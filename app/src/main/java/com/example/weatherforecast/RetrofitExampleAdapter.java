package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RetrofitExampleAdapter extends RecyclerView.Adapter<RetrofitExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<RetrofitExampleItem> mExampleList;

    public RetrofitExampleAdapter(Context context, ArrayList<RetrofitExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.retrofit_example_item, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        RetrofitExampleItem item = mExampleList.get(position);

        String date = item.getmDate();
        Integer tempAvr = item.getmTempAvr();
        String weather = item.getmWeather();
        String urlImage = "http://openweathermap.org/img/w/" + item.getmIcon() + ".png";

        holder.mDate.setText("Date: " + date);
        holder.mTempAvr.setText("Temp Avr: " + tempAvr + "°C");
        holder.mWeather.setText("Weather: " + weather);
        Picasso.get().load(urlImage).into(holder.mIcon);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mTempAvr;
        public TextView mWeather;
        public ImageView mIcon;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.tvDate);
            mTempAvr = itemView.findViewById(R.id.tvTempAvr);
            mWeather = itemView.findViewById(R.id.tvWeather);
            mIcon = itemView.findViewById(R.id.iconview);
        }
    }
}
