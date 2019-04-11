package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResponse {
    @SerializedName("forecast")
    private WeatherList weatherList;

    public WeatherList getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(WeatherList weatherList) {
        this.weatherList = weatherList;
    }
}
