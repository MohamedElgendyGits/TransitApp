package com.android.transitapp.base.data.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Elgendy
 */

public class Provider {

    @Nullable
    @SerializedName("provider_icon_url")
    private String providerIcon;

    @Nullable
    @SerializedName("disclaimer")
    private String disclaimer;

    @Nullable
    @SerializedName("ios_itunes_url")
    private String iosTunesUrl;

    @Nullable
    @SerializedName("ios_app_url")
    private String iosAppUrl;

    @Nullable
    @SerializedName("android_package_name")
    private String androidPackageName;

    @Nullable
    @SerializedName("display_name")
    private String displayName;

    @Nullable
    public String getProviderIcon() {
        return providerIcon;
    }

    public void setProviderIcon(@Nullable String providerIcon) {
        this.providerIcon = providerIcon;
    }

    @Nullable
    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(@Nullable String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @Nullable
    public String getIosTunesUrl() {
        return iosTunesUrl;
    }

    public void setIosTunesUrl(@Nullable String iosTunesUrl) {
        this.iosTunesUrl = iosTunesUrl;
    }

    @Nullable
    public String getIosAppUrl() {
        return iosAppUrl;
    }

    public void setIosAppUrl(@Nullable String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
    }

    @Nullable
    public String getAndroidPackageName() {
        return androidPackageName;
    }

    public void setAndroidPackageName(@Nullable String androidPackageName) {
        this.androidPackageName = androidPackageName;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
    }
}
