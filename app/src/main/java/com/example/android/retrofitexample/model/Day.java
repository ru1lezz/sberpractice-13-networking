package com.example.android.retrofitexample.model;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("maxtemp_c")
    private double maxTemp;
    @SerializedName("mintemp_c")
    private double minTemp;
    @SerializedName("maxwind_kph")
    private double maxWind;
    @SerializedName("avgHumidity")
    private double avgHumidity;
    @Embedded
    @SerializedName("condition")
    private Condition condition;

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(double maxWind) {
        this.maxWind = maxWind;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
