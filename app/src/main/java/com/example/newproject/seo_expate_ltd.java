package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class seo_expate_ltd extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seo_expate_ltd);

       RelativeLayout map_direction= findViewById(R.id.map_direction);
       ImageView back= findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seo_expate_ltd.super.onBackPressed();
            }
        });

        map_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/SEO+EXPATE+BANGLADESH+LTD./@24.7753232,89.3915456,17z/data=!4m7!3m6!1s0x39fc5380c9b5d9d3:0x6b91dc7c9e5fc33b!4b1!8m2!3d24.7753184!4d89.3941205!16s%2Fg%2F11sx0mj1ny?entry=ttu")));

              //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=<49.000000Z>,<49.000000Z>")));

//
            }
        });

    }
}