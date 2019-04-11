package com.example.android.retrofitexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.retrofitexample.model.ForecastResponse;
import com.example.android.retrofitexample.model.Weather;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherService extends Service {
    private final IBinder mBinder = new WeatherBinder();
    private ExecutorService executorService;
    private WeatherApplication weatherApplication;

    public static Intent newIntent(Context context) {
        return new Intent(context, WeatherService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newSingleThreadExecutor();
        weatherApplication = (WeatherApplication) getApplication();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void downloadDailyWeather(ServiceCallback callback) {
        weatherApplication.getForecastRepository().downloadDailyWeather(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                List<Weather> weatherList = response.body().getWeatherList().getItems();
                executorService.execute(() -> weatherApplication
                        .getLocalRepository()
                        .saveWeatherList(weatherList));
                callback.setWeatherList(weatherList);
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e(getClass().getName(), t.getMessage());
            }
        });
    }

    interface ServiceCallback {
        void setWeatherList(List<Weather> weatherList);
    }

    public class WeatherBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }
}
