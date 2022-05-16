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
        //db.execSQL("CREATE TABLE if not exists USERS(id int primary key, name text, email text, telephone text, rfc text, role text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}
