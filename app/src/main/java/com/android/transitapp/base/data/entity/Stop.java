package com.android.transitapp.data.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Elgendy.
 */

public class Stop {


    @Nullable
    @SerializedName("lat")
    private float latitude;

    @Nullable
    @SerializedName("lng")
    private float longitude;

    @Nullable
    @SerializedName("datetime")
    private String date;

    @Nullable
    @SerializedName("name")
    private String name;

    @Nullable
    @SerializedName("properties")
    private String properties;

    @Nullable
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(@Nullable float latitude) {
        this.latitude = latitude;
    }

    @Nullable
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(@Nullable float longitude) {
        this.longitude = longitude;
    }

    @Nullable
    public String getDate() {
        return date;
    }

    public void setDate(@Nullable String date) {
        this.date = date;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getProperties() {
        return properties;
    }

    public void setProperties(@Nullable String properties) {
        this.properties = properties;
    }
}
