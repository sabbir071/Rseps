package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.newproject.MainActivity;

public class splash_screen extends AppCompatActivity {

    private static final long SPLASH_DELAY = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(splash_screen.this, MainActivity.class);
            startActivity(mainIntent);
            finish(); // close the splash activity to prevent going back
        }, SPLASH_DELAY);

    }
}