package com.example.knjizara.Activities;

import android.os.Bundle;

import com.example.knjizara.R;
import com.example.knjizara.Utility;

public class MainActivity extends LoggedActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utility.initToolbar(this);
        Utility.initMainPart(this);
    }
}