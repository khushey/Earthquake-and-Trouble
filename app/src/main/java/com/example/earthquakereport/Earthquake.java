package com.example.earthquakereport;

import android.util.Log;
import android.util.Pair;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private final static String TAG = "Earthquake";
    private Date dateObject;
    private SimpleDateFormat dateFormatter;
    private DecimalFormat decimalFormatter;
    private Double magnitude;
    private String location;
    private String date;
    private String time;
    private String URL;


    public Earthquake(Double magnitude, String location, Long timeInMilliseconds, String URL)
    {
        dateObject = new Date(timeInMilliseconds);
        this.magnitude = magnitude;
        this.location = location;
        this.URL = URL;
    }

    public String getURL(){
        return URL;

    }
    public Pair<Integer, String> getMagnitude(){
        decimalFormatter = new DecimalFormat("0.0");
        int magInt = (int) Math.floor(magnitude);
        String magString = decimalFormatter.format(magnitude);
        return new Pair<>(magInt, magString); //return object of Pair
    }
    public String getLocation(){
        return location;
    }

    public String fotmatDate(){
        dateFormatter = new SimpleDateFormat("MMM DD, YYYY");
        date = dateFormatter.format(dateObject);
        return date;
    }

    public String formatTime(){
        dateFormatter = new SimpleDateFormat("h:mm a");
        time = dateFormatter.format(dateObject);
        return time;
    }
}
