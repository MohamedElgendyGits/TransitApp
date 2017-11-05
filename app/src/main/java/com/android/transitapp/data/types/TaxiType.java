package com.android.transitapp.data.types;

import com.android.transitapp.data.entity.Company;
import com.android.transitapp.data.types.parent.RouteType;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Mohamed Elgendy.
 */

public class TaxiType extends RouteType {

    @SerializedName("companies")
    private ArrayList<Company> companies;

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }
}
