package com.campuspedia.campuspedia.response;

import com.campuspedia.campuspedia.model.EventCategory;
import com.campuspedia.campuspedia.model.Link;
import com.campuspedia.campuspedia.model.MetaWithoutImg;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by misbahulard on 10/16/2017.
 */

public class EventCategoryListResponse {
    @SerializedName("data")
    private ArrayList<EventCategory> eventCategories;
    @SerializedName("meta")
    private MetaWithoutImg meta;
    @SerializedName("links")
    private Link link;

    public ArrayList<EventCategory> getEventCategories() {
        return eventCategories;
    }

    public void setEventCategories(ArrayList<EventCategory> eventCategories) {
        this.eventCategories = eventCategories;
    }

    public MetaWithoutImg getMeta() {
        return meta;
    }

    public void setMeta(MetaWithoutImg meta) {
        this.meta = meta;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
