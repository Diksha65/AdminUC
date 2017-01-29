package com.example.diksha.admin;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private SupportMapFragment mapFragment;
    private View bottomSheet;
    private Button deleteButton;
    private Circle circle;

    private static DataStash dataStash = DataStash.getsDataStash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        createView();
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        dataStash.googleMap = googleMap;
        setMarkers();

        CameraPosition cameraPosition = CameraPosition
                .builder(dataStash.googleMap.getCameraPosition())
                .target(dataStash.getLatLng())
                .zoom(16)
                .tilt(25)
                .build();

        dataStash.googleMap
                .animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition)
                );
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //dataStash.googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //dataStash.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        listeners();
    }


    private void setMarkers(){
        dataStash.googleMap.clear();
        MarkerOptions options = new MarkerOptions().flat(true);
        int i = 0;
        for(LatLng latLng : dataStash.getLatLngs()){
            i += 1;
            dataStash.googleMap.addMarker(new MarkerOptions().flat(true).position(latLng).title("Marker"));
            dataStash.googleMap.addCircle(new CircleOptions().center(latLng).radius(1)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE)
            );
        }
    }

    public void listeners() {

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dataStash.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (dataStash.bottomSheetBehavior .getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    dataStash.googleMap.setPadding(0, 0, 0, bottomSheet.getHeight());
                    dataStash.bottomSheetBehavior .setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                return true;
            }
        });

        dataStash.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (dataStash.bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    dataStash.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    dataStash.googleMap.setPadding(0, 0, 0, 0);
                }
            }
        });
    }

    public void createView() {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainContent);
        bottomSheet = coordinatorLayout.findViewById(R.id.bottomSheet);
        dataStash.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        dataStash.bottomSheetBehavior .setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e(TAG, "onStateChanged:" + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "onSlide");
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });
        deleteButton = (Button)findViewById(R.id.deleteButton);
    }

    @Override
    public void onBackPressed() {
        if (dataStash.bottomSheetBehavior .getState() == BottomSheetBehavior.STATE_EXPANDED) {
            dataStash.bottomSheetBehavior .setState(BottomSheetBehavior.STATE_COLLAPSED);
            dataStash.googleMap.setPadding(0, 0, 0, 0);
        } else {
            super.onBackPressed();
        }

    }
}