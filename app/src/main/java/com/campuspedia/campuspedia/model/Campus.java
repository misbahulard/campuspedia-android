package com.campuspedia.campuspedia.model;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Campus {
    private int campusid;
    private Location location;
    private String name;
    private String web;
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
