package com.android.transitapp.main.model;

import android.arch.lifecycle.LiveData;

import com.android.transitapp.base.AppExecutors;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.main.model.network.RouteNetworkDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohamed Elgendy.
 */

public class RouteRepository {

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

    public LiveData<List<Route>> getAllRoutes() {
        return mRouteNetworkDataSource.getRoutes();
    }
}
