/**
 * Owner Login Class
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelway.Api.ApiStation;
import com.example.fuelway.Service.StationService;
import com.example.fuelway.model.FuelModel;
import com.example.fuelway.model.FuelQueueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerLogin extends AppCompatActivity {

    TextView signup;
    EditText sname, nic;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        loginbtn = findViewById(R.id.logbtnow);
        sname = findViewById(R.id.unamelogow);
        nic = findViewById(R.id.passtxtlgowe);
        signup = findViewById(R.id.signuplinkow);

        String ownerNic = nic.getText().toString();
        FuelModel fuelModel = new FuelModel();
        nic.setText(ownerNic);
//        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
//        Call<FuelModel> call = apiService.getStation(ownerNic);

//        call.clone().enqueue(new Callback<FuelModel>() {
//            @Override
//            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<FuelModel> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Retrofit Failed", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                    Intent intent = new Intent(OwnerLogin.this, UpdateStationScreen.class);
//                    intent.putExtra("_id",fuelModel.get_id());
//                    intent.putExtra("fuelStationName", fuelModel.getFuelStationName());
//                    intent.putExtra("ownerNIC", fuelModel.getOwnerNIC());
//                    intent.putExtra("stationAddress", fuelModel.getStationLocationURL());
//                    intent.putExtra("petrol", fuelModel.isPetrol());
//                    intent.putExtra("diesel", fuelModel.isDiesel());
//                    intent.putExtra("petrolAT", fuelModel.getPetrolAT());
//                    intent.putExtra("dieselAT", fuelModel.getDieselAT());
//                    intent.putExtra("petrolL", fuelModel.getPetrolL());
//                    intent.putExtra("dieselL", fuelModel.getDieselL());
//                    intent.putExtra("petrolFT", fuelModel.getPetrolFT());
//                    intent.putExtra("dieselFT", fuelModel.getDieselFT());
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OwnerRegisterScreen.class);
                startActivity(i);
            }
        });

        //Login Button
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(sname.getText().toString()) || TextUtils.isEmpty(nic.getText().toString())){
                    Toast.makeText(OwnerLogin.this,"Station Name / NIC Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }
            }
        });


    }
//https://www.youtube.com/watch?v=7aRn2Ch7Cs0

    //Login Method
    private void login() {
        FuelModel fuelModel = new FuelModel();
        fuelModel.setFuelStationName(sname.getText().toString());
        fuelModel.setOwnerNIC(nic.getText().toString());

        StationService apiService = ApiStation.getRetrofit().create(StationService.class);
        Call<FuelModel> call = apiService.stationLogin(fuelModel);

        //Station Owner Login
        call.enqueue(new Callback<FuelModel>() {
            @Override
            public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {

                if(response.isSuccessful()){
                    Toast.makeText(OwnerLogin.this,"Login Successful", Toast.LENGTH_LONG).show();
                    FuelModel fuelModel = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(OwnerLogin.this, OwnerHome.class);
//                    intent.putExtra("_id",fuelModel.get_id());
                    intent.putExtra("fuelStationName", fuelModel.getFuelStationName());
                    intent.putExtra("ownerNIC", fuelModel.getOwnerNIC());
//                    intent.putExtra("stationAddress", fuelModel.getStationLocationURL());
//                    intent.putExtra("petrol", fuelModel.isPetrol());
//                    intent.putExtra("diesel", fuelModel.isDiesel());
//                    intent.putExtra("petrolAT", fuelModel.getPetrolAT());
//                    intent.putExtra("dieselAT", fuelModel.getDieselAT());
//                    intent.putExtra("petrolL", fuelModel.getPetrolL());
//                    intent.putExtra("dieselL", fuelModel.getDieselL());
//                    intent.putExtra("petrolFT", fuelModel.getPetrolFT());
//                    intent.putExtra("dieselFT", fuelModel.getDieselFT());
                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(OwnerLogin.this,UpdateStationScreen.class).putExtra("ownerNIC",fuelModel.getOwnerNIC()).putExtra("fuelStationName",fuelModel.getFuelStationName()));
                        }
                    },700);

                }else{
                    Toast.makeText(OwnerLogin.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<FuelModel> call, Throwable t) {
                Toast.makeText(OwnerLogin.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}