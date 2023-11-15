package com.example.newproject;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    //  Spinner spinner1,spinner2,spinner3,spinner4;
    ImageView imageMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    ListView listView;

    private String selected_District, selected_division,selected_upazila,selected_union;
    ProgressBar progressBar;
    Spinner select_division, select_district,select_upazila,select_union;
    ArrayAdapter<CharSequence> divisionAdapter, districtAdapter,upazilaAdapter,unionAdapter;

    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    LottieAnimationView lottie_menu;

    String baseDivisionUrl = "https://resps.wsfilter.com/api/divisions?division_id=";
    String baseDistrictUrl = "https://resps.wsfilter.com/api/districts?division_id=";
    String baseUpazilaUrl = "https://resps.wsfilter.com/api/upazilas?district_id=";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getColor(R.color.freelancerColor));
        }

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);


        select_division = findViewById(R.id.select_division);       select_upazila = findViewById(R.id.upazila);
        select_district = findViewById(R.id.select_district);       select_union = findViewById(R.id.union);

        lottie_menu = findViewById(R.id.lottie_menu);
       /* imageMenu = findViewById(R.id.imageMenu); */   drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_View);


        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.category) {
                    startActivity(new Intent(MainActivity.this, project_category.class));
                    drawerLayout.closeDrawers();

                } else if (itemId == R.id.list) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    Toast.makeText(MainActivity.this, "Project List", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                } else if (itemId == R.id.about) {
                    startActivity(new Intent(MainActivity.this, About_activity.class));
                  //  Toast.makeText(MainActivity.this, "About Project", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                } else if (itemId == R.id.exit) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.exit)
                            .setTitle("Exit!")
                            .setMessage("Are you sure to exit from the app now?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_HOME);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });

        lottie_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


      //  loaddata();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resps.wsfilter.com/api/get-locations";

        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        loaddata();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error (show a message or log details)
                        Log.e("Volley", "Error: " + error.toString());
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(stringRequest);



        MainActivity.MyAdapter yAdapter = new MainActivity.MyAdapter();
        listView.setAdapter(yAdapter);


        ///

        // DIVISION
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fetchDataForSpinner("https://resps.wsfilter.com/api/divisions", divisionAdapter);
        select_division.setAdapter(divisionAdapter);

        ////    DISTRICT
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       fetchDataForSpinner("https://resps.wsfilter.com/api/districts?division_id=", districtAdapter);
        select_district.setAdapter(districtAdapter);

        // UPAZILA
        ArrayAdapter<String> upazilaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        upazilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fetchDataForSpinner("https://resps.wsfilter.com/api/upazilas?district_id=", upazilaAdapter);
        select_upazila.setAdapter(upazilaAdapter);

        /////// UNION

        ArrayAdapter<String> unionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        unionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fetchDataForSpinner("https://resps.wsfilter.com/api/unions?upazila_id=", unionAdapter);
        select_union.setAdapter(unionAdapter);

// Specify the URL to fetch data for the division spinner

        String divisionUrl = "https://resps.wsfilter.com/api/divisions";
        fetchDataForSpinner(divisionUrl, divisionAdapter);

        String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=";
        fetchDataForSpinner(districtUrl, districtAdapter);

        String upazilaUrl = "https://resps.wsfilter.com/api/upazilas?district_id=1";
        fetchDataForSpinner(upazilaUrl, upazilaAdapter);

        String unionUrl = "https://resps.wsfilter.com/api/unions?upazila_id=1";
        fetchDataForSpinner(unionUrl, unionAdapter);

      /*  // Handle item selection for the division spinner
        select_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (id == 0) {
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=1";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                } if (id == 1){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=2";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 2){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=3";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 3){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=4";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 4){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=5";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 5){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=6";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 6){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=7";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 7){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=8";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }if (id == 8){
                    String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=9";
                    fetchDataForSpinner(districtUrl, districtAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

*/



        select_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               String division = ("https://resps.wsfilter.com/api/divisions?division_id=");

                for (int i = 0; i <= division.length(); i++) {
                    if (id == i) {
                        String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=" + (i + 1);
                        fetchDataForSpinner(districtUrl, districtAdapter);
                        break;
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected if needed
            }
        });


        select_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Assuming you have an array or list of district URLs

                // Validate that the position is within the bounds of your data
                if (position >= 0 && position < districtUrl.length()) {
                    String upazilaUrl = "https://resps.wsfilter.com/api/upazilas?district_id=" + (position + 1);
                    fetchDataForSpinner(upazilaUrl, upazilaAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected if needed
            }
        });









/*

        select_upazila.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // if (id > 0) {
                    int district = (int) id;

                    for (int i = 1; i <= 100; i++) {

                        String baseUpazilaUrl = "https://resps.wsfilter.com/api/upazilas?district_id=";
                        if (district == i) {
                            String upazilaUrl = baseUpazilaUrl + i;
                            fetchDataForSpinner(upazilaUrl, upazilaAdapter);
                            break;  // Exit the loop after finding the matching division ID
                        }
                    }
              //  }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

*/






/////
        }




    private void fetchDataForSpinner(String url, final ArrayAdapter<String> adapter) {
        RequestQueue queue = Volley.newRequestQueue(this);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<String> dataList = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
                        String itemName = item.getString("name");
                        String itemId = item.getString("id");

                        dataList.add(itemName);
                       // dataList.add("ID: " + itemId);

                    }

                    // Clear previous data in the adapter and add new data
                    adapter.clear();
                    adapter.addAll(dataList);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                });

        queue.add(jsonArrayRequest);
    }







    public class MyAdapter extends BaseAdapter {

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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View myview = layoutInflater.inflate(R.layout.officer, parent, false);

            // TextView tvid = myview.findViewById(R.id.tvid);
            TextView tvname = myview.findViewById(R.id.name);
            // TextView tvdes = myview.findViewById(R.id.tvdes);
            TextView serial_Number_text = myview.findViewById(R.id.serialNumber);



            HashMap<String, String>
            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String name = hashMap.get("name");
            //  String des = hashMap.get("description");


            int serialNumber = position + 1; // Adding 1 because the position is zero-based
            String serialNumberText = serialNumber + ".";
            serial_Number_text.setText(serialNumberText);

            tvname.setText(name);

            myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int itemId = Integer.parseInt(String.valueOf(serialNumber));

                    switch (itemId) {
                        case 1:
                            startActivity(new Intent(getApplicationContext(), pio_officer.class));
                            break;
                        case 2:
                            startActivity(new Intent(getApplicationContext(), seo_expate_ltd.class));
                            break;
                        case 3:
                            startActivity(new Intent(getApplicationContext(), Bongobondhu_Hall.class));
                            break;
                    }
                }
            });







            return myview;
        }
    }


    public void loaddata(){



        arrayList= new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        progressBar.setVisibility(View.VISIBLE);

       String url = "https://resps.wsfilter.com/api/get-locations";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);

                        int id = object.getInt("id");
                        String name = object.getString("name");
                      //  String des = object.getString("description");

                        hashMap = new HashMap<>();
                        hashMap.put("id", String.valueOf(id));
                        hashMap.put("name", name);
                      //  hashMap.put("description", des);

                        arrayList.add(hashMap);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /////////////////

                if (arrayList.size()>0){
                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        //   Log.d(TAG, "onErrorResponse: g",error);
                    }
                });

        queue.add(jsonArrayRequest);

    }





}