package com.android.transitapp.main.model.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;


import com.android.transitapp.application.AppExecutors;
import com.android.transitapp.data.entity.DataResponse;
import com.android.transitapp.data.entity.Price;
import com.android.transitapp.data.entity.Route;
import com.android.transitapp.data.entity.RouteProperties;
import com.android.transitapp.data.entity.Segment;
import com.android.transitapp.data.types.parent.RouteType;
import com.android.transitapp.utils.InjectorUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.android.transitapp.application.TransitConstants.JSON_PRICE;
import static com.android.transitapp.application.TransitConstants.JSON_PROPERTIES;
import static com.android.transitapp.application.TransitConstants.JSON_PROVIDER;
import static com.android.transitapp.application.TransitConstants.JSON_SEGMENTS;
import static com.android.transitapp.application.TransitConstants.JSON_TYPE;

/**
 * Created by Mohamed Elgendy.
 */

public class RouteNetworkDataSource {

    /**
     <p>
     * This Network data source is designed retrieve data from network call using API endpoints
     *
     * NOTE for business case of this application the json response is already locally grabbed from ASSETS Folder
     */

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RouteNetworkDataSource sInstance;
    private final Context mContext;

    // LiveData storing the routes
    private final MutableLiveData<List<Route>> mRoutes;
    private final AppExecutors mExecutors;

    private RouteNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mRoutes = new MutableLiveData<>();
    }

    /**
     * Get the singleton for this class
     */
    public static RouteNetworkDataSource getInstance(Context context, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RouteNetworkDataSource(context.getApplicationContext(), executors);
            }
        }
        return sInstance;
    }

    /**
     * this method for retrieving all routes from the json response by
     * running this task in network executor
     *
     * @return the LiveData of list of routes
     */
    public LiveData<List<Route>> getRoutes() {

        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                DataResponse dataResponse = parseRoutes();
                mRoutes.postValue(dataResponse.getRoutes());
            }
        });

        return mRoutes;
    }

    /**
     * this method for parsing the routes from the json file and map to
     * DataResponse object
     *
     * @return the DataResponse object
     */
    private DataResponse parseRoutes() {

        final HashMap<String, Class> routePropertiesTypes = InjectorUtils.provideRouteTypes();
        String responseJson = loadJSONFromAsset(); // assume this the service endpoint

        DataResponse dataResponse = new GsonBuilder().registerTypeAdapter(Route.class,
                new JsonDeserializer<Route>() {

                    @Override
                    public Route deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {

                        return deserializeRoutesFromResponse(routePropertiesTypes, json);
                    }
                }).create().fromJson(responseJson, DataResponse.class);


        return dataResponse;
    }

    /**
     * this method for loading json response from asset folder
     *
     * @return json string
     */
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * this method for custom deserialization for the json response as
     * it has dynamic response results so we have to manually deserialize
     *
     * @param routePropertiesTypes  all available types to be parsed
     * @param json  json element to be deserialize
     * @return the Route Object
     */
    private Route deserializeRoutesFromResponse(HashMap<String, Class> routePropertiesTypes, JsonElement json) {

        Route route = new Route();
        Gson gson = new Gson();

        String type = json.getAsJsonObject().get(JSON_TYPE).toString().replace("\"", "");
        String provider = json.getAsJsonObject().get(JSON_PROVIDER).toString();
        String segmentJson = json.getAsJsonObject().get(JSON_SEGMENTS).toString();
        TypeToken<ArrayList<Segment>> token = new TypeToken<ArrayList<Segment>>() {
        };
        ArrayList<Segment> segments = gson.fromJson(segmentJson, token.getType());


        JsonElement propertiesElement = json.getAsJsonObject().get(JSON_PROPERTIES);
        RouteProperties routeProperties = new RouteProperties();
        RouteType routeType = null;
        if(! (propertiesElement instanceof JsonNull) ) {
            String propertiesJson = propertiesElement.toString();
            routeType = (RouteType) gson.fromJson(propertiesJson, routePropertiesTypes.get(type));
        }
        routeProperties.setRouteType(routeType);

        String priceJson = json.getAsJsonObject().get(JSON_PRICE).toString();
        Price price = gson.fromJson(priceJson, Price.class);

        route.setType(type);
        route.setProvider(provider);
        route.setSegments(segments);
        route.setProperties(routeProperties);
        route.setPrice(price);

        return route;
    }
}
