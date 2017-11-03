package com.android.transitapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Mohamed Elgendy.
 */

public class DataResponse {

    @SerializedName("routes")
    private ArrayList<Route> routes;

    @SerializedName("provider_attributes")
    private Map<String, Provider> providers;

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public Map<String, Provider> getProviders() {
        return providers;
    }

    public void setProviders(Map<String, Provider> providers) {
        this.providers = providers;
    }
}
