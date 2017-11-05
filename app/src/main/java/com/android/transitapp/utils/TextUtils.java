package com.android.transitapp.utils;

import android.support.annotation.StringRes;

import com.android.transitapp.application.app.TransitApp;

/**
 * Created by Mohamed Elgendy.
 */

public class TextUtils {

    private static final String EMPTY_STRING_PATTERN = "^$|\\s+";

    public static String getString(@StringRes int resId) {
        return TransitApp.getInstance().getString(resId);
    }

    public static boolean isEmptyString(String str) {
        if (str == null || str.length() == 0 ||
                str.matches(EMPTY_STRING_PATTERN)) {
            return true;
        }
        return false;
    }


}
