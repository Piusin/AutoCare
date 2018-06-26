package com.example.piusin.autocare;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Garages extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String URL_GARAGES  = "http://10.42.0.1/Scripts/autocare/garages.php";
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<GarageDataProvider> garageDataProviderList;
    GarageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garages);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.garages_reyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        garageDataProviderList = new ArrayList<>();
        loadGarages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.overflow_home:
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.overflow_mapseach:
                Toast.makeText(this, "MapSearch Clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }

    private void loadGarages(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GARAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject garage = array.getJSONObject(i);
                        garageDataProviderList.add(new GarageDataProvider(
                                garage.getString("garage_id"),
                                garage.getString("garage_image"),
                                garage.getString("garage_name"),
                                garage.getString("garage_location"),
                                garage.getString("open_time"),
                                garage.getString("close_time"),
                                garage.getString("owner_name"),
                                garage.getString("description")
                        ));
                    }
                    adapter = new GarageAdapter(getApplicationContext(),garageDataProviderList);
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
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length()>=2) {
            newText = newText.toLowerCase();
            ArrayList<GarageDataProvider> newList = new ArrayList<>();
            for (GarageDataProvider dataProvider : garageDataProviderList) {
                String name = dataProvider.getGarageLocation().toLowerCase();
                if (name.contains(newText))
                    newList.add(dataProvider);
            }
            if(newList.size()>0) {
                adapter.setFilter(newList);
                recyclerView.setAdapter(adapter);
            }
            else {
                displayAlert();
            }
        }
        return true;
    }

    private void displayAlert(){
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops Location Not Found.!")
                .setContentText("Try again with app valid location.")
                .show();
    }
}
