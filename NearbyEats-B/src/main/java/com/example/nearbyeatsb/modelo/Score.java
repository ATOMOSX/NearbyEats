package com.example.nearbyeatsb.modelo;

import java.util.Date;

public class Score {

    private String id;
    private double rating;
    private Date publicationDate;
    private User user;
    private Location location;

    public Score(String id, double rating, Date publicationDate, User user, Location location) {
        this.id = id;
        this.rating = rating;
        this.publicationDate = publicationDate;
        this.user = user;
        this.location = location;
    }

    public Score() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
