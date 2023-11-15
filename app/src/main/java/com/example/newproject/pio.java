package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class pio extends AppCompatActivity {

    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pio);

        listView = findViewById(R.id.listView);

        creatTable();
        pio.MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);



/////////////////////////////
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View myViewttt = layoutInflater.inflate(R.layout.pio_layout, parent, false);

           TextView project_name=findViewById(R.id.project_name);
            TextView project_detail=findViewById(R.id.project_detail);
            TextView  project_division=findViewById(R.id.project_division);
            TextView  division_text=findViewById(R.id.division_text);
            TextView   project_district=findViewById(R.id.project_district);
            TextView  district_text=findViewById(R.id.district_text);
            TextView  project_upazila=findViewById(R.id.project_upazila);
            TextView  upazila_text=findViewById(R.id.upazila_text);
            TextView   project_union=findViewById(R.id.project_union);
            TextView   union_text=findViewById(R.id.union_text);
            TextView  map_text=findViewById(R.id.map_text);
            TextView   project_status=findViewById(R.id.project_status);
            TextView   project_status_text=findViewById(R.id.project_status_text);
            TextView   project_des=findViewById(R.id.project_des);
            TextView   project_des_text=findViewById(R.id.project_des_text);



            HashMap<String, String> hashMap = arrayList.get(position);
            String project_name1 = hashMap.get("project_name");
            String project_detail1 = hashMap.get("project_detail");
            String project_division1 = hashMap.get("project_division");
            String division_text1 = hashMap.get("division_text");
            String project_district1 = hashMap.get("project_district");
            String district_text1 = hashMap.get("district_text");
            String project_upazila1 = hashMap.get("project_upazila");
            String upazila_text1 = hashMap.get("upazila_text");
            String project_union1 = hashMap.get("project_union");
            String union_text1 = hashMap.get("union_text");
            String map_text1 = hashMap.get("map_text");
            String project_status1 = hashMap.get("project_status");
            String project_status_text1 = hashMap.get("project_status_text");
            String project_des1 = hashMap.get("project_des");
            String project_des_text1 = hashMap.get("project_des_text");

//            myViewttt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    pio_officer.PROJECT_NAME = project_name1;
//                    pio_officer.PROJECT_DETAIL = project_detail1;
//                    pio_officer.PROJECT_DIVISION = project_division1;
//                    pio_officer.DIVISION_TEXT = division_text1;
//                    pio_officer.PROJECT_DISTRICT = project_district1;
//                    pio_officer.DISTRICT_TEXT = district_text1;
//                    pio_officer.PROJECT_UPAZILA = project_upazila1;
//                    pio_officer.UPAZILA_TEXT = upazila_text1;
//                    pio_officer.PROJECT_UNION = project_union1;
//                    pio_officer.UNION_TEXT = union_text1;
//                    pio_officer.MAP_TEXT = map_text1;
//                    pio_officer.PROJECT_STATUS = project_status1;
//                    pio_officer.PROJECT_STATUS_TEXT = project_status_text1;
//                    pio_officer.PROJECT_DES = project_des1;
//                    pio_officer.PROJECT_DES_TEXT = project_des_text1;
//
//                    startActivity(new Intent(pio.this, pio_officer.class));
//
//                }
//            });





            return myViewttt;
        }
    }

        ///////////////
    private void creatTable() {

        hashMap = new HashMap<>();
        hashMap.put("projecame", "project_name");
        hashMap.put("project_detail", "HGFHFGHFG");
        hashMap.put("project_division", "HGFHFGHFG");
        hashMap.put("division_text", "HGFHFGHFG");
        hashMap.put("project_district", "HGFHFGHFG");
        hashMap.put("district_text", "HGFHFGHFG");
        hashMap.put("project_upazila", "HGFHFGHFG");
        hashMap.put("upazila_text", "HGFHFGHFG");
        hashMap.put("project_union", "HGFHFGHFG");
        hashMap.put("union_text", "HGFHFGHFG");
        hashMap.put("map_text", "HGFHFGHFG");
        hashMap.put("project_status", "HGFHFGHFG");
        hashMap.put("project_status_text", "HGFHFGHFG");
        hashMap.put("project_des", "HGFHFGHFG");
        hashMap.put("project_des_text", "HGFHFGHFG");

        arrayList.add(hashMap);


    }







}