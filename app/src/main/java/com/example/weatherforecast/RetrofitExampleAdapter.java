package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RetrofitExampleAdapter extends RecyclerView.Adapter<RetrofitExampleAdapter.ExampleViewHolder> {
    private final Context mContext;
    private final List<RetrofitExampleItem> mExampleList;

    public RetrofitExampleAdapter(Context context, List<RetrofitExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        holder.mDate.setText(mContext.getResources().getString(R.string.info_Date,date));
        holder.mTempAvr.setText(mContext.getResources().getString(R.string.info_Temp,tempAvr));
        holder.mWeather.setText(mContext.getResources().getString(R.string.info_Weather,weather));
        Picasso.get().load(urlImage).into(holder.mIcon);
    }

//    public void refreshExampleList(List<RetrofitExampleItem> mData) {
//        this.mExampleList.clear(); //Avoid duplicating data displayed on RecyclerView everytime do request
//        this.mExampleList.addAll(mData);
//    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
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
