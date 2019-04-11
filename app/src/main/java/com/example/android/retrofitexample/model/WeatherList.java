package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherList {
    @SerializedName("forecastday")
    private List<Weather> items;

    public List<Weather> getItems() {
        return items;
    }

    public void setItems(List<Weather> items) {
        this.items = items;
    }
}
