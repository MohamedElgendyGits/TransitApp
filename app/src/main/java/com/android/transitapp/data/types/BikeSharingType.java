package com.android.transitapp.data.types;

import com.android.transitapp.data.types.parent.RouteType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Elgendy.
 */

public class BikeSharingType extends RouteType {

    @SerializedName("id")
    private String id;

    @SerializedName("available_bikes")
    private int availableBikes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    @Override
    public String printClassName() {
        return this.getClass().getSimpleName();
    }
}
