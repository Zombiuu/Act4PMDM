package com.example.ramsesdiezgalvan.act4pmdm.fragment;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramsesdiezgalvan.act4pmdm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapDetailFragment extends Fragment {
    public TextView txtName;
    public TextView txtPob;
    public TextView txtCountry;


    public MapDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_detail, container, false);


        txtName = v.findViewById(R.id.txtName);
        txtCountry= v.findViewById(R.id.txtCountry);
        txtPob = v.findViewById(R.id.txtPob);




        return v;
    }

    public void setSelectedLocation(){

    }


}
