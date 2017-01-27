package com.github.menf.smprojekt.util;

/**
 * Created by menf on 2017-01-26.
 */

public class Rybki {
    int id;
    int id_akwaria;
    String nazwa;
    String ilosc;
    String wiek;

    public Rybki() {
    }

    public Rybki( int id, int id_akwaria, String nazwa, String ilosc,String wiek) {
        this.wiek = wiek;
        this.id = id;
        this.id_akwaria = id_akwaria;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
    }

    public Rybki(int id_akwaria, String nazwa, String ilosc, String wiek) {
        this.id_akwaria = id_akwaria;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.wiek = wiek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_akwaria(int id_akwaria) {
        this.id_akwaria = id_akwaria;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public void setWiek(String wiek) {
        this.wiek = wiek;
    }

    public int getId() {
        return id;
    }

    public int getId_akwaria() {
        return id_akwaria;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getIlosc() {
        return ilosc;
    }

    public String getWiek() {
        return wiek;
    }
}