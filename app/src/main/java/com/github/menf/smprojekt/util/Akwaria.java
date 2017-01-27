package com.github.menf.smprojekt.util;

/**
 * Created by Milek on 2017-01-20.
 */

public class Akwaria {
    int id;
    int dlugosc;
    int wysokosc;
    int szerokosc;

    public Akwaria() {
    }

    public Akwaria(int id, int dlugosc, int wysokosc, int szerokosc) {
        this.id = id;
        this.dlugosc = dlugosc;
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
    }

    public Akwaria(int dlugosc, int wysokosc, int szerokosc) {
        this.dlugosc = dlugosc;
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(int wysokosc) {
        this.wysokosc = wysokosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(int szerokosc) {
        this.szerokosc = szerokosc;
    }

    public float getPojemnosc() {
        return szerokosc*wysokosc*dlugosc/1000;
    }
}


