package com.campuspedia.campuspedia.model;

/**
 * Created by misbahulard on 10/11/2017.
 */

public class Reminder {
    private int id;
    private User user;
    private Event event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
