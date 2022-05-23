package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.travelsagency.utilities.TravelUtilities;
import com.example.travelsagency.utilities.UserUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateTravel extends AppCompatActivity {
    EditText name_travel, description_travel, location_exit_travel, location_arrive_travel, date_start_travel, date_end_travel, time_exit_travel, seats_quantity_travel, price_travel;
    Spinner destination_travel;
    Button btnCrateTravel;
    DatePickerDialog datePickerDialog;
    String id_agencie_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_travel);

        name_travel = (EditText) findViewById(R.id.edtxt_name_travel);
        description_travel = (EditText) findViewById(R.id.edtxt_description_travel);
        destination_travel = (Spinner) findViewById(R.id.edtxt_destination_travel);
        location_exit_travel = (EditText) findViewById(R.id.edtxt_location_exit_travel);
        location_arrive_travel = (EditText) findViewById(R.id.edtxt_location_arrive_travel);
        date_start_travel = (EditText) findViewById(R.id.edtxt_start_travel);
        date_end_travel = (EditText) findViewById(R.id.edtxt_end_travel);
        time_exit_travel = (EditText) findViewById(R.id.edtxt_hour_exit_travel);
        seats_quantity_travel = (EditText) findViewById(R.id.edtxt_quantity_travel);
        price_travel = (EditText) findViewById(R.id.edtxt_price_travel);
        btnCrateTravel = (Button) findViewById(R.id.btnCreateTravel);

        //Llenado del spinner con los estados de méxico
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.edos_rep_mex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destination_travel.setAdapter(adapter);

        date_start_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Llamamos a la función que mostrará el dialogo de selección de fecha*/
                dateDialog(view,date_start_travel);
            }
        });
        date_end_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Llamamos a la función que mostrará el dialogo de selección de fecha*/
                dateDialog(view, date_end_travel);
            }
        });

        time_exit_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateTravel.this, new TimePickerDialog.OnTimeSetListener() {
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
        cargarPreferencias();
        Toast.makeText(this, "Recibimos: "+id_agencie_get, Toast.LENGTH_SHORT).show();

        btnCrateTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper user = new DatabaseHelper(CreateTravel.this, "TravelsAgency.db", null, 1 );
                SQLiteDatabase db = user.getWritableDatabase();
                String id_agencie_fk = id_agencie_get;
                String name = name_travel.getText().toString();
                String description = description_travel.getText().toString();
                String destination = destination_travel.getSelectedItem().toString();
                //String destination = destination_travel.getText().toString();
                String location_exit = location_exit_travel.getText().toString();
                String location_arrive = location_arrive_travel.getText().toString();
                String start_travel = date_start_travel.getText().toString();
                String hour_exit = time_exit_travel.getText().toString();
                String end_travel = date_end_travel.getText().toString();
                String quantity = seats_quantity_travel.getText().toString();
                String price = price_travel.getText().toString();
                if (!name.isEmpty() && !description.isEmpty() && !location_exit.isEmpty()  && !location_arrive.isEmpty() && !start_travel.isEmpty() && !end_travel.isEmpty() && !quantity.isEmpty() && !price.isEmpty()){
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
                        register.put(TravelUtilities.AGENCY_ID, id_agencie_fk);
                        db.insert("TRAVELS", null, register);
                        db.close();
                        //clean_data();
                        //Intent intent = new Intent(SignUp.this, UserHome.class);
                        //startActivity(intent);
                        Toast.makeText(CreateTravel.this, "Se agregó el viaje " + name + " exitosamente", Toast.LENGTH_LONG).show();
                        cleanFields();
                    }else{
                        Toast.makeText(CreateTravel.this, "No has seleccionado el destino", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CreateTravel.this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            }
        });
    }
    public void cleanFields(){
        name_travel.setText("");
        description_travel.setText("");
        destination_travel.setSelection(0);
        location_exit_travel.setText("");
        location_arrive_travel.setText("");
        date_start_travel.setText("");
        date_end_travel.setText("");
        time_exit_travel.setText("");
        seats_quantity_travel.setText("");
        price_travel.setText("");
    }

    public void dateDialog(View view, EditText edtxtDate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(CreateTravel.this,
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



    /*private void showDatePickerDialog() {
        DatePickerDialog pickerDialog = new DatePickerDialog();

    }*/
    private void cargarPreferencias() {
        id_agencie_get = getIntent().getStringExtra("id");
        //titleListTravels.setText(id_agencie);
    }
}