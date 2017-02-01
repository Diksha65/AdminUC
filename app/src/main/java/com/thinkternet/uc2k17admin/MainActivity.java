package com.thinkternet.uc2k17admin;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
    private TextView textView;
    public CountDownTimer waitTimer;


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
        StartGamePlay = (Button)findViewById(R.id.startGamePlay);
        textView = (TextView)findViewById(R.id.textView);
        StopGamePlay = (Button)findViewById(R.id.stopGamePlay);
        StartRegistration = (Button)findViewById(R.id.startRegistration);
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


        StartGamePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitTimer = new CountDownTimer(15000, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textView.setText("Time left: " + millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("DONE");
                        Toast.makeText(MainActivity.this, "CountdownOver", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        });

        StopGamePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(waitTimer != null){
                    waitTimer.cancel();
                    waitTimer = null;
                    textView.setText("STOPPED");
                }
            }
        });

        StartRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.team_card_view,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
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
