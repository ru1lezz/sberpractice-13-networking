package com.example.android.retrofitexample.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.android.retrofitexample.model.WeatherList;
import com.example.android.retrofitexample.model.Weather;

import java.util.List;

@Dao
public abstract class WeatherDao {
    @Query("SELECT * FROM weather")
    public abstract List<Weather> getDailyWeather();

    @Query("SELECT * FROM weather WHERE epoch = :epoch")
    public abstract Weather getDailyWeatherByEpoch(long epoch);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<Weather> weatherList);

    @Query("DELETE FROM Weather")
    public abstract void clear();

    @Transaction
    public void setWeather(List<Weather> weatherList) {
        clear();
        insert(weatherList);
    }
}
