package com.campuspedia.campuspedia.model;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Location {
    private int id;
    private String streetAddress;
    private int postalCode;
    private String city;
    private String stateProvince;
    private double latitute;
    private double langtitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
