package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class About_activity extends AppCompatActivity {

    TextView phoneNumber;
    ImageView call,mail,loca,back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        RelativeLayout callButton = findViewById(R.id.call_button);
        phoneNumber = findViewById(R.id.phoneNumber);
        call = findViewById(R.id.call);   loca = findViewById(R.id.loca);
        mail = findViewById(R.id.mail);


        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                About_activity.super.onBackPressed();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumbertext = phoneNumber.getText().toString();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumbertext));

                startActivity(intent);
            }
        });



        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView emailTextView = findViewById(R.id.email_num);
                String emailAddress = emailTextView.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + emailAddress));

                startActivity(intent);
            }
        });

        loca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/Shajahanpur+is+an+upazila+of+Bogura+District+in+the+Division+of+Rajshahi,+Bangladesh/@24.7621831,89.3017439,12z/data=!3m1!4b1?entry=ttu")));
            }
        });







    }
}