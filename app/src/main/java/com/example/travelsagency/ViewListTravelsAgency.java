package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelsagency.R;
import com.example.travelsagency.entities.Travel;

import java.util.ArrayList;

public class ViewListTravelsAgency extends AppCompatActivity {
    ListView listViewTravels;
    ArrayList<String> listaInformacion;
    ArrayList<Travel>  listaTravels;
    TextView titleListTravels;
    Cursor fila;
    Button btnAddTravel;
    String id_agencie;
    String id_agencie_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_travels_agency);

        btnAddTravel = (Button) findViewById(R.id.btnAddTravel);
        titleListTravels = (TextView) findViewById(R.id.title_list_travels);
        listViewTravels = (ListView) findViewById(R.id.listTravels);

        cargarPreferencias();
        cargarPreferenciasMain();
        consultTravels();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewTravels.setAdapter(adapter);

        btnAddTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("ID AGENCIE", Context.MODE_PRIVATE);
                String id_agencie_send;
                if (!(id_agencie == null)){
                    id_agencie_send=id_agencie;
                }else {
                    id_agencie_send = id_agencie_login;
                }
                SharedPreferences.Editor editor = preferences.edit();
                Toast.makeText(ViewListTravelsAgency.this, "Enviamos: "+id_agencie_send, Toast.LENGTH_SHORT).show();
                editor.putString("id", id_agencie_send);
                editor.commit();
                Intent intent_create_travels = new Intent(ViewListTravelsAgency.this, CreateTravel.class);
                intent_create_travels.putExtra("id", id_agencie_send);
                startActivity(intent_create_travels);
            }
        });

    }

    private void cargarPreferencias() {
        id_agencie = getIntent().getStringExtra("id");
        //titleListTravels.setText(id_agencie);
    }

    private void cargarPreferenciasMain() {
        id_agencie_login = getIntent().getStringExtra("id_agencie");
        //titleListTravels.setText(id_agencie_login);
        /*Toast.makeText(this, "Recibimos: "+id_agencie, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Recibimos: "+id_agencie_login, Toast.LENGTH_SHORT).show();*/
        //titleListTravels.setText(id_agencie);
    }

    private void consultTravels() {
        DatabaseHelper travels = new DatabaseHelper(ViewListTravelsAgency.this, "TravelsAgency.db", null, 1 );
        SQLiteDatabase dbTravels = travels.getWritableDatabase();
        Travel travel = null;
        listaTravels = new ArrayList<Travel>();
        fila=dbTravels.rawQuery("select name, destination, start_travel, end_travel, quantity, price from TRAVELS where agency_id_fk = '"+ id_agencie_login +"'",null);
        while (fila.moveToNext()){
            travel = new Travel();
            travel.setName(fila.getString(0));
            travel.setDestination(fila.getString(1));
            travel.setStart_travel(fila.getString(2));
            travel.setEnd_travel(fila.getString(3));
            travel.setQuantity(fila.getInt(4));
            travel.setPrice(fila.getFloat(5));
            listaTravels.add(travel);
        }
        getListTravels();
    }

    private void getListTravels(){
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaTravels.size(); i++){
            listaInformacion.add("\n"+listaTravels.get(i).getName()+ " - " + listaTravels.get(i).getDestination() + "\nStart: " +listaTravels.get(i).getStart_travel() + "         Ends: "+ listaTravels.get(i).getEnd_travel()+"\nSeats: "+listaTravels.get(i).getQuantity() + "         Price: "+listaTravels.get(i).getPrice() + "\n");
        }
    }
}