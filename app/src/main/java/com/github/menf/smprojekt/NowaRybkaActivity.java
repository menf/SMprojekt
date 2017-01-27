package com.github.menf.smprojekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.menf.smprojekt.util.Akwaria;
import com.github.menf.smprojekt.util.AkwariaImp;
import com.github.menf.smprojekt.util.Bazadanych;
import com.github.menf.smprojekt.util.Rybki;
import com.github.menf.smprojekt.util.RybkiImp;

/**
 * Created by menf on 2017-01-27.
 */

public class NowaRybkaActivity extends AppCompatActivity {
    private EditText wiek, ilosc, nazwa;
    private Button dodaj;
    private RybkiImp db;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowa_rybka);
        wiek = (EditText) findViewById(R.id.editTextWiek);
        ilosc = (EditText) findViewById(R.id.editTextIlosc);
        nazwa = (EditText) findViewById(R.id.editTextNazwa);
        dodaj = (Button) findViewById(R.id.buttonDodajRybke);
        id = getIntent().getExtras().getString("id");
        Bazadanych baza = Bazadanych.PobierzBazeDanych(this, "bazadanych.db", null, 1);

        db = new RybkiImp(this,baza);
        db.open();
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(isEmpty(wiek) || isEmpty(ilosc) || isEmpty(nazwa)){
                    Toast.makeText(getApplicationContext(), "Wprawadź wszystkie dane", Toast.LENGTH_SHORT).show();
                }
                else {
                    String w = wiek.getText().toString();
                    String i = ilosc.getText().toString();
                    String n = nazwa.getText().toString();
                    //Intent intent = new Intent(getApplicationContext(), GlowneOkno.class);
                    db.DodajRybke(new Rybki(Integer.parseInt(id),n,i,w));
                    db.close();
                    finish();
                }
            }
        });
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }
}
