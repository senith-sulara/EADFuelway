/**
 * Owner home class
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelway.Api.ApiStation;
import com.example.fuelway.Service.StationService;
import com.example.fuelway.adapter.FuelUserAdapter;
import com.example.fuelway.adapter.OwnerAdapter;
import com.example.fuelway.model.FuelModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerHome extends AppCompatActivity {

    SwipeRefreshLayout swip;
    EditText search;
    RecyclerView recyclerView;
    List<FuelModel> list;
    OwnerAdapter ownerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        search = findViewById(R.id.edsearcho);

        swip= findViewById(R.id.sweep);
        recyclerView = findViewById(R.id.resss);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        list = new ArrayList<>();
//        ownerAdapter = new OwnerAdapter(getApplicationContext(),list);
//
//        recyclerView.setAdapter(ownerAdapter);
//
        getDAta();
//        String stationName = getIntent().getStringExtra("fuelStationName");
//        String nic = getIntent().getStringExtra("ownerNIC");
//
//        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
//        Call<List<FuelModel>> call2 = apiService.getStation(nic);
//
//        call2.enqueue(new Callback<List<FuelModel>>() {
//            @Override
//            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
//                list.addAll(response.body());
//                Log.d("TAG","Response = "+list);
//                ownerAdapter.setData(list);
//                Toast.makeText(OwnerHome.this,"success data", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
//                Toast.makeText(OwnerHome.this,"Data Retrieve Failed", Toast.LENGTH_LONG).show();
//
//            }
//        });

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDAta();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
        //get Owner Station details
//        call2.enqueue(new Callback<FuelModel>() {
//            @Override
//            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
//                list.addAll(response.body());
//                Log.d("TAG","Response = "+list);
//                fuelUserAdapter.setData(list);
//                    Toast.makeText(OwnerHome.this,"success data", Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<FuelModel> call, Throwable t) {
//                Toast.makeText(OwnerHome.this,"Data Retrieve Failed", Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void getDAta() {
        list = new ArrayList<>();
        ownerAdapter = new OwnerAdapter(getApplicationContext(),list);

        recyclerView.setAdapter(ownerAdapter);

        String stationName = getIntent().getStringExtra("fuelStationName");
        String nic = getIntent().getStringExtra("ownerNIC");

        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
        Call<List<FuelModel>> call2 = apiService.getStation(nic);

        call2.enqueue(new Callback<List<FuelModel>>() {
            @Override
            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
                list.addAll(response.body());
                Log.d("TAG","Response = "+list);
                ownerAdapter.setData(list);
                swip.setRefreshing(false);
                Toast.makeText(OwnerHome.this,"success data", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
                Toast.makeText(OwnerHome.this,"Data Retrieve Failed", Toast.LENGTH_LONG).show();

            }
        });

    }

    //Filter Search data
    private void filter(String text){
        ArrayList<FuelModel> filterList = new ArrayList<>();

        for (FuelModel item: list){
            if (item.getStationLocationURL().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        ownerAdapter.setData(filterList);
    }

    //https://www.youtube.com/watch?v=wg9nG07UvuU

}