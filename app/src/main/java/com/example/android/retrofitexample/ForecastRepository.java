package com.example.android.retrofitexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.android.retrofitexample.api.ApiMapper;
import com.example.android.retrofitexample.api.RetrofitHelper;
import com.example.android.retrofitexample.db.WeatherDatabase;
import com.example.android.retrofitexample.model.ForecastResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastRepository {
    private ApiMapper mApiMapper;

    public ForecastRepository() {
        mApiMapper = new ApiMapper(new RetrofitHelper());
    }

    public void downloadDailyWeather(Callback<ForecastResponse> callback) {
         mApiMapper.getDailyWeatherAsync(callback);
    }
}
