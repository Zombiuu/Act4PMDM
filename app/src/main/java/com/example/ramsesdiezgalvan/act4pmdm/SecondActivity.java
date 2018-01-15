package com.example.ramsesdiezgalvan.act4pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdmin;
import com.example.ramsesdiezgalvan.act4pmdm.firebase.FireBaseAdminListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SecondActivity  extends AppCompatActivity{

    SupportMapFragment mapFragment;
    SecondActivityEvents events;
    FireBaseAdmin fireBaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         events = new SecondActivityEvents(this);
         mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
         mapFragment.getMapAsync(events);

        fireBaseAdmin = new FireBaseAdmin();
        fireBaseAdmin.setFireBaseAdminListener(events);

    }


}
