package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class project_category extends AppCompatActivity {


    ImageView back;
    ProgressBar progressBar;

    ListView listView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();


    Spinner select_division, select_district,select_upazila,select_union;
    ArrayAdapter<String> divisionAdapter;
    ArrayAdapter<CharSequence> districtAdapter;
    ArrayAdapter<CharSequence> upazilaAdapter;
    ArrayAdapter<CharSequence> unionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_category);

        listView=findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        back=findViewById(R.id.back);

        select_division = findViewById(R.id.select_division);       select_upazila = findViewById(R.id.upazila);
        select_district = findViewById(R.id.select_district);       select_union = findViewById(R.id.union);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                project_category.super.onBackPressed();

            }
        });


      /*  divisionAdapter = ArrayAdapter.createFromResource(this, R.array.division, R.layout.spinner_layout);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_division.setAdapter(divisionAdapter);

        upazilaAdapter = ArrayAdapter.createFromResource(this, R.array.array_default_upazila, R.layout.spinner_layout);
        upazilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_upazila.setAdapter(upazilaAdapter);

        unionAdapter = ArrayAdapter.createFromResource(this, R.array.Union, R.layout.spinner_layout);
        unionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_union.setAdapter(unionAdapter);

*/




      /*  select_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected_division = select_division.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.select_division){

                    if (selected_division.equals("Select Division")) {
                        districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_districts, R.layout.spinner_layout);
                    }
                    else if (select_district.equals("Select District")) {
                        districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_districts, R.layout.spinner_layout);

                    }else if (select_district.equals("Select Upazila")) {
                        districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_upazila, R.layout.spinner_layout);
                    }

                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    select_district.setAdapter(districtAdapter);

                    //To obtain the selected District from the spinner
                    select_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selected_District = select_district.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/







        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resps.wsfilter.com/api/project-category";

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
                        Toast.makeText(project_category.this, "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(stringRequest);



        /////////////////////////

        /// this is retrive data from server in spinner layout


// Assume you have an ArrayAdapter for the division spinner
     /*   ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_division.setAdapter(divisionAdapter);

        // Assume you have an ArrayAdapter for the division spinner
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_district.setAdapter(districtAdapter);

        // Assume you have an ArrayAdapter for the division spinner
        ArrayAdapter<String> upazilaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        upazilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_upazila.setAdapter(upazilaAdapter);

        ArrayAdapter<String> unionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        unionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_union.setAdapter(unionAdapter);*/


// Specify the URL to fetch data for the division spinner
       /* String divisionUrl = "https://resps.wsfilter.com/api/divisions";
        fetchDataForSpinner(divisionUrl, divisionAdapter);

        String districtUrl = "https://resps.wsfilter.com/api/districts?division_id=2";
        fetchDataForSpinner(districtUrl, districtAdapter);

        String upazilaUrl = "https://resps.wsfilter.com/api/upazilas?district_id=14";
        fetchDataForSpinner(upazilaUrl, upazilaAdapter);

        String unionUrl = "https://resps.wsfilter.com/api/unions?upazila_id=125";
        fetchDataForSpinner(unionUrl, unionAdapter);

        // Handle item selection for the division spinner
        select_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Check if the selected item has id 0
                if (id == 0) {
                    // Load district data for division with id 0
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
        });*/


















        ///////////////

    }




    public void loaddata(){

        arrayList= new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(project_category.this);
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://resps.wsfilter.com/api/project-category";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);

                        int id = object.getInt("id");
                        String name = object.getString("name");
                        String des = object.getString("description");

                        hashMap = new HashMap<>();
                        hashMap.put("id", String.valueOf(id));
                        hashMap.put("name", name);
                        hashMap.put("description", des);

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
    ///// Load data end

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
            View myview = layoutInflater.inflate(R.layout.volly_list, null);

            // TextView tvid = myview.findViewById(R.id.tvid);
            TextView tvname = myview.findViewById(R.id.tvname);
            TextView tvdes = myview.findViewById(R.id.tvdes);

            /*HashMap<String, String>*/
            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String name = hashMap.get("name");
            String des = hashMap.get("description");

            //  tvid.setText(id);
            tvname.setText(name);
            tvdes.setText(des);

            return myview;
        }
    }
    ////////////////////////////////////

    /*String url = "https://resps.wsfilter.com/api/divisions";*/
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
                                dataList.add(itemName);
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













}