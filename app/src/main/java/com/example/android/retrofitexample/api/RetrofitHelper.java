package com.example.android.retrofitexample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final String BASE_URL = "http://api.apixu.com/v1/";
    public static final int MOSCOW_ID = 524901;

    WeatherService getService() {
        final Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(WeatherService.class);
    }
}
