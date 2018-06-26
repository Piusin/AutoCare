package com.example.piusin.autocare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GarageDescription extends AppCompatActivity {
    private static final String URL_GARAGES  = "http://10.42.0.1/Scripts/autocare/garageDesc.php";
    RecyclerView recyclerView;
    List<GarageDescDataProvider> garageDataProviderList;
    Intent intent;
    String garageId;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garages);

        recyclerView = findViewById(R.id.garages_reyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        garageDataProviderList = new ArrayList<>();
        intent = getIntent();
        if(intent!= null) {
            garageId = intent.getStringExtra("garageId");
        }
        else{
            Toast.makeText(context, "End of Options", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Am in" + garageId, Toast.LENGTH_SHORT).show();

       loadGarages();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void loadGarages(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GARAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject garage = array.getJSONObject(i);
                        if(garageId.equals(garage.getString("garage_id")))
                        garageDataProviderList.add(new GarageDescDataProvider(
                                garage.getString("garage_id"),
                                garage.getString("garage_image"),
                                garage.getString("garage_name"),
                                garage.getString("garage_location"),
                                garage.getString("open_time"),
                                garage.getString("close_time"),
                                garage.getString("owner_name"),
                                garage.getString("experience"),
                                garage.getString("description"),
                                garage.getString("phone_no")

                        ));
                    }
                    //creating adapter object and setting it to recyclerview
                    GarageDescAdapter adapter = new GarageDescAdapter(getApplicationContext(),garageDataProviderList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
