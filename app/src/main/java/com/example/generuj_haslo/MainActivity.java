package com.example.generuj_haslo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] stanowiska = {"Kierownik","Starszy programista","Młodszy programista","Tester"};
    String haslo = "";
    String znakiDoHasla = "";
    String male = "abcdefghijklmnopqrstuvwxyz";
    String wielkie = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String cyfry = "0123456789";
    String znakiSpecjalne = "!@#$%^&*()_+-=";
    int liczbaZnakow;
    int dlugoscHasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner stanowisko = findViewById(R.id.stanowisko);
        ArrayAdapter aA = new ArrayAdapter(this, android.R.layout.simple_spinner_item,stanowiska);
        aA.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stanowisko.setAdapter(aA);

        EditText imie, nazwisko, iloscZnakowET;
        imie = findViewById(R.id.imie);
        nazwisko = findViewById(R.id.nazwisko);
        iloscZnakowET = findViewById(R.id.iloscZnakow);

        CheckBox wielkieCB, cyfryCB, znakiSpecjalneCB;
        wielkieCB = findViewById(R.id.wielkie);
        cyfryCB = findViewById(R.id.cyfry);
        znakiSpecjalneCB = findViewById(R.id.znakiSpecjalne);

        Button generuj, done;
        generuj = findViewById(R.id.generuj);
        done = findViewById(R.id.done);

        Random generator = new Random();

        AlertDialog.Builder oknoPowiadomien = new AlertDialog.Builder(this);

        generuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haslo = "";

                dlugoscHasla = Integer.parseInt(iloscZnakowET.getText().toString());

                znakiDoHasla = male;

                if (wielkieCB.isChecked()) znakiDoHasla += wielkie;
                if (cyfryCB.isChecked()) znakiDoHasla += cyfry;
                if (znakiSpecjalneCB.isChecked()) znakiDoHasla +=znakiSpecjalne;

                liczbaZnakow = znakiDoHasla.length();

                for (int i=0; i<dlugoscHasla; i++) {
                    haslo += znakiDoHasla.charAt(generator.nextInt(liczbaZnakow));
                }

                oknoPowiadomien.setMessage(haslo);
                oknoPowiadomien.create();
                oknoPowiadomien.show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oknoPowiadomien.setMessage("Dane pracownika: "+imie.getText().toString()+" "+nazwisko.getText().toString()+" "+stanowisko.getSelectedItem().toString()+" Hasło: "+haslo);
                oknoPowiadomien.create();
                oknoPowiadomien.show();
            }
        });
    }
}