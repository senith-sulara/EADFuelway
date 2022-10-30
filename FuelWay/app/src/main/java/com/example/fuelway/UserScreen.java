/**
 * User Screen class
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.fuelway.Api.ApiStation;
import com.example.fuelway.Service.StationService;
import com.example.fuelway.adapter.FuelStationAdapter;
import com.example.fuelway.adapter.FuelUserAdapter;
import com.example.fuelway.model.FuelModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<FuelModel> list;
    FuelUserAdapter fuelUserAdapter;
    EditText search;
    SwipeRefreshLayout swip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        recyclerView = findViewById(R.id.resviewu);
        search = findViewById(R.id.edsearchu);
        swip= findViewById(R.id.sweepHome);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        list = new ArrayList<>();
//        fuelUserAdapter = new FuelUserAdapter(getApplicationContext(),list);
//
//        recyclerView.setAdapter(fuelUserAdapter);
//        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
//        Call<List<FuelModel>> call = apiService.getAllStations();
//        int mobile = getIntent().getIntExtra("userMobile", 0);

        getData();

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData();
            }
        });
//        call.enqueue(new Callback<List<FuelModel>>() {
//            @Override
//            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
//                list.addAll(response.body());
//                Log.d("TAG","Response = "+list);
//                fuelUserAdapter.setData(list);
//            }
//
//            @Override
//            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
//                Log.d("TAG","Response = "+t.toString());
//            }
//        });
//reference for search = https://gist.github.com/codinginflow/ea0d9aeb791fb2eac190befcec448909
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

    }

    private void getData() {
        list = new ArrayList<>();
        fuelUserAdapter = new FuelUserAdapter(getApplicationContext(),list);

        recyclerView.setAdapter(fuelUserAdapter);
        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
        Call<List<FuelModel>> call = apiService.getAllStations();
        int mobile = getIntent().getIntExtra("userMobile", 0);

        call.enqueue(new Callback<List<FuelModel>>() {
            @Override
            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
                list.addAll(response.body());
                Log.d("TAG","Response = "+list);
                fuelUserAdapter.setData(list);
                swip.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    private void filter(String text){
        ArrayList<FuelModel> filterList = new ArrayList<>();

        for (FuelModel item: list){
            if (item.getStationLocationURL().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        fuelUserAdapter.setData(filterList);
    }
}
