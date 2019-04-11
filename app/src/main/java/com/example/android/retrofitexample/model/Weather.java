package com.example.android.retrofitexample.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Weather {
    @PrimaryKey
    @SerializedName("date_epoch")
    private long epoch;
    @SerializedName("date")
    private String date;
    @Embedded
    @SerializedName("day")
    Day day;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return epoch == weather.epoch &&
                Objects.equals(date, weather.date) &&
                Objects.equals(day, weather.day);
    }

    @Override
    public int hashCode() {

        return Objects.hash(epoch, date, day);
    }
}
