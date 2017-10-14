package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class EventCategory {
    @SerializedName("category_id")
    private int id;
    @SerializedName("main_category_id")
    private EventMainCategory mainCategory;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventMainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(EventMainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
