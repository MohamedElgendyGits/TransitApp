package com.android.transitapp.data.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed Elgendy.
 */

public class Route {

    @SerializedName("type")
    private String type;

    @SerializedName("provider")
    private String provider;

    @SerializedName("segments")
    private ArrayList<Segment> segments;

    @Nullable
    @SerializedName("properties")
    private RouteProperties properties;

    @Nullable
    @SerializedName("price")
    private Price price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

    @Nullable
    public RouteProperties getProperties() {
        return properties;
    }

    public void setProperties(@Nullable RouteProperties properties) {
        this.properties = properties;
    }

    @Nullable
    public Price getPrice() {
        return price;
    }

    public void setPrice(@Nullable Price price) {
        this.price = price;
    }
}
