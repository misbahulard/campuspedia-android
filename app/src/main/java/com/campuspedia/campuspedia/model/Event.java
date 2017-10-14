package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Event {

    @SerializedName("event_id")
    private int eventId;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("photo")
    private String photo;
    @SerializedName("event_date")
    private Date eventDate;
    @SerializedName("main_category")
    private String mainCategory;
    @SerializedName("category")
    private String category;
    @SerializedName("campus")
    private String campus;
    @SerializedName("location")
    private EventLocation location;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }
}
