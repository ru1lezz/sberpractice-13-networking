package com.example.android.retrofitexample;

import android.app.Application;

public class WeatherApplication extends Application {
    private ForecastRepository forecastRepository;
    private LocalRepository localRepository;

    public ForecastRepository getForecastRepository() {
        return forecastRepository;
    }

    public LocalRepository getLocalRepository() { return localRepository; }

    @Override
    public void onCreate() {
        super.onCreate();
        forecastRepository = new ForecastRepository();
        localRepository = new LocalRepository(getApplicationContext());
    }
}
