package com.ps.rest;

/**
 * Created by iuliana.cosmina on 10/15/16.
 */
public class User {

    private String email;
    private String username;
    private Double rating;
    private boolean active;

    public User() {
    }

    public User(String username, String email, Double rating, boolean active) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "email='" + email + '\'' +
                ", rating=" + rating +
                ", active=" + active +
                '}';
    }
}
