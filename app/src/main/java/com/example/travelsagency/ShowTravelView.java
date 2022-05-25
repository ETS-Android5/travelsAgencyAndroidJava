package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTravelView extends AppCompatActivity {
    private TextView title, description, destination, exit, arrive, startDate, endDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_travel_view);
        title = (TextView) findViewById(R.id.titleTravel);
        destination = findViewById(R.id.travelDestionation);
        description = findViewById(R.id.travelDescription);
        exit = findViewById(R.id.location_exit);
        arrive = findViewById(R.id.location_arrive);
        startDate = findViewById(R.id.start_travel);
        endDate = findViewById(R.id.end_travel);
        Intent intent = getIntent();

        //Extraer datos del intent listtravels
        int id = intent.getExtras().getInt("id");
        String name = intent.getExtras().getString("travelName");
        String destinationTravel = intent.getExtras().getString("travelDestination");
        String descriptionTravel = intent.getExtras().getString("travelDescription");
        String exitTravel = intent.getExtras().getString("travelExit");
        String arriveTravel = intent.getExtras().getString("travelArrive");
        String startDateTravel = intent.getExtras().getString("travelDateStart");
        String startEndTravel = intent.getExtras().getString("travelDateEnd");

        title.setText(name);
        description.setText("Description: "+descriptionTravel);
        destination.setText("Destination: "+destinationTravel);
        exit.setText("Location exit:\n"+exitTravel);
        arrive.setText("Location arrive:\n"+arriveTravel);
        startDate.setText("Date start:\n"+startDateTravel);
        endDate.setText("Date end:\n"+startEndTravel);
    }
}