package com.example.android.retrofitexample.api;

import com.example.android.retrofitexample.model.ForecastResponse;
import com.example.android.retrofitexample.model.WeatherList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET(value = "forecast.json")
    Call<ForecastResponse> getDailyWeather(@Query("key") String key, @Query("q") String name, @Query("days") String days, @Query("lang") String lang);
}

