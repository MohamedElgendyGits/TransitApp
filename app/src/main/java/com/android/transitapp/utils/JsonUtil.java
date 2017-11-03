package com.android.transitapp.utils;

import com.google.gson.Gson;

/**
 * Created by Mohamed Elgendy.
 */

public class JsonUtil {

    public static <T> T parseJson(String jsonString, Class<T> clazz) {

        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, clazz);

        } catch (Exception e) {
            throw e;
        }

    }

    public static String objectToString(Object clazz) {
        Gson gson = new Gson();
        return gson.toJson(clazz);

    }
}
