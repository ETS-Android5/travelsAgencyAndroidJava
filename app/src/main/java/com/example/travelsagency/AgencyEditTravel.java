package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.travelsagency.utilities.TravelUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AgencyEditTravel extends AppCompatActivity {
    EditText name_travel, description_travel, location_exit_travel, location_arrive_travel, date_start_travel, date_end_travel, time_exit_travel, seats_quantity_travel, price_travel;
    Spinner destination_travel;
    Button btnUpdateTravel;
    DatePickerDialog datePickerDialog;
    String id_travel, name, description, destination, location_exit, location_arrive, start_travel, hour_exit, end_travel;
    Integer quantity;
    Float price;
    private Cursor fila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_edit_travel);
        cargarPreferencias();
        consultTravel(id_travel);


        name_travel = (EditText) findViewById(R.id.edtxt_update_name_travel);
        description_travel = (EditText) findViewById(R.id.edtxt_update_description_travel);
        destination_travel = (Spinner) findViewById(R.id.edtxt_update_destination_travel);
        location_exit_travel = (EditText) findViewById(R.id.edtxt_update_location_exit_travel);
        location_arrive_travel = (EditText) findViewById(R.id.edtxt_update_location_arrive_travel);
        date_start_travel = (EditText) findViewById(R.id.edtxt_update_start_travel);
        time_exit_travel = (EditText) findViewById(R.id.edtxt_update_hour_exit_travel);
        date_end_travel = (EditText) findViewById(R.id.edtxt_update_end_travel);
        seats_quantity_travel = (EditText) findViewById(R.id.edtxt_update_quantity_travel);
        price_travel = (EditText) findViewById(R.id.edtxt_update_price_travel);
        btnUpdateTravel = (Button) findViewById(R.id.btnUpdateTravel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.edos_rep_mex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date_start_travel.setText(start_travel);
        time_exit_travel.setText(hour_exit);
        date_end_travel.setText(end_travel);
        date_start_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Llamamos a la función que mostrará el dialogo de selección de fecha*/
                dateDialog(view,date_start_travel);
            }
        });
        time_exit_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AgencyEditTravel.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mcurrentTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mcurrentTime.set(Calendar.MINUTE, selectedMinute);
                        SimpleDateFormat mSDF = new SimpleDateFormat("hh:mm a");
                        String time = mSDF.format(mcurrentTime.getTime());
                        time_exit_travel.setText(time);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        date_end_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Llamamos a la función que mostrará el dialogo de selección de fecha*/
                dateDialog(view, date_end_travel);
            }
        });
        destination_travel.setAdapter(adapter);
        destination_travel.setSelection(getIndex(destination_travel, destination));
        name_travel.setText(name);
        description_travel.setText(description);
        location_exit_travel.setText(location_exit);
        location_arrive_travel.setText(location_arrive);

        seats_quantity_travel.setText(quantity.toString());
        price_travel.setText(price.toString());
        /*=============================== UPDATE TRAVEL ===============================*/
        btnUpdateTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper travel = new DatabaseHelper(AgencyEditTravel.this, "TravelsAgency.db", null, 1 );
                SQLiteDatabase db = travel.getWritableDatabase();
                String id_travel_update = id_travel;
                String name = name_travel.getText().toString();
                String description = description_travel.getText().toString();
                String destination = destination_travel.getSelectedItem().toString();
                String location_exit = location_exit_travel.getText().toString();
                String location_arrive = location_arrive_travel.getText().toString();
                String start_travel = date_start_travel.getText().toString();
                String hour_exit = time_exit_travel.getText().toString();
                String end_travel = date_end_travel.getText().toString();
                String quantity = seats_quantity_travel.getText().toString();
                String price = price_travel.getText().toString();
                if (!destination.equals("Destination")){
                    ContentValues register = new ContentValues();
                    //register.put(UserUtilities.ID_FIELD, Integer.toString(upperbound));
                    register.put(TravelUtilities.NAME_TRAVEL, name);
                    register.put(TravelUtilities.DESCRIPTION, description);
                    register.put(TravelUtilities.DESTINATION, destination);
                    register.put(TravelUtilities.LOCATION_EXIT, location_exit);
                    register.put(TravelUtilities.LOCATION_ARRIVE, location_arrive);
                    register.put(TravelUtilities.HOUR_EXIT, hour_exit);
                    register.put(TravelUtilities.START_TRAVEL, start_travel);
                    register.put(TravelUtilities.END_TRAVEL, end_travel);
                    register.put(TravelUtilities.QUANTITY, quantity);
                    register.put(TravelUtilities.PRICE, price);
                    db.update("TRAVELS", register,"id='"+id_travel_update+"'",null);
                    db.close();
                    //clean_data();
                    //Intent intent = new Intent(SignUp.this, UserHome.class);
                    //startActivity(intent);
                    Toast.makeText(AgencyEditTravel.this, "Se actualizo el viaje " + name + " exitosamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AgencyEditTravel.this, "No has seleccionado el destino", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }

    private int getIndex(Spinner destination_travel, String destination) {
        for (int i=0; i<destination_travel.getCount(); i++){
            if (destination_travel.getItemAtPosition(i).toString().equalsIgnoreCase(destination)){
                return i;
            }
        }
        return 0;
    }

    public void dateDialog(View view, EditText edtxtDate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AgencyEditTravel.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        edtxtDate.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void cargarPreferencias() {
        id_travel = getIntent().getStringExtra("id");
        /*Toast.makeText(this, "Recibimos el viaje con ID: "+id_travel, Toast.LENGTH_SHORT).show();*/
        //titleListTravels.setText(id_agencie);
    }

    private  void consultTravel(String id){
        DatabaseHelper travel = new DatabaseHelper(AgencyEditTravel.this, "TravelsAgency.db", null, 1 );
        SQLiteDatabase db= travel.getWritableDatabase();
        fila=db.rawQuery("select name, description, destination, location_exit, location_arrive,start_travel, hour_exit, end_travel, quantity, price from TRAVELS where id = '"+ id +"'",null);
        /*Realizamos un try catch para captura de errores*/
        try {
            /*Condicional if preguntamos si cursor tiene algun dato*/
            if(fila.moveToFirst()){
//capturamos los valores del cursos y lo almacenamos en variable
                name = fila.getString(0);
                description = fila.getString(1);
                destination = fila.getString(2);
                location_exit = fila.getString(3);
                location_arrive = fila.getString(4);
                start_travel = fila.getString(5);
                hour_exit = fila.getString(6);
                end_travel = fila.getString(7);
                quantity = fila.getInt(8);
                price = fila.getFloat(9);
            }//si la primera condicion no cumple entonces que envie un mensaje toast
            else {
                Toast.makeText(AgencyEditTravel.this,"Hubo un error al tratar de recuperar la información",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            //capturamos los errores que hubieran
            Toast toast=Toast.makeText(AgencyEditTravel.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            //mostramos el mensaje
            toast.show();
        }
    }
}