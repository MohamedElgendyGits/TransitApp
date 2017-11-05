package com.android.transitapp.data.entity;

import android.support.annotation.Nullable;

import com.android.transitapp.utils.Conversions;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohamed Elgendy.
 */

public class Segment {

    @Nullable
    @SerializedName("name")
    private String name;

    @Nullable
    @SerializedName("num_stops")
    private int numberOfStops;

    @Nullable
    @SerializedName("stops")
    private ArrayList<Stop> stops;

    @Nullable
    @SerializedName("travel_mode")
    private String travelMode;

    @Nullable
    @SerializedName("description")
    private String description;

    @Nullable
    @SerializedName("color")
    private String color;

    @Nullable
    @SerializedName("icon_url")
    private String iconUrl;

    @Nullable
    @SerializedName("polyline")
    private String polyline;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(@Nullable int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    @Nullable
    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(@Nullable ArrayList<Stop> stops) {
        this.stops = stops;
    }

    @Nullable
    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(@Nullable String travelMode) {
        this.travelMode = travelMode;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getColor() {
        return color;
    }

    public void setColor(@Nullable String color) {
        this.color = color;
    }

    @Nullable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(@Nullable String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Nullable
    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(@Nullable String polyline) {
        this.polyline = polyline;
    }

    public int getSegmentDuration(){

        ArrayList<String> stopDates = new ArrayList<>();

        for(Stop stop : stops){
            stopDates.add(stop.getDate());
        }

        int segmentDuration = 0;
        for(int i=stopDates.size()-1; i>=0; i--){
            if(i == 0)
                break;

            long difference = Conversions.convertToDate(stopDates.get(i-1)).getTime() -
                    Conversions.convertToDate(stopDates.get(i)).getTime();
            segmentDuration += (int) Math.abs(TimeUnit.MILLISECONDS.toMinutes(difference));
        }

        return segmentDuration;
    }
}
