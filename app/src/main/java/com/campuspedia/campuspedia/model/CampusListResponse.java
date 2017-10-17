package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by misbahulard on 10/17/2017.
 */

public class CampusListResponse {
    @SerializedName("data")
    private ArrayList<Campus> campuses;
    @SerializedName("meta")
    private CampusMeta meta;
    @SerializedName("links")
    private Link link;

    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }

    public CampusMeta getMeta() {
        return meta;
    }

    public void setMeta(CampusMeta meta) {
        this.meta = meta;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
