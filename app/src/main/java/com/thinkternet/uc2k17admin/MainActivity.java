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

import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button SubmitLocations;
    private Button StartRegistration;
    private Button ViewMap;
    private Button StartGamePlay;
    private Button StopGamePlay;
    private Button check;
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
        check = (Button)findViewById(R.id.check);
        StartRegistration = (Button)findViewById(R.id.startRegistration);
        ViewMap = (Button)findViewById(R.id.viewMap);
    }

    private void createListeners(){
        SubmitLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLocationstoFirebase();
                //transaction();
                SubmitLocations.setText("Do not press again");
                SubmitLocations.setEnabled(false);
            }
        });


        ViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStash.firebase.child(CONSTANTS.FIREBASE.AVAILABLE_LOCATIONS)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Toast.makeText(getApplicationContext(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
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

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addLocationstoFirebase(){
        Map<String, Object> teamBaseData = new HashMap<>();

        for(LatLng latLng : dataStash.LatLngs)
            teamBaseData.put(UUID.randomUUID().toString(), latLng);

        dataStash.firebase
                .child(CONSTANTS.FIREBASE.AVAILABLE_LOCATIONS)
                .updateChildren(teamBaseData);
    }
}
