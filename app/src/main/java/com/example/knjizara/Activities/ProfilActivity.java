package com.example.knjizara.Activities;


import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knjizara.R;
import com.example.knjizara.Types.User;
import com.example.knjizara.Utility;

public class ProfilActivity extends LoggedActivityBase {
    User korisnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialContext();
    }

    private void setUserInfo(boolean edit){
        if (!edit){
        TextView tv = findViewById(R.id.name);
        tv.setText("Ime: " + korisnik.getName());
        tv = findViewById(R.id.surname);
        tv.setText("Prezime: " + korisnik.getSurnme());
        tv = findViewById(R.id.ussername);
        tv.setText("Korisnicko ime: " + korisnik.getUssername());
        tv = findViewById(R.id.phone);
        tv.setText("Broj telefona: " + korisnik.getPhone());
        tv = findViewById(R.id.town);
        tv.setText("Grad: " + korisnik.getTown());
        tv = findViewById(R.id.street);
        tv.setText("Ulica: " + korisnik.getStreet());
        tv = findViewById(R.id.street_number);
        tv.setText("Broj: " + korisnik.getStreet_number());
        }
        else {
            EditText tv = findViewById(R.id.name);
            tv.setText(korisnik.getName());
            tv = findViewById(R.id.surname);
            tv.setText(korisnik.getSurnme());
            tv = findViewById(R.id.ussername);
            tv.setText(korisnik.getUssername());
            tv = findViewById(R.id.phone);
            tv.setText(korisnik.getPhone());
            tv = findViewById(R.id.town);
            tv.setText(korisnik.getTown());
            tv = findViewById(R.id.street);
            tv.setText( korisnik.getStreet());
            tv = findViewById(R.id.street_number);
            tv.setText(korisnik.getStreet_number());
        }
    }

    private void setInitialContext(){
        setContentView(R.layout.profil);
        Utility.initToolbar(this);
        korisnik = Utility.getUser();
        setUserInfo(false);

        findViewById(R.id.button_promeni_lozinku).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogSearchView = inflater.inflate(R.layout.change_password, null);
            builder.setView(dialogSearchView);

            AlertDialog alert = builder.create();
            alert.show();

            alert.findViewById(R.id.potvrdi).setOnClickListener(view1 -> {
                String staral= ((EditText)alert.findViewById(R.id.old_password)).getText().toString();
                String noval= ((EditText)alert.findViewById(R.id.new_password)).getText().toString();
                String noval2= ((EditText)alert.findViewById(R.id.new_password2)).getText().toString();
                if (staral.equals("") || noval.equals("") || noval2.equals("")){
                    Toast.makeText(this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
                }
                else if (staral!=korisnik.getPassword()){
                    Toast.makeText(this, "Neispravna stara lozinka", Toast.LENGTH_SHORT).show();
                }
                else if (!noval.equals(noval2)){
                    Toast.makeText(this, "Lozinke se ne poklapaju", Toast.LENGTH_SHORT).show();
                }
                else {
                    korisnik.setPassword(noval);
                    Utility.setUser(korisnik);
                    alert.dismiss();
                }
            });
        });

        findViewById(R.id.button_izmeni_podatke).setOnClickListener(view -> {
            setContentView(R.layout.change_info);
            setUserInfo(true);
            Utility.initToolbar(this);
            findViewById(R.id.potvrdi).setOnClickListener(view1 -> {
                EditText ime= findViewById(R.id.name);
                EditText prezime= findViewById(R.id.surname);
                EditText kor = findViewById(R.id.ussername);
                EditText tel = findViewById(R.id.phone);
                EditText grad = findViewById(R.id.town);
                EditText ul = findViewById(R.id.street);
                EditText kbroj = findViewById(R.id.street_number);
                String lozinka=korisnik.getPassword();

                if (ime.getText().toString().equals("") || prezime.getText().toString().equals("") ||
                        kor.getText().toString().equals("") || lozinka.equals("") || tel.getText().toString().equals("") || grad.getText().toString().equals("") ||
                        ul.getText().toString().equals("") || kbroj.getText().toString().equals("")){
                    Toast.makeText(this, "Sva polja moraju da budu popunjena", Toast.LENGTH_SHORT).show();
                   return;

                }

                korisnik.setAll( ime.getText().toString(), prezime.getText().toString(),
                        kor.getText().toString(), lozinka, tel.getText().toString(), grad.getText().toString(),
                        ul.getText().toString(), kbroj.getText().toString() );
                Utility.setUser(korisnik);
                Toast.makeText(this, "Uspesno sacuvane izmene", Toast.LENGTH_SHORT).show();
                setInitialContext();


            });
        });
    }
}