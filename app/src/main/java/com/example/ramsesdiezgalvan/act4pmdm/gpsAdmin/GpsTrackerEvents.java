package com.example.ramsesdiezgalvan.act4pmdm.gpsAdmin;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by ramsesdiezgalvan on 15/1/18.
 */

public class GpsTrackerEvents implements LocationListener{
    GpsTracker gpsTracker;

    public GpsTrackerEvents(GpsTracker gpsTracker) {
        this.gpsTracker = gpsTracker;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
