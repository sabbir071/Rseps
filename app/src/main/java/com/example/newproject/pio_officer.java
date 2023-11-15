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
import android.widget.Toast;

public class pio_officer extends AppCompatActivity {


  /*  public static String PROJECT_NAME = "";
    public static String PROJECT_DETAIL = "";
    public static String PROJECT_DIVISION = "";
    public static String DIVISION_TEXT = "";
    public static String PROJECT_DISTRICT = "";
    public static String DISTRICT_TEXT = "";
    public static String PROJECT_UPAZILA = "";
    public static String UPAZILA_TEXT = "";
    public static String PROJECT_UNION = "";
    public static String UNION_TEXT = "";
    public static String MAP_TEXT = "";
    public static String PROJECT_STATUS = "";
    public static String PROJECT_STATUS_TEXT = "";
    public static String PROJECT_DES = "";
    public static String PROJECT_DES_TEXT = "";*/


    TextView project_name, project_detail,project_division,division_text,project_district,district_text,project_upazila,
            upazila_text,project_union,union_text,map_text,project_status,project_status_text,project_des,project_des_text;

    ImageView back;

    RelativeLayout map_direction;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pio_officer);

        back=findViewById(R.id.back);

        project_name=findViewById(R.id.project_name);
        project_detail=findViewById(R.id.project_detail);
        project_division=findViewById(R.id.project_division);
        division_text=findViewById(R.id.division_text);
        project_district=findViewById(R.id.project_district);
        district_text=findViewById(R.id.district_text);
        project_upazila=findViewById(R.id.project_upazila);
        upazila_text=findViewById(R.id.upazila_text);
        project_union=findViewById(R.id.project_union);
        union_text=findViewById(R.id.union_text);
        map_text=findViewById(R.id.map_text);
        project_status=findViewById(R.id.project_status);
        project_status_text=findViewById(R.id.project_status_text);
        project_des=findViewById(R.id.project_des);
        project_des_text=findViewById(R.id.project_des_text);


        map_direction=findViewById(R.id.map_direction);

     /*   project_name.setText(PROJECT_NAME);
        project_detail.setText(PROJECT_DETAIL);
        project_division.setText(PROJECT_DIVISION);
        division_text.setText(DIVISION_TEXT);
        project_district.setText(PROJECT_DISTRICT);
        district_text.setText(DISTRICT_TEXT);
        project_upazila.setText(PROJECT_UPAZILA);
        upazila_text.setText(UPAZILA_TEXT);
        project_union.setText(PROJECT_UNION);
        union_text.setText(UNION_TEXT);
        map_text.setText(MAP_TEXT);
        project_status.setText(PROJECT_STATUS);
        project_status_text.setText(PROJECT_STATUS_TEXT);
        project_des.setText(PROJECT_DES);
        project_des_text.setText(PROJECT_DES_TEXT);*/


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pio_officer.super.onBackPressed();
            }
        });

       map_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Shajahanpur+Upazila+Parishad,+Dhaka+-+Rangpur+Hwy,+Majhira/@24.7777291,89.3907147,17z/data=!3m1!4b1!4m6!3m5!1s0x39fc53315041ebdb:0xecf5b5f9f86840d5!8m2!3d24.7778128!4d89.393349!16s%2Fg%2F11ckngdpnl?entry=ttu")));

                 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=<24.7776741>,<89.3938513>")));

//
            }
        });



    }
}