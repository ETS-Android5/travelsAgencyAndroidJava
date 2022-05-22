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
        super(context,name , factory, version);
    }

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE if not exists USERS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT, email TEXT, password TEXT, telephone TEXT, " +
                    "role TEXT, rfc TEXT, created_at TEXT);";

    private static final String CREATE_TABLE_AGENCIES =
            "CREATE TABLE if not exists AGENCIES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT, location TEXT, description TEXT, bussines_name TEXT, " +
                    "rating REAL, user_id_fk INTEGER, " +
                    "FOREIGN KEY (user_id_fk) REFERENCES USERS (id));";

    private static final String CREATE_TABLE_USERS_AGENCIES =
            "CREATE TABLE if not exists USERS_AGENCIES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "rating INTEGER, comment TEXT, user_id_fk INTEGER, agency_id_fk INTEGER, " +
                    "FOREIGN KEY (user_id_fk) REFERENCES USERS (id), " +
                    "FOREIGN KEY (agency_id_fk) REFERENCES AGENCIES (id));";

    private static final String CREATE_TABLE_VEHICLES =
            "CREATE TABLE if not exists VEHICLES(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT, model TEXT, year_vehicle TEXT, seat_quantity INTEGER, plate TEXT);";

    private static final String CREATE_TABLE_TRAVELS =
            "CREATE TABLE if not exists TRAVELS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT, description TEXT, destination TEXT, location_exit TEXT, location_arrive TEXT, " +
                    "start_travel TEXT, hour_exit TEXT, end_travel TEXT, quantity INTEGER, rating REAL, " +
                    "price REAL, agency_id_fk INTEGER, vehicle_id_fk INTEGER, " +
                    "FOREIGN KEY (agency_id_fk) REFERENCES AGENCIES (id), " +
                    "FOREIGN KEY (vehicle_id_fk) REFERENCES VEHICLES (id));";

    private static final String CREATE_TABLE_USERS_TRAVELS =
            "CREATE TABLE if not exists USERS_TRAVELS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "rating INTEGER, comment TEXT, seat_number TEXT, complete_payment INTEGER);";

    private static final String CREATE_TABLE_AMENITIES =
            "CREATE TABLE if not exists AMENITIES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, description TEXT);";

    private static final String CREATE_TABLE_VEHICLES_AMENITIES =
            "CREATE TABLE if not exists VEHICLES_AMENITIES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, vehicle_id_fk INTEGER, amenitie_id_fk INTEGER, " +
                    "FOREIGN KEY (vehicle_id_fk) REFERENCES VEHICLES (id), " +
                    "FOREIGN KEY (amenitie_id_fk) REFERENCES AMENITIES (id));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_AGENCIES);
        db.execSQL(CREATE_TABLE_USERS_AGENCIES);
        db.execSQL(CREATE_TABLE_VEHICLES);
        db.execSQL(CREATE_TABLE_TRAVELS);
        db.execSQL(CREATE_TABLE_USERS_TRAVELS);
        db.execSQL(CREATE_TABLE_AMENITIES);
        db.execSQL(CREATE_TABLE_VEHICLES_AMENITIES);
    }

    /*@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists USERS (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, email text, password text, telephone text, role text, rfc text, created_at datetime)");

        db.execSQL("CREATE TABLE if not exists AGENCIES (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, location text, description text, bussines_name text, rating float, FOREIGN KEY (user_id_fk) REFERENCES USERS (id))");

        db.execSQL("CREATE TABLE if not exists USERS_AGENCIES (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, rating int, comment text, FOREIGN KEY (user_id_fk) REFERENCES USERS (id), FOREIGN KEY (agency_id_fk) REFERENCES AGENCIES (id))");

        db.execSQL("CREATE TABLE if not exists VEHICLES(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, model text, year_vehicle datetime, seat_quantity int, plate text)");

        db.execSQL("CREATE TABLE if not exists TRAVELS (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, description text, destination text, location_exit text, location_arrive text, start_travel datetime, hour_exit timestamp, end_travel datetime, quantity int, rating float, price float, FOREIGN KEY (agency_id_fk) REFERENCES AGENCIES (id), FOREIGN KEY (vehicle_id_fk) REFERENCES VEHICLES (id))");

        db.execSQL("CREATE TABLE if not exists USERS_TRAVELS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, rating int, comment text, seat_number text, complete_payment int)");

        db.execSQL("CREATE TABLE if not exists AMENITIES (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name text, description text)");

        db.execSQL("CREATE TABLE if not exists VEHICLES_AMENITIES (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, FOREIGN KEY (vehicle_id_fk) REFERENCES VEHICLES (id), FOREIGN KEY (amenitie_id_fk) REFERENCES AMENITIES (id))");
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}
