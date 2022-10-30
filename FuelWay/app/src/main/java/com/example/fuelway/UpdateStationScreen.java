/**
 * Update Station details
 *
 */

package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fuelway.Api.ApiStation;
import com.example.fuelway.Service.StationService;
import com.example.fuelway.model.FuelModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStationScreen extends AppCompatActivity {

    TextView txtAdd,txtName, timepicker,timepicker2;
    int hour, minute, hour2, minute2;
    Switch petrol, diesel;
    EditText petrolLit, dieselLit;
    Button buttonUpdate;
    Boolean petro, diese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station_screen);

//        txtName = findViewById(R.id.textView5);
        txtAdd = findViewById(R.id.address);
        timepicker = findViewById(R.id.timepicker);
        timepicker2 = findViewById(R.id.timepicker2);
        petrol = findViewById(R.id.switch2);
        diesel = findViewById(R.id.switch3);
        petrolLit = findViewById(R.id.petLit);
        dieselLit = findViewById(R.id.dieselLiter);
        buttonUpdate = findViewById(R.id.buttonUpdate);


//        String name = getIntent().getStringExtra("fuelStationName");
        String add = getIntent().getStringExtra("fuelStationName");
        String id = getIntent().getStringExtra("id");
        String time = getIntent().getStringExtra("fuelUpdatedDateTime");

        boolean pet = getIntent().getBooleanExtra("petrol", false);
        boolean dies = getIntent().getBooleanExtra("diesel", false);

        String parivaltime = getIntent().getStringExtra("petrolAT");
        String darivaltime = getIntent().getStringExtra("dieselAT");
        String nic = getIntent().getStringExtra("ownerNIC");

        int petlit = getIntent().getIntExtra("petrolL", 0);
        int dielit = getIntent().getIntExtra("dieselL", 0);

//        txtName.setText(name);

        timepicker.setText(parivaltime);
        timepicker2.setText(darivaltime);
        txtAdd.setText(add);
        petrol.setChecked(pet);
        diesel.setChecked(dies);
        petrolLit.setText(""+ petlit);
        dieselLit.setText(""+ dielit);

        //https://www.youtube.com/watch?v=o-HVE_VxyjQ
        //Pick the time
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        UpdateStationScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hour =  i;
                                minute= i1;

                                String time = hour + ":" + minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    timepicker.setText(f12Hours.format(date));
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,minute);
                timePickerDialog.show();
            }
        });
//https://www.youtube.com/watch?v=o-HVE_VxyjQ
        timepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        UpdateStationScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hour2 =  i;
                                minute2= i1;

                                String time = hour2 + ":" + minute2;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    timepicker2.setText(f12Hours.format(date));
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour2,minute2);
                timePickerDialog.show();
            }
        });




        //update Station details by owner
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(petrol.isChecked()){
                        petro = true;
                    }else {
                        petro = false;
                    }
                    if (diesel.isChecked()) {
                        diese = true;
                    } else {
                        diese = false;
                    }

                    FuelModel fuelModel = new FuelModel();
                    fuelModel.setPetrol(petro);
                    fuelModel.setDiesel(diese);
                    fuelModel.setPetrolAT(timepicker.getText().toString());
                    fuelModel.setDieselAT(timepicker2.getText().toString());
                    fuelModel.setPetrolL(Integer.parseInt(petrolLit.getText().toString()));
                    fuelModel.setDieselL(Integer.parseInt(dieselLit.getText().toString()));
                    StationService stationService = ApiStation.getRetrofit().create(StationService.class);

                    Call call = stationService.updateFuelStation(id, fuelModel);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
//                            Intent intent = new Intent(UpdateStationScreen.this, OwnerHome.class);
//                            intent.putExtra("fuelStationName", fuelModel.getFuelStationName());
//                            intent.putExtra("ownerNIC", fuelModel.getOwnerNIC());
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);

                            finish();
                            Log.d("TAG","Response = "+response.code());
                            Toast.makeText(UpdateStationScreen.this, "Update Success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(UpdateStationScreen.this, "Fail", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(UpdateStationScreen.this, "Fail", Toast.LENGTH_SHORT).show();

                    }
                });
                }catch(Exception e){

                }
            }
        });

//        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
//        Call<FuelModel> call2 = apiService.getStation(nic);
//
//        call2.enqueue(new Callback<FuelModel>() {
//            @Override
//            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
//                if(response.isSuccessful()) {
//                    FuelModel fuelModel = response.body();
////                    petrolLit.setText(fuelModel.getPetrolL());
////                    dieselLit.setText(fuelModel.getDieselL());
////                    timepicker.setText(fuelModel.getPetrolAT());
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            petrolLit.setText(fuelModel.getPetrolL());
//                            dieselLit.setText(fuelModel.getDieselL());
//
//                        }
//                    }, 700);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FuelModel> call, Throwable t) {
//
//            }
//        });

    }
}