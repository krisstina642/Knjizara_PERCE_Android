package com.example.knjizara.Activities;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.knjizara.R;
import com.example.knjizara.Utility;

public class LoggedActivityBase extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (Utility.isLogged()){
            super.onCreateOptionsMenu(menu);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return Utility.initMenuItemFunctions(this, item);
    }

}
