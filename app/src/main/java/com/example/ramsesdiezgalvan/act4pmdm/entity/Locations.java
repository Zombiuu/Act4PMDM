package com.example.ramsesdiezgalvan.act4pmdm.entity;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramsesdiezgalvan on 11/1/18.
 */

public class Locations {

    public double lat, lon;
    public String name;
    public Marker marker;
    public String poblation;
    public String country;

    public Locations() {

    }

    public Locations(double lat, double lon, String name, String country, String poblation) {

        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.poblation = poblation;
        this.country = country;

    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("country", country);
        result.put("lat", lat);
        result.put("lon", lon);
        result.put("name", name);
        result.put("poblation", poblation);

        return result;
    }
}
