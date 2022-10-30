/**
 * Author: Senith S
 * Join to the Queue
 *
 */

package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelway.Api.ApiQueue;
import com.example.fuelway.Service.FuelQueueService;
import com.example.fuelway.model.FuelQueueModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinPetQueue extends AppCompatActivity {
    EditText mobile;
    RadioButton car, bike,van,lorry, petrol,diesel;
    boolean join;
    String type,fuelType;
    Button joinbtn, cancel;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_pet_queue);


        mobile=findViewById(R.id.editTextTextPersonName);
        car=findViewById(R.id.radioButton4);
        bike=findViewById(R.id.radioButton3);
        van=findViewById(R.id.radioButton6);
        lorry=findViewById(R.id.radioButton5);
        petrol=findViewById(R.id.radioButton);
        diesel=findViewById(R.id.radioButton2);
        joinbtn=findViewById(R.id.button4);
        txt= findViewById(R.id.textView6);

        String stationName = getIntent().getStringExtra("stationName");
        String parivalTime = getIntent().getStringExtra("petrolAT");
        String darivalTime = getIntent().getStringExtra("dieselAT");
        int pliters = getIntent().getIntExtra("petrolL", 0);
        int dliters = getIntent().getIntExtra("dieselL", 0);
        boolean pet = getIntent().getBooleanExtra("petrol", false);
        boolean dies = getIntent().getBooleanExtra("diesel", false);

        txt.setText(stationName);


        if(pet == false){
            petrol.setClickable(false);
        }

        if(dies== false){
            diesel.setClickable(false);
        }
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(mobile.length() == 10){

                        //Select Vehicle type
                        if (car.isChecked()) {
                            type = "Car";
                        } else if(bike.isChecked()){
                            type = "Bike";
                        }else if(van.isChecked()){
                            type= "Van";
                        }else{
                            type= "Lorry";
                        }
                        //select Fuel type
                        if(petrol.isChecked()){
                            fuelType="Petrol";
                        }else{
                            fuelType= "Diesel";
                        }
                        FuelQueueModel fuelQueueModel = new FuelQueueModel();
                        fuelQueueModel.setStationName(stationName);
                        fuelQueueModel.setUserMobile(Integer.parseInt(mobile.getText().toString()));
                        fuelQueueModel.setFuelType(fuelType);
                        fuelQueueModel.setVehicalType(type);
                        fuelQueueModel.setJoined(Boolean.parseBoolean("true"));

                        FuelQueueService apiService = ApiQueue.getRetrofit().create(FuelQueueService.class);
                        //Add to the vehicle queue
                        Call call = apiService.createQueue(fuelQueueModel);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(JoinPetQueue.this, ViewQueueScreen.class);
                                    intent.putExtra("userMobile", Integer.parseInt(mobile.getText().toString()));
                                    intent.putExtra("stationName", stationName);
                                    intent.putExtra("pliters", pliters);
                                    intent.putExtra("dliters", dliters);
                                    intent.putExtra("parivalTime", parivalTime);
                                    intent.putExtra("darivalTime", darivalTime);
                                    intent.putExtra("petrol", pet);
                                    intent.putExtra("diesel", dies);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "You Join to the Queue", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"Please add valid mobile number", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }
        });
    }
}