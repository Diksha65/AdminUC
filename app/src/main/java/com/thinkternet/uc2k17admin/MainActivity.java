package com.thinkternet.uc2k17admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
                //transaction();
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addLocationstoFirebase(){
        LatLng latLngs[] = dataStash.getLatLngs();
        int i=0;
        String key;
        for(LatLng latLng : latLngs){
            i += 1;
            Log.d("Adding geolocations ", dataStash.getString(latLng));
            key = dataStash.firebase.child(CONSTANTS.FIREBASE.AVAILABLE_LOCATIONS).push().getKey();
            dataStash.firebase.child(CONSTANTS.FIREBASE.AVAILABLE_LOCATIONS).child(key).setValue(dataStash.getString(latLng));
        }
        Log.d("ADDED", "added all the locations to firebase");
    }

    private void transaction(){
        dataStash.firebase.child(CONSTANTS.FIREBASE.AVAILABLE_LOCATIONS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String latlng = dataSnapshot.getValue().toString();
                        Toast.makeText(MainActivity.this, latlng, Toast.LENGTH_LONG).show();
                        Log.d("FETCHED", latlng);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
