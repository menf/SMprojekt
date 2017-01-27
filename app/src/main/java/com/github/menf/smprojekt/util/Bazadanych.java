package com.github.menf.smprojekt.util;

/**
 * Created by Milek on 2017-01-20.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Bazadanych extends SQLiteOpenHelper {

    private static Bazadanych bazadanych;

    private static final String DEBUG_TAG = "SqLite";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bazadanych.db";

    private static final String tabela_akwaria = "akwaria";
    private static final String id = "id";
    private static final String dlugosc = "dlugosc";
    private static final String wysokosc = "wysokosc";
    private static final String szerokosc = "szerokosc";

    private static final String tabela_rybki = "rybki";
    private static final String id_rybki = "id_rybki";
    private static final String id_akwaria = "id_akwaria";
    private static final String nazwa = "nazwa";
    private static final String ilosc = "ilosc";
    private static final String wiek = "wiek";




    String UTWORZ_AKWARIA = "CREATE TABLE " + tabela_akwaria + "("
            + id + " INTEGER PRIMARY KEY," + dlugosc + " TEXT,"
            + wysokosc + " TEXT," + szerokosc + " TEXT" + ");";

    String UTWORZ_RYBKI = "CREATE TABLE " + tabela_rybki + "("
            + id_rybki + " INTEGER PRIMARY KEY," + id_akwaria + " TEXT,"
            + nazwa + " TEXT," + ilosc + " TEXT," + wiek + " TEXT" + ");";

    String USUN_AKWARIA =
            "DROP TABLE IF EXISTS " + tabela_akwaria;

    String USUN_RYBKI =
            "DROP TABLE IF EXISTS " + tabela_rybki;


    public Bazadanych(Context context, String name,
                      SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static Bazadanych PobierzBazeDanych(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        if (bazadanych==null)
        {
            bazadanych = new Bazadanych(context, name, factory, version);
            return bazadanych;
        }
        else return bazadanych;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UTWORZ_AKWARIA);
        db.execSQL(UTWORZ_RYBKI);

        Log.d(DEBUG_TAG, "Database creating...");
        Log.d(DEBUG_TAG, "Table " + tabela_akwaria + " ver." + DB_VERSION + " created");
        Log.d(DEBUG_TAG, "Table " + tabela_rybki + " ver." + DB_VERSION + " created");

    }
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(USUN_AKWARIA);
        db.execSQL(UTWORZ_RYBKI);

        Log.d(DEBUG_TAG, "Database updating...");
        Log.d(DEBUG_TAG, "Table " + tabela_akwaria + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "Table " + tabela_rybki + " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "All wiek is lost.");

        onCreate(db);
    }
}