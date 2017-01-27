package com.github.menf.smprojekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.menf.smprojekt.util.Akwaria;
import com.github.menf.smprojekt.util.AkwariaImp;
import com.github.menf.smprojekt.util.Bazadanych;

public class NoweAkwariumActivity extends AppCompatActivity {
    EditText dlugosc, wysokosc, szerokosc;
    Button dodaj;
    AkwariaImp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_akwarium);
        dlugosc = (EditText) findViewById(R.id.editTextDlugosc);
        wysokosc = (EditText) findViewById(R.id.editTextWysokosc);
        szerokosc = (EditText) findViewById(R.id.editTextSzerokosc);
        dodaj = (Button) findViewById(R.id.buttonDodajAkwarium);
        Bazadanych baza = Bazadanych.PobierzBazeDanych(this, "bazadanych.db", null, 1);

        db = new AkwariaImp(this,baza);
        db.open();
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(isEmpty(dlugosc) || isEmpty(wysokosc) || isEmpty(szerokosc)){
                    Toast.makeText(getApplicationContext(), "Wprawadź wszystkie dane", Toast.LENGTH_SHORT).show();
                }
                else {
                    int d = Integer.parseInt(dlugosc.getText().toString());
                    int w = Integer.parseInt(wysokosc.getText().toString());
                    int s = Integer.parseInt(szerokosc.getText().toString());
                    //Intent intent = new Intent(getApplicationContext(), GlowneOkno.class);
                    db.DodajAkwarium(new Akwaria(d,w,s));
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
