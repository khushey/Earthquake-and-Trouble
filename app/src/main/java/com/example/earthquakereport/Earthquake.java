package com.example.earthquakereport;

public class Earthquake {
    public Earthquake(String date, double magnitude, String location)
    {
        this.date = date;
        this.magnitude = magnitude;
        this.location = location;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }

    private double magnitude;
    private String location;
    private String date;
}
