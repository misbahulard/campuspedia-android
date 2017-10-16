package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by misbahulard on 10/16/2017.
 */

public class MainCategoryListResponse {
    @SerializedName("data")
    private ArrayList<EventMainCategory> mainCategories;
    @SerializedName("meta")
    private EventMainCategoryMeta meta;
    @SerializedName("links")
    private Link link;

    public ArrayList<EventMainCategory> getMainCategories() {
        return mainCategories;
    }

    public void setMainCategories(ArrayList<EventMainCategory> mainCategories) {
        this.mainCategories = mainCategories;
    }

    public EventMainCategoryMeta getMeta() {
        return meta;
    }

    public void setMeta(EventMainCategoryMeta meta) {
        this.meta = meta;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
