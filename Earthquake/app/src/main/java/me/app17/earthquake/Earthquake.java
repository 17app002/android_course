package me.app17.earthquake;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Earthquake {

    private String id;
    private Date date;
    private String details;
    private Location location;
    private double magnitude;
    private String link;

    public Earthquake(String id, Date date, String details, Location location, double magnitude, String link) {
        this.id = id;
        this.date = date;
        this.details = details;
        this.location = location;
        this.magnitude = magnitude;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public Location getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm", Locale.TAIWAN);
        String dateString = simpleDateFormat.format(date);
        return dateString + ": " + magnitude + " " + details;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Earthquake) {
            if (((Earthquake) o).getId().equals(id)) {
                return true;
            }
        }

        return false;
    }


}
