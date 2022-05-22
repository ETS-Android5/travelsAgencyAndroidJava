package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.travelsagency.R;
import com.example.travelsagency.entities.Travel;

import java.util.ArrayList;

public class ViewListTravelsAgency extends AppCompatActivity {
    ListView listViewCursos;
    ArrayList<String> listaInformacion;
    ArrayList<Travel>  listaCursos;
    Button btnAddTravel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_travels_agency);

        btnAddTravel = (Button) findViewById(R.id.btnAddTravel);
        btnAddTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewListTravelsAgency.this, CreateTravel.class));
            }
        });
    }

}