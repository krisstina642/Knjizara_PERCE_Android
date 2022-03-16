package com.example.knjizara.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.knjizara.R;
import com.example.knjizara.Utility;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity klasa =this;
    View dialogSearchView;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Utility.initToolbar(this);
    }

    public void prijavaNaSistem(View view) {
        EditText korisnicko_ime = findViewById(R.id.ussername);
        EditText lozinka = findViewById(R.id.password1);
        if(Utility.logIn(korisnicko_ime.getText().toString(), lozinka.getText().toString())) {
            Toast.makeText(this, "Uspesna prijava", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{

            Toast.makeText(this, "Losi podaci ", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrujse(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
