package com.example.admin.pet_managing.Model;

public class Tracking {
    private String email,name,lat,lng;

    public Tracking() {
    }

    public Tracking(String email, String name, String lat, String lng) {
        this.email = email;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
