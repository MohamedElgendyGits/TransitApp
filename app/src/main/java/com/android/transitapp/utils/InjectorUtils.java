package com.android.transitapp.utils;

import android.content.Context;

import com.android.transitapp.application.AppExecutors;
import com.android.transitapp.data.types.BikeSharingType;
import com.android.transitapp.data.types.CarSharingType;
import com.android.transitapp.data.types.PrivateBikeType;
import com.android.transitapp.data.types.PublicTransportType;
import com.android.transitapp.data.types.TaxiType;
import com.android.transitapp.main.model.RouteRepository;
import com.android.transitapp.main.model.network.RouteNetworkDataSource;
import com.android.transitapp.main.viewmodel.MainViewModelFactory;

import java.util.HashMap;

import static com.android.transitapp.application.TransitConstants.KEY_BIKE_SHARING_TYPE;
import static com.android.transitapp.application.TransitConstants.KEY_CAR_SHARING_TYPE;
import static com.android.transitapp.application.TransitConstants.KEY_PRIVATE_BIKE_TYPE;
import static com.android.transitapp.application.TransitConstants.KEY_PUBLIC_TRANSPORT_TYPE;
import static com.android.transitapp.application.TransitConstants.KEY_TAXI_TYPE;

/**
 * Created by Mohamed Elgendy.
 */

/**
 * Provides static methods to inject the various classes needed for Routes
 */
public class InjectorUtils {

    public static RouteRepository provideRouteRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        RouteNetworkDataSource routeNetworkDataSource =
                RouteNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return RouteRepository.getInstance(routeNetworkDataSource, executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        RouteRepository repository = provideRouteRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

    public static HashMap<String, Class> provideRouteTypes() {

        // adding here all possible types that may server reply with
        HashMap<String, Class> routePropertiesTypes = new HashMap<>();
        routePropertiesTypes.put(KEY_PUBLIC_TRANSPORT_TYPE, PublicTransportType.class);
        routePropertiesTypes.put(KEY_CAR_SHARING_TYPE, CarSharingType.class);
        routePropertiesTypes.put(KEY_PRIVATE_BIKE_TYPE, PrivateBikeType.class);
        routePropertiesTypes.put(KEY_BIKE_SHARING_TYPE, BikeSharingType.class);
        routePropertiesTypes.put(KEY_TAXI_TYPE, TaxiType.class);

        return routePropertiesTypes;
    }
}
