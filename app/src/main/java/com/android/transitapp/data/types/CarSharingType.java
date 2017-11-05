package com.android.transitapp.data.types;

import android.support.annotation.Nullable;

import com.android.transitapp.data.types.parent.RouteType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Elgendy.
 */

public class CarSharingType extends RouteType {

    @SerializedName("address")
    private String address;

    @SerializedName("model")
    private String model;

    @SerializedName("license_plate")
    private String licencePlate;

    @SerializedName("fuel_level")
    private int fuelLevel;

    @SerializedName("engine_type")
    private String engineType;

    @SerializedName("internal_cleanliness")
    private String internalCleanliness;

    @Nullable
    @SerializedName("description")
    private String description;

    @SerializedName("seats")
    private int seats;

    @SerializedName("doors")
    private int doors;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getInternalCleanliness() {
        return internalCleanliness;
    }

    public void setInternalCleanliness(String internalCleanliness) {
        this.internalCleanliness = internalCleanliness;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

}
