package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.travelsagency.entities.Travel;

import java.util.ArrayList;

public class ListTravelsUser extends AppCompatActivity {
    ListView listViewTravels;
    DatabaseHelper conn;
    ArrayList<String> listaInformacion;
    ArrayList<Travel> listaTravels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_travels_user);
        listViewTravels = (ListView) findViewById(R.id.listViewTravels);

        conn = new DatabaseHelper(getApplicationContext(), "TravelsAgency.db", null, 1);
        consultarListaTravels();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewTravels.setAdapter(adaptador);
        listViewTravels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListTravelsUser.this, ShowTravelView.class);
                intent.putExtra("id", listaTravels.get(i).getId());
                intent.putExtra("travelName", listaTravels.get(i).getName());
                intent.putExtra("travelDescription", listaTravels.get(i).getDescription());
                intent.putExtra("travelDestination", listaTravels.get(i).getDestination());
                intent.putExtra("travelExit", listaTravels.get(i).getLocation_exit());
                intent.putExtra("travelArrive", listaTravels.get(i).getLocation_arrive());
                intent.putExtra("travelDateStart", listaTravels.get(i).getStart_travel());
                intent.putExtra("travelDateEnd", listaTravels.get(i).getEnd_travel());
                startActivity(intent);
            }
        });
    }
    private void consultarListaTravels() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Travel travel = null;
        listaTravels = new ArrayList<Travel>();
        Cursor cursor = db.rawQuery("SELECT * FROM TRAVELS", null);
        //Toast.makeText(this, Integer.toString(cursor.getCount()), Toast.LENGTH_LONG).show();
        while(cursor.moveToNext()) {
            travel = new Travel();
            travel.setId(cursor.getInt(0));
            travel.setName(cursor.getString(1));
            travel.setDescription(cursor.getString(2));
            travel.setDestination(cursor.getString(3));
            travel.setLocation_exit(cursor.getString(4));
            travel.setLocation_arrive(cursor.getString(5));
            travel.setStart_travel(cursor.getString(6));
            travel.setHour_exit(cursor.getString(7));
            travel.setEnd_travel(cursor.getString(8));
            listaTravels.add(travel);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaTravels.size(); i++){
            listaInformacion.add("Nombre del viaje: "+listaTravels.get(i).getName()
                    + "\nDestino: " + listaTravels.get(i).getDestination()
                    +"\nFecha de salida: "+ listaTravels.get(i).getStart_travel()
                    + "\nHora de salida: " + listaTravels.get(i).getHour_exit());
        }
    }
}