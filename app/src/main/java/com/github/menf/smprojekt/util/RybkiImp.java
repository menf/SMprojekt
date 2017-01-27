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

public class RybkiImp {
    private static final String DEBUG_TAG = "SqLite";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bazadanych.db";
    private static final String tabela_rybki = "rybki";
    private static final String id_rybki = "id_rybki";
    private static final String id_akwaria = "id_akwaria";
    private static final String nazwa = "nazwa";
    private static final String ilosc = "ilosc";
    private static final String wiek = "wiek";
    private SQLiteDatabase db;
    private Context context;
    private Bazadanych baza;

    public RybkiImp() {}


    public RybkiImp(Context context, Bazadanych baza) {
        this.baza=baza;
        this.context = context;
    }


    public RybkiImp open(){
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




    public void DodajRybke(Rybki ryb) {
        ContentValues newTodoValues = new ContentValues();
        newTodoValues.put(id_akwaria, ryb.getId_akwaria());
        newTodoValues.put(nazwa, ryb.getNazwa());
        newTodoValues.put(ilosc, ryb.getIlosc());
        newTodoValues.put(wiek, ryb.getWiek());
        db.insert(tabela_rybki, null, newTodoValues);
    }


    public boolean UaktualnijRybke(Rybki ryb) {
        int id = ryb.getId();
        int id_akwaria= ryb.getId_akwaria();
        String nazwa= ryb.getNazwa();
        String ilosc=ryb.getIlosc();
        String wiek=ryb.getWiek();

        return UaktualnijRybke(id, id_akwaria,nazwa, ilosc,wiek);
    }

    public boolean UaktualnijRybke(int a,int ida,String nazwar,String iloscr,String wiekr) {
        String where = id_rybki + "=" + a;

        ContentValues updateProductValues = new ContentValues();
        updateProductValues.put(id_akwaria, ida);
        updateProductValues.put(nazwa, nazwar);
        updateProductValues.put(ilosc, iloscr);
        updateProductValues.put(wiek, wiekr);
        return db.update(tabela_rybki, updateProductValues, where, null) > 0;
    }



    public Rybki PobierzRybke(int  idp) {
        String[] columns = {id_rybki, id_akwaria,nazwa, ilosc, wiek};
        String where = id_rybki + "="+idp;
        Cursor cursor = db.query(tabela_rybki, columns, where, null, null, null, null);
        Rybki task = null;
        if(cursor != null && cursor.moveToFirst()) {
            int ida = Integer.parseInt(cursor.getString(1));
            String naz = cursor.getString(2);
            String il = cursor.getString(3);
            String wie = cursor.getString(4);
            task = new Rybki(idp,ida,naz,il,wie);
        }
        return task;
    }










    public List<Rybki> PobierzWszystkieRybki(int id) {

        List<Rybki> pom =new ArrayList<Rybki>();

        String[] columns = {id_rybki, id_akwaria,nazwa, ilosc, wiek};
        Cursor cursor=  db.query(tabela_rybki,columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                if(id==Integer.parseInt(cursor.getString(1)))
                {
                    Rybki item = new Rybki();
                    item.setId(Integer.parseInt(cursor.getString(0)));
                    item.setId_akwaria(Integer.parseInt(cursor.getString(1)));
                    item.setNazwa(cursor.getString(2));
                    item.setIlosc(cursor.getString(3));
                    item.setWiek(cursor.getString(4));
                    pom.add(item);
                }
            } while (cursor.moveToNext());
        }
        return pom;
    }

    public boolean UsunRybke(long idp){

        String where = id_rybki + "=" + idp;
        return db.delete(tabela_rybki, where, null) > 0;

    }

}
