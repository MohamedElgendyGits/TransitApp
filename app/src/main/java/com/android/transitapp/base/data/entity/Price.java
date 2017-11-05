package com.android.transitapp.base.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Elgendy.
 */

public class Price {

    @SerializedName("currency")
    private String currency;

    @SerializedName("amount")
    private int amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return currency+" "+amount;
    }
}
