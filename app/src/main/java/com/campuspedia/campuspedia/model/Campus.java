package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Campus {
    @SerializedName("campus_id")
    private int campusid;
    @SerializedName("location")
    private Location location;
    @SerializedName("name")
    private String name;
    @SerializedName("web")
    private String web;
    @SerializedName("logo")
    private Photo logo;

    public int getCampusid() {
        return campusid;
    }

    public void setCampusid(int campusid) {
        this.campusid = campusid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Photo getLogo() {
        return logo;
    }

    public void setLogo(Photo logo) {
        this.logo = logo;
    }
}
