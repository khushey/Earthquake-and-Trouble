package com.example.earthquakereport;

public class Earthquake {
    public Earthquake(String magnitude, String location, String date)
    {
        this.date = date;
        this.magnitude = magnitude;
        this.location = location;
    }

    public String getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }

    private String magnitude;
    private String location;
    private String date;
}
