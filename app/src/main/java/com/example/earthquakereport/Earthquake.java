package com.example.earthquakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private Date dateObject;
    private SimpleDateFormat dateFormatter;
    private DecimalFormat decimalFormatter;
    private Double magnitude;
    private String location;
    private String date;
    private String time;


    public Earthquake(Double magnitude, String location, Long timeInMilliseconds)
    {
        dateObject = new Date(timeInMilliseconds);
        this.magnitude = magnitude;
        this.location = location;
    }

    public String getMagnitude(){
        decimalFormatter = new DecimalFormat("0.0");
        return decimalFormatter.format(magnitude);
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
