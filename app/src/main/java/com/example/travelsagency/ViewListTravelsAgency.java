package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.travelsagency.R;
import com.example.travelsagency.entities.Travel;

import java.util.ArrayList;

public class ViewListTravelsAgency extends AppCompatActivity {
    ListView listViewCursos;
    TextView titleListTravels;
    ArrayList<String> listaInformacion;
    ArrayList<Travel>  listaCursos;
    Button btnAddTravel;
    String id_agencie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_travels_agency);


        btnAddTravel = (Button) findViewById(R.id.btnAddTravel);
        titleListTravels = (TextView) findViewById(R.id.title_list_travels);
        cargarPreferencias();
        btnAddTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("ID AGENCIE", Context.MODE_PRIVATE);
                String id_agencie_send=id_agencie;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("id", id_agencie_send);
                editor.commit();
                Intent intent_create_travels = new Intent(ViewListTravelsAgency.this, CreateTravel.class);
                intent_create_travels.putExtra("id", id_agencie);
                startActivity(intent_create_travels);
            }
        });

    }
    private void cargarPreferencias() {
        id_agencie = getIntent().getStringExtra("id");
        //titleListTravels.setText(id_agencie);
    }

}