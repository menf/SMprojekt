package com.github.menf.smprojekt.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by menf on 2017-01-26.
 */

public class AkwariaImp {
private static final String DEBUG_TAG = "SqLite";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bazadanych.db";
    private static final String tabela_akwaria = "akwaria";
    private static final String id = "id";
    private static final String dlugosc = "dlugosc";
    private static final String wysokosc = "wysokosc";
    private static final String szerokosc = "szerokosc";

    private SQLiteDatabase db;
    private Context context;
    private Bazadanych baza;

    public AkwariaImp() {}


    public AkwariaImp(Context context, Bazadanych baza) {
        this.baza=baza;
        this.context = context;
    }


    public AkwariaImp open(){
        baza = new Bazadanych(context, DB_NAME, null, DB_VERSION);
        try {
            db = baza.getWritableDatabase();
        } catch (SQLException e) {
            db = baza.getReadableDatabase();
        }
        return this;
    }
    public void close() {
        baza.close();
    }




    public void DodajAkwarium(Akwaria akw) {
        ContentValues newTodoValues = new ContentValues();
        newTodoValues.put(dlugosc, akw.getDlugosc());
        newTodoValues.put(wysokosc, akw.getWysokosc());
        newTodoValues.put(szerokosc, akw.getSzerokosc());
        db.insert(tabela_akwaria, null, newTodoValues);
    }


    public boolean UaktualnijAkwarium(Akwaria akw) {
        int id = akw.getId();
        int dlugosc = akw.getDlugosc();
        int wysokosc = akw.getWysokosc();
        int szerokosc = akw.getSzerokosc();

        return UaktualnijAkwarium(id, dlugosc,wysokosc, szerokosc);
    }

    public boolean UaktualnijAkwarium(int a,int dlugosca,int wysokosca,int szerokosca) {
        String where = id + "=" + a;

        ContentValues updateProductValues = new ContentValues();
        updateProductValues.put(dlugosc, dlugosca);
        updateProductValues.put(wysokosc, wysokosca);
        updateProductValues.put(szerokosc, szerokosca);
        return db.update(tabela_akwaria, updateProductValues, where, null) > 0;
    }



    public Akwaria PobierzAkwarium(int  idp) {
        String[] columns = {id, dlugosc,szerokosc, wysokosc};
        String where = id + "="+idp;
        Cursor cursor = db.query(tabela_akwaria, columns, where, null, null, null, null);
        Akwaria task = null;
        if(cursor != null && cursor.moveToFirst()) {
            int dlugosc = Integer.parseInt(cursor.getString(1));
            int wysokosc = Integer.parseInt(cursor.getString(2));
            int szerokosc = Integer.parseInt(cursor.getString(3));
            task = new Akwaria(idp,dlugosc,wysokosc,szerokosc);
        }
        return task;
    }










    public List<Akwaria> PobierzWszystkieAkwaria() {

        List<Akwaria> pom =new ArrayList<Akwaria>();

        String[] columns = {id, dlugosc,szerokosc, wysokosc};
        Cursor cursor=  db.query(tabela_akwaria,columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Akwaria item = new Akwaria();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setDlugosc(Integer.parseInt(cursor.getString(1)));
                item.setWysokosc(Integer.parseInt(cursor.getString(2)));
                item.setSzerokosc(Integer.parseInt(cursor.getString(3)));
                pom.add(item);
            } while (cursor.moveToNext());
        }
        return pom;
    }

    public boolean UsunAkwarium(long idp){

        String where = id + "=" + idp;
        return db.delete(tabela_akwaria, where, null) > 0;

    }

}


