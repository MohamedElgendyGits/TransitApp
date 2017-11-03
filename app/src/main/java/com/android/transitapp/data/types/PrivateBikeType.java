package com.android.transitapp.data.types;

import com.android.transitapp.data.types.parent.RouteType;

/**
 * Created by Mohamed Elgendy.
 */

public class PrivateBikeType extends RouteType {
    @Override
    public String printClassName() {
        return this.getClass().getSimpleName();
    }
}
