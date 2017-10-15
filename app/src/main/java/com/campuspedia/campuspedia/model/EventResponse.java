package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/14/2017.
 */

public class EventResponse {
    @SerializedName("data")
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
