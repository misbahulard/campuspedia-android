package com.campuspedia.campuspedia.model;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class EventCategory {
    private int id;
    private EventMainCategory mainCategory;
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
