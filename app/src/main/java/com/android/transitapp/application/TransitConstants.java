package com.android.transitapp.application;

/**
 * Created by Mohamed Elgendy.
 */

public class TransitConstants {

    // Main to Detail Key intent
    public static final String DETAIL_KEY  = "detail_key";

    // json key values for route json response
    public static final String JSON_TYPE  = "type";
    public static final String JSON_PROVIDER  = "provider";
    public static final String JSON_SEGMENTS  = "segments";
    public static final String JSON_PROPERTIES  = "properties";
    public static final String JSON_PRICE  = "price";

    // json key values for all possible types retrieved from server
    public static final String KEY_PUBLIC_TRANSPORT_TYPE  = "public_transport";
    public static final String KEY_CAR_SHARING_TYPE  = "car_sharing";
    public static final String KEY_PRIVATE_BIKE_TYPE  = "private_bike";
    public static final String KEY_BIKE_SHARING_TYPE  = "bike_sharing";
    public static final String KEY_TAXI_TYPE  = "taxi";
}
