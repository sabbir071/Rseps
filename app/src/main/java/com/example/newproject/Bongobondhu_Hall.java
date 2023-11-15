package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Bongobondhu_Hall extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bongobondhu_hall);


        RelativeLayout map_direction= findViewById(R.id.map_direction);
        ImageView back= findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bongobondhu_Hall.super.onBackPressed();
            }
        });

        map_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/24%C2%B046'21.4%22N+89%C2%B023'49.1%22E/@24.7723801,89.3951443,17.18z/data=!4m4!3m3!8m2!3d24.772601!4d89.396976?entry=ttu")));

                //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=<37.000000Z>,<37.000000Z>")));

            }
        });

    }
}