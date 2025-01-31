package com.example.travelsagency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnlogin, btn_sign_up;
    EditText txtuser, txtpassword;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin = findViewById(R.id.btnLogin);
        btn_sign_up = findViewById(R.id.btnReg);
        txtuser = findViewById(R.id.edtxt_usuario);
        txtpassword = findViewById(R.id.edtxt_pwd);
        btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /*Creamos un objeto de la clase DBHelper e
            instanciamos el constructor y damos el nonbre de
            la base de datos y la version*/
            DatabaseHelper admin = new DatabaseHelper(MainActivity.this, "TravelsAgency.db", null, 1 );
            /*Abrimos la base de datos como escritura*/
            SQLiteDatabase db= admin.getWritableDatabase();
            /*Creamos dos variables string y capturamos los datos
            ingresado por el usuario y lo convertimos a string*/
            String usuario=txtuser.getText().toString();
            String contrasena=txtpassword.getText().toString();
            /*inicializamos al cursor y llamamos al objeto de la base
            de datos para realizar un sentencia query where donde
            pasamos las dos variables nombre de usuario y password*/
            fila=db.rawQuery("select u.email, u.password, u.role, u.id, a.id from USERS as u, AGENCIES as a where email='"+
                    usuario+"' and password='"+contrasena+"' and u.id = a.user_id_fk",null);
            /*Realizamos un try catch para captura de errores*/
            try {
                /*Condicional if preguntamos si cursor tiene algun dato*/
                if(fila.moveToFirst()){
                    //capturamos los valores del cursos y lo almacenamos en variable
                    String usua=fila.getString(0);
                    String pass=fila.getString(1);
                    String role = fila.getString(2);
                    String id = fila.getString(4);
                    //preguntamos si los datos ingresados son iguales
                    if (usuario.equals(usua)&&contrasena.equals(pass)&&role.equals("Viajero")){
                        //si son iguales entonces vamos a otra ventana
                        //Menu es una nueva actividad empty
                        Toast.makeText(MainActivity.this, "Bienvenido "+ usua + "!", Toast.LENGTH_SHORT).show();
                        Intent ven=new Intent(MainActivity.this, ListTravelsUser.class);
                        //lanzamos la actividad
                        startActivity(ven);
                        //limpiamos las las cajas de texto
                        txtuser.setText("");
                        txtpassword.setText("");
                    }else if(usuario.equals(usua)&&contrasena.equals(pass)&&role.equals("Agencia")){
                        SharedPreferences preferences = getSharedPreferences("ID AGENCIE", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id_agencie",id);
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Bienvenido "+ usua + "!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ViewListTravelsAgency.class);
                        intent.putExtra("id_agencie",id);
                        /*Toast.makeText(MainActivity.this, "Hemos enviado "+id, Toast.LENGTH_SHORT).show();*/
                        startActivity(intent);
                        //Toast.makeText(MainActivity.this, "email: "+ usua + " pass: " + pass + "role: " + role, Toast.LENGTH_SHORT).show();
                        //limpiamos las las cajas de texto
                        txtuser.setText("");
                        txtpassword.setText("");
                    }
                }//si la primera condicion no cumple entonces que envie un mensaje toast
                else {
                    Toast.makeText(MainActivity.this,"Datos incorrectos",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                //capturamos los errores que hubieran
                Toast toast=Toast.makeText(MainActivity.this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
                //mostramos el mensaje
                toast.show();
            }
        }
    });
    }

    public void sign_up_view(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}