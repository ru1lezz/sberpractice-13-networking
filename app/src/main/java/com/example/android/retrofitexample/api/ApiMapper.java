package com.example.android.retrofitexample.api;

import android.util.Log;

import com.example.android.retrofitexample.model.ForecastResponse;
import com.example.android.retrofitexample.model.Weather;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ApiMapper {
    public static final String API_KEY = "36fb2efac1e148468fd131639190202";
    private RetrofitHelper retrofitHelper;

    public ApiMapper(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    public void getDailyWeatherAsync(Callback<ForecastResponse> callback) {
        retrofitHelper.getService().getDailyWeather(API_KEY, "moscow", "10", "ru").enqueue(callback);
    }

    public List<Weather> getDailyWeatherSync() throws IOException {
        Response<ForecastResponse> response = retrofitHelper.getService().getDailyWeather(API_KEY, "moscow", "10", "ru").execute();
        return response.body().getWeatherList().getItems();
    }
}
