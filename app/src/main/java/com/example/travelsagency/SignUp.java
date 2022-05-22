package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.travelsagency.utilities.UserUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SignUp extends AppCompatActivity{
    private EditText id_user, name, telephone, rfc, password, email;
    private RadioButton radio_role;
    private RadioGroup role;
    private Button btn_sign_up, btn_sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.edtxt_usuario);
        telephone = findViewById(R.id.edtxt_telephone);
        role = findViewById(R.id.roles);
        email = findViewById(R.id.edtxt_email);
        password = findViewById(R.id.edtxt_pwd);
        rfc = findViewById(R.id.edtxt_rfc);
        //btn_sign_in = findViewById(R.id.btnLogin);
        btn_sign_up = findViewById(R.id.btnReg);
        /*btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper user = new DatabaseHelper(SignUp.this, "TravelsAgency.db", null, 1 );
                //long id = getTaskCount();
                Random rand = new Random(); //instance of random class
                int upperbound = 1000;
                //generate random values from 0-24
                int int_random = rand.nextInt(upperbound);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                SQLiteDatabase db = user.getWritableDatabase();
                String user_name = name.getText().toString();
                String user_tel = telephone.getText().toString();
                String user_rfc = rfc.getText().toString();
                String user_pass = password.getText().toString();
                String user_email = email.getText().toString();
                int selectedId = role.getCheckedRadioButtonId();
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.roles);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                String selectedText = (String) radioButton.getText();
                radio_role = (RadioButton) findViewById(selectedId);
                if (!user_name.isEmpty() && !user_tel.isEmpty() && !user_email.isEmpty() && !user_pass.isEmpty()){
                    ContentValues register = new ContentValues();
                    //register.put(UserUtilities.ID_FIELD, Integer.toString(upperbound));
                    register.put(UserUtilities.RFC_FIELD, user_rfc);
                    register.put(UserUtilities.NAME_FIELD, user_name);
                    register.put(UserUtilities.ROLE_FIELD, selectedText);
                    register.put(UserUtilities.EMAIL_FIELD, user_email);
                    register.put(UserUtilities.TELEPHONE_FIELD, user_tel);
                    register.put(UserUtilities.PASSWORD_FIELD, user_pass);
                    register.put(UserUtilities.CREATED_AT, strDate);
                    db.insert("USERS", null, register);
                    db.close();
                    //clean_data();
                    //Intent intent = new Intent(SignUp.this, UserHome.class);
                    //startActivity(intent);
                    Toast.makeText(SignUp.this, "Se agregó a " + user_name + " exitosamente", Toast.LENGTH_LONG).show();
                    if(radio_role.getText().toString().equals("Viajero")){
                        startActivity(new Intent(SignUp.this, TravelerHome.class));
                    }else if (radio_role.getText().toString().equals("Agencia")){
                        startActivity(new Intent(SignUp.this, ViewListTravelsAgency.class));
                    }
                }else{
                    Toast.makeText(SignUp.this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                    db.close();
                }
                // find the radiobutton by returned id
                radio_role = (RadioButton) findViewById(selectedId);

                Toast.makeText(SignUp.this, radio_role.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UserRegister(View view){

    }

    public long getTaskCount() {
        DatabaseHelper user = new DatabaseHelper(this, "TravelsAgency.db", null, 1 );
        SQLiteDatabase db = user.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(db, "USERS", "id");
    }

    public void sign_in_view(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clean_data(){
        id_user.setText("");
        name.setText("");
        telephone.setText("");
        rfc.setText("");
        email.setText("");
        password.setText("");
    }


}