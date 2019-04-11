package com.example.android.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Condition {
    @SerializedName("text")
    private String text;
    @SerializedName("icon")
    private String iconUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(text, condition.text) &&
                Objects.equals(iconUrl, condition.iconUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, iconUrl);
    }
}
