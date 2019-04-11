package com.example.android.retrofitexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofitexample.model.Weather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private final Context mContext;
    private List<Weather> mWeatherList;

    public WeatherAdapter(Context mContext) {
        this.mContext = mContext;
        mWeatherList = new ArrayList<>();
    }

    public void setWeatherList(List<Weather> weatherList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(mWeatherList, weatherList));
        diffResult.dispatchUpdatesTo(this);
        mWeatherList.clear();
        mWeatherList.addAll(weatherList);
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.forecast_item, viewGroup, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder weatherHolder, int i) {
        weatherHolder.bind(mWeatherList.get(i));
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }


    public class WeatherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView mWeatherDate, mMaxTemperature, mLowTemperature, mWeatherDescription;
        private Weather mWeather;

        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
            itemView.setOnClickListener(this);
        }

        private void initViews(View itemView) {
            mWeatherDate = itemView.findViewById(R.id.weather_date_text_view);
            mMaxTemperature = itemView.findViewById(R.id.weather_max_temperature_text_view);
            mLowTemperature = itemView.findViewById(R.id.weather_low_temperature_text_view);
            mWeatherDescription = itemView.findViewById(R.id.weather_description_text_view);
            imageView = itemView.findViewById(R.id.weather_icon);
        }

        public void bind(Weather weather) {
            mWeather = weather;
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
            mMaxTemperature.setText(String.valueOf(mWeather.getDay().getMaxTemp()));
            mLowTemperature.setText(String.valueOf(mWeather.getDay().getMinTemp()));
            mWeatherDescription.setText(mWeather.getDay().getCondition().getText());
            mWeatherDate.setText(sdf.format(new Date(mWeather.getEpoch() * 1000)));
            String url = "http:" + mWeather.getDay().getCondition().getIconUrl();
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(WeatherActivity.newIntent(v.getContext(), mWeather.getEpoch()));
        }
    }
}
