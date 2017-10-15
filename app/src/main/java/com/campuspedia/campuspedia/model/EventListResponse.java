package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misbahulard on 10/12/2017.
 */

public class EventListResponse {
    @SerializedName("data")
    private ArrayList<Event> events;
    @SerializedName("meta")
    private EventMeta meta;
    @SerializedName("links")
    private Link link;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public EventMeta getMeta() {
        return meta;
    }

    public void setMeta(EventMeta meta) {
        this.meta = meta;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
