package com.example.ramsesdiezgalvan.act4pmdm;

import android.util.Log;

import com.example.ramsesdiezgalvan.act4pmdm.entity.Locations;
import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdmin;
import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdminListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by ramsesdiezgalvan on 11/1/18.
 */

public class SecondActivityEvents implements OnMapReadyCallback, FireBaseAdminListener, GoogleMap.OnMarkerClickListener {


    SecondActivity secondActivity;
    GoogleMap mMap;
    FireBaseAdmin fireBaseAdmin;
    ArrayList<Locations> location;

    SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity = secondActivity;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        secondActivity.fireBaseAdmin.downAndObserveBranch("Locations");
    }

    @Override
    public void logInOk(boolean ok) {

    }

    @Override
    public void signOutOk(boolean ok) {

    }

    @Override
    public void fireBaseDownloadBranch(String branch, DataSnapshot dataSnapshot) {
        GenericTypeIndicator<ArrayList<Locations>> indicator = new GenericTypeIndicator<ArrayList<Locations>>() {
        };
        location = dataSnapshot.getValue(indicator);
        addPins();

    }

    public void removeOldPins(){
        for (int i = 0;i<location.size();i++){
            Locations locationTemp = location.get(i);
            if(locationTemp!=null){

            }
        }
    }

    public void addPins(){
        for (int i = 0;i<location.size();i++){

            Locations locationTemp = location.get(i);
            System.out.println("Location lat: "+locationTemp.lat);
            System.out.println("Location lon: "+locationTemp.lon);
            System.out.println("Location name: "+locationTemp.name);
            LatLng locationPos = new LatLng(locationTemp.lat, locationTemp.lon);
            mMap.addMarker(new MarkerOptions().position(locationPos).title(locationTemp.name)).setTag(locationTemp);

            // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
    //metodo que detecta si un pin esta clickado
    @Override
    public boolean onMarkerClick(Marker marker) {

        Locations location = (Locations) marker.getTag();
        return false;
    }
}
