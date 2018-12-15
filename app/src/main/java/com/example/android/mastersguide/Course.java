package com.example.android.mastersguide;

public class Course {

    private String name;
    private String country;
    private String cost;
    private String university;
    private String duration;
    private String url;
    private String image;

    public Course(String name, String country, String cost, String university, String duration, String url, String image) {
        this.name = name;
        this.country = country;
        this.cost = cost;
        this.university = university;
        this.duration = duration;
        this.url = url;
        this.image = image;
    }

    public Course(String name, String country, String cost, String university, String duration) {
        this.name = name;
        this.country = country;
        this.cost = cost;
        this.university = university;
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
