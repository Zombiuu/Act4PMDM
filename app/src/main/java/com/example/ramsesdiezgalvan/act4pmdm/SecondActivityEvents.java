package com.example.ramsesdiezgalvan.act4pmdm;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.ramsesdiezgalvan.act4pmdm.entity.Locations;
import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdmin;
import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdminListener;
import com.google.android.gms.maps.CameraUpdate;
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

public class SecondActivityEvents implements OnMapReadyCallback, FireBaseAdminListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {


    SecondActivity secondActivity;
    GoogleMap mMap;
    FireBaseAdmin fireBaseAdmin;
    ArrayList<Locations> location;

    SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
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
        removeOldPins();
        GenericTypeIndicator<ArrayList<Locations>> indicator = new GenericTypeIndicator<ArrayList<Locations>>() {
        };
        location = dataSnapshot.getValue(indicator);
        addPins();

    }

    public void removeOldPins() {

        if (location != null) {
            for (int i = 0; i < location.size(); i++) {
                Locations locationTemp = location.get(i);
                if (locationTemp.getMarker() != null) {
                    locationTemp.getMarker().remove();
                }
            }
        }

    }

    // metodo para añadir pins de la base de datos
    public void addPins() {
        for (int i = 0; i < location.size(); i++) {

            Locations locationTemp = location.get(i);
            System.out.println("Location lat: " + locationTemp.lat);
            System.out.println("Location lon: " + locationTemp.lon);
            System.out.println("Location name: " + locationTemp.name);
            LatLng locationPos = new LatLng(locationTemp.lat, locationTemp.lon);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(locationPos);
            markerOptions.title(locationTemp.name);

            if (mMap != null) {
                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(locationTemp);
                locationTemp.setMarker(marker);
            }


            // hace zoom en el primero pin que se añade al mapa, 10 es el tamaño del zoom
            if (i == 0) mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationPos, 10));


        }
    }

    //metodo que detecta si un pin esta clickado
    @Override
    public boolean onMarkerClick(Marker marker) {


        Locations location = (Locations) marker.getTag();

        secondActivity.mapDetailFragment.txtName.setText("Nombre: " + location.name);
        secondActivity.mapDetailFragment.txtPob.setText("Población: " + location.poblation + "");
        secondActivity.mapDetailFragment.txtCountry.setText("País: " + location.country);
        FragmentTransaction transaction = secondActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(secondActivity.mapDetailFragment);
        transaction.commit();


        return false;
    }


    // usamos este metodo para esconder el fragment de los detalles al pulsar en algo que no sea un pin
    @Override
    public void onMapClick(LatLng latLng) {

        System.out.print("Click en el mapa");
        FragmentTransaction transaction = secondActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(secondActivity.mapDetailFragment);
        transaction.commit();

    }
}
