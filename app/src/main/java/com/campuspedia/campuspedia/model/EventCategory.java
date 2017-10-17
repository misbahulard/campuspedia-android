package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class EventCategory {
    @SerializedName("category_id")
    private int id;
    /**
     * TODO: Cek ini ya nanti ngaruh gak ke yang lain
     */
    @SerializedName("main_category_id")
    // private EventMainCategory mainCategory;
    private int mainCategoryId;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
