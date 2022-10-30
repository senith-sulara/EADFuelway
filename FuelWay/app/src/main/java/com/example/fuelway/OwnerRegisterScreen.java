/**
 * Owner Register Fuel Station
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.fuelway.Api.ApiStation;
import com.example.fuelway.Service.StationService;
import com.example.fuelway.model.FuelModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerRegisterScreen extends AppCompatActivity {

    EditText stationName, nic, location, petrolAT,dieselAT,availableP, availableD;
    Switch petr, dies;
    Button regisbtn;
    Boolean petro, diese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register_screen);
        getSupportActionBar().hide();

        regisbtn = findViewById(R.id.buttonowreg);

        stationName = findViewById(R.id.editTextTextPersonName2);
        nic = findViewById(R.id.editTextTextPersonName3);
        location = findViewById(R.id.editTextTextPersonName4);
        petrolAT = findViewById(R.id.editTextTime);
        dieselAT = findViewById(R.id.editTextTime2);
        availableP = findViewById(R.id.editTextNumber);
        availableD = findViewById(R.id.editTextNumber2);

        petr = findViewById(R.id.switch1);
        dies = findViewById(R.id.switch4);


        //Register Button
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(TextUtils.isEmpty(stationName.getText().toString()) || TextUtils.isEmpty(nic.getText().toString()) || TextUtils.isEmpty(location.getText().toString())) {
                        Toast.makeText(OwnerRegisterScreen.this, "Fields are missing", Toast.LENGTH_LONG).show();
                    }else if(!(nic.getText().toString().matches("^[0-9]{9}[vVxX]$") || nic.getText().toString().matches("^[0-9]{12}$"))){
                        Toast.makeText(OwnerRegisterScreen.this, "Enter Valid NIC", Toast.LENGTH_LONG).show();
                    }else {

                        if (petr.isChecked()) {
                            petro = true;
                        } else {
                            petro = false;
                        }
                        if (dies.isChecked()) {
                            diese = true;
                        } else {
                            diese = false;
                        }
                        FuelModel fuelModel = new FuelModel();
                        fuelModel.setFuelStationName(stationName.getText().toString());
                        fuelModel.setOwnerNIC(nic.getText().toString());
                        fuelModel.setStationLocationURL(location.getText().toString());
                        fuelModel.setPetrolAT(petrolAT.getText().toString());
                        fuelModel.setDieselAT(dieselAT.getText().toString());

                        fuelModel.setPetrolL(Integer.parseInt(availableP.getText().toString()));
                        fuelModel.setDieselL(Integer.parseInt(availableD.getText().toString()));

                        fuelModel.setPetrol(petro);
                        fuelModel.setDiesel(diese);

                        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
                        Call<FuelModel> call2 = apiService.addFuelStation(fuelModel);

                        //Add Fuel Station method
                        call2.enqueue(new Callback<FuelModel>() {
                            @Override
                            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                                if (response.isSuccessful()) {
                                    Intent i = new Intent(OwnerRegisterScreen.this, OwnerLogin.class);
                                    startActivity(i);
                                    Toast.makeText(OwnerRegisterScreen.this, "Registration Success", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<FuelModel> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                }catch (Exception e){

                }
            }
        });

    }
}