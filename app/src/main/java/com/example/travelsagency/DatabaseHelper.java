package com.example.travelsagency;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.travelsagency.entities.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context,
                          @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists USERS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, email text, telephone text, rfc text, role text, password text)");
        //Create table for Travels
        db.execSQL("CREATE TABLE if not exists TRAVELS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, description text, destination text, location_exit text, location_arrive text, start_travel datetime, hour_exit timestamp, end_travel datetime, quantity intger, rating float, price float, FOREIGN KEY (agency_id_fk) REFERENCES AGENCIES (agency_id), FOREIGN KEY (vehicle_id_fk) REFERENCES VEHICLES (vehicle_id))");


        //db.execSQL("CREATE TABLE if not exists USERS(id int primary key, name text, email text, telephone text, rfc text, role text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}
