package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Campus {
    @SerializedName("campus_id")
    private int campusid;
    @SerializedName("name")
    private String name;
    @SerializedName("web")
    private String web;
    @SerializedName("logo")
    private String logo;
    @SerializedName("location")
    private CampusLocation location;

    public int getCampusid() {
        return campusid;
    }

    public void setCampusid(int campusid) {
        this.campusid = campusid;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CampusLocation getLocation() {
        return location;
    }

    public void setLocation(CampusLocation location) {
        this.location = location;
    }
}
