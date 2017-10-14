package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Location {
    @SerializedName("street_address")
    private String streetAddress;
    @SerializedName("postal_code")
    private int postalCode;
    @SerializedName("city")
    private String city;
    @SerializedName("state_province")
    private String stateProvince;
    @SerializedName("latitude")
    private double latitute;
    @SerializedName("longtitude")
    private double langtitude;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLangtitude() {
        return langtitude;
    }

    public void setLangtitude(double langtitude) {
        this.langtitude = langtitude;
    }
}
