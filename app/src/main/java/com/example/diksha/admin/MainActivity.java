package com.example.diksha.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {

    private Button SubmitLocations;
    private Button StartRegistration;
    private Button StartGamePlay;
    private Button StopGamePlay;

    private static DataStash dataStash = DataStash.getsDataStash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createView();
        createListeners();
    }

    private void createView(){
        SubmitLocations = (Button)findViewById(R.id.submitLocations);
    }

    private void createListeners(){
        SubmitLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLocationstoFirebase();
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addLocationstoFirebase(){
        Map<String, GeoLocation> locationMap = new ConcurrentHashMap<>();
        LatLng latLngs[] = dataStash.getLatLngs();
        int i=0;
        for(LatLng latLng : latLngs){
            i += 1;
            Log.d("Adding geolocations ", latLng.toString());
            dataStash.firebase.child(CONSTANTS.FIREBASE.GEOFIRE).push().setValue(latLng);
        }
        Log.d("ADDED", "added all the locations to firebase");
    }

    /*
    private static Map<String, GeoLocation> geoFireLocations(){
        Map<String, GeoLocation> locationMap = new ConcurrentHashMap<>();
        GeoLocation geoLocations[] = dataStash.getGeoLocations();
        int i = 0;
        for(GeoLocation geoLocation : geoLocations) {
            i += 1;
            //Toast.makeText( ,"Geolocation " + Integer.toString(i) +" added.", Toast.LENGTH_SHORT).show();
            Log.d("Adding geolocations ", Integer.toString(i));
            locationMap.put("LOCATION-" + Integer.toString(i), geoLocation);
        }
        return locationMap;
    }

    private static void addStaticGeoFireLocationstoMap(GeoFire geoFire,
                                          Map<String, GeoLocation> staticGeoLocations){
        for(Map.Entry<String, GeoLocation> locationEntry : staticGeoLocations.entrySet())
            geoFire.setLocation(
                    locationEntry.getKey(),
                    locationEntry.getValue(),
                    //Success
                    new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {
                            if (error != null)
                                Log.e("ADDING STATIC GEOFIRE",error.toString());
                        }});
    }
*/
}
