package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/13/2017.
 */

public class EventLocation extends Location {
    @SerializedName("event_location_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
