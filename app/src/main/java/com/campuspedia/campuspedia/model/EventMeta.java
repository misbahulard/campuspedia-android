package com.campuspedia.campuspedia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by misbahulard on 10/13/2017.
 */

public class EventMeta extends BaseMeta {
    @SerializedName("event_img_path")
    private String eventImgPath;
    @SerializedName("current_page")
    private String currentPage;
    @SerializedName("from")
    private int from;
    @SerializedName("last_page")
    private int lastPage;
    @SerializedName("path")
    private String path;
    @SerializedName("per_page")
    private String perPage;
    @SerializedName("to")
    private int to;
    @SerializedName("total")
    private int total;

    public String getEventImgPath() {
        return eventImgPath;
    }

    public void setEventImgPath(String eventImgPath) {
        this.eventImgPath = eventImgPath;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
