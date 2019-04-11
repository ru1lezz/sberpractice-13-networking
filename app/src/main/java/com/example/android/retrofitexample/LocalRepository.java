package com.example.android.retrofitexample;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.retrofitexample.db.WeatherDatabase;
import com.example.android.retrofitexample.model.Weather;

import java.util.List;

public class LocalRepository {
    private Context context;
    private WeatherDatabase db;

    public LocalRepository(Context context) {
        this.context = context;
        db = Room
                .databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public void saveWeatherList(List<Weather> weatherList) {
        db.getWeatherDao().setWeather(weatherList);
    }

    public Weather getWeatherByEpoch(long epoch) {
        return db.getWeatherDao().getDailyWeatherByEpoch(epoch);
    }
}
