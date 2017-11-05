package com.android.transitapp.main.model;

import android.arch.lifecycle.LiveData;

import com.android.transitapp.application.AppExecutors;
import com.android.transitapp.base.data.entity.Route;
import com.android.transitapp.main.model.network.RouteNetworkDataSource;

import java.util.List;

/**
 * Created by Mohamed Elgendy.
 */

public class RouteRepository {

    /**
     <p>
     * This repository is designed to handle from which data source to retrieve data from that asked by ViewModel
     * but for business case for this application we have ONLY ONE data-source which is Network only
     *
     * NOTE all fields are dependency injected from out side to ease the unit testing purposes
     */


    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RouteRepository sInstance;
    private final RouteNetworkDataSource mRouteNetworkDataSource;
    private final AppExecutors mExecutors;

    private RouteRepository(RouteNetworkDataSource routeNetworkDataSource,
                               AppExecutors executors) {

        mRouteNetworkDataSource = routeNetworkDataSource;
        mExecutors = executors;
    }

    public synchronized static RouteRepository getInstance(RouteNetworkDataSource routeNetworkDataSource,
                                                           AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RouteRepository(routeNetworkDataSource,executors);
            }
        }
        return sInstance;
    }

    /**
     * This method is for retrieving all routes
     */
    public LiveData<List<Route>> getAllRoutes() {
        return mRouteNetworkDataSource.getRoutes();
    }
}
