package com.example.knjizara.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knjizara.R;
import com.example.knjizara.Types.User;
import com.example.knjizara.Utility;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Utility.initToolbar(this);
    }

    public void register(View view) {
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String surname = ((EditText)findViewById(R.id.surname)).getText().toString();
        String ussername = ((EditText)findViewById(R.id.ussername)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        String state = ((EditText)findViewById(R.id.state)).getText().toString();
        String town = ((EditText)findViewById(R.id.town)).getText().toString();
        String street = ((EditText)findViewById(R.id.street)).getText().toString();
        String street_number = ((EditText)findViewById(R.id.street_number)).getText().toString();
        String password = ((EditText)findViewById(R.id.password1)).getText().toString();
        String password2 = ((EditText)findViewById(R.id.password2)).getText().toString();

        if(name.equals("") || surname.equals("") || ussername.equals("") || phone.equals("")
        || state.equals("") || town.equals("") || street.equals("")|| street_number.equals("")
        || password.equals("") || password2.equals("")){
            Toast.makeText(this, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(password2)){
            Toast.makeText(this, "Lozinke se ne poklapaju", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Korisnik je kreiran", Toast.LENGTH_SHORT).show();
            User user=new User(name,surname,ussername, password, town, phone, street, street_number);
            finish();
        }
    }
}