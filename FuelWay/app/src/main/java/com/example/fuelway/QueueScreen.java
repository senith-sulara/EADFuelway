/**
 * View Station details
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.fuelway.Api.ApiQueue;
import com.example.fuelway.Service.FuelQueueService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueScreen extends AppCompatActivity {

    TextView name,petrolLit,dieselLit, petQueue,DieQueue,petrolAT, dieselAT, bike,car,lorry,van;
    Switch petW, dieW;
    Button joinbtn1, joinbtn2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_screen);

        name=findViewById(R.id.textView5);
        petrolLit=findViewById(R.id.textView17);
        dieselLit=findViewById(R.id.textView17d);
        petQueue=findViewById(R.id.textView18);
        DieQueue=findViewById(R.id.textView18d);
        petrolAT=findViewById(R.id.textView22);
        dieselAT=findViewById(R.id.textView22d);
        bike=findViewById(R.id.textView26);
        car=findViewById(R.id.textView27);
        lorry=findViewById(R.id.textView28);
        van=findViewById(R.id.textView7);

        petW=findViewById(R.id.switch5);
        dieW=findViewById(R.id.switch6);

        button3=findViewById(R.id.button3);

        String add = getIntent().getStringExtra("fuelStationName");
        String id = getIntent().getStringExtra("_id");

        boolean pet = getIntent().getBooleanExtra("petrol", false);
        boolean dies = getIntent().getBooleanExtra("diesel", false);

        String parivaltime = getIntent().getStringExtra("petrolAT");
        String darivaltime = getIntent().getStringExtra("dieselAT");
        String nic = getIntent().getStringExtra("ownerNIC");

        int petlit = getIntent().getIntExtra("petrolL", 0);
        int dielit = getIntent().getIntExtra("dieselL", 0);

        FuelQueueService fuelQueueService = ApiQueue.getRetrofit().create(FuelQueueService.class);
        Call<Object> call = fuelQueueService.getAllQueue(add);
        Call<Object> callp = fuelQueueService.getPetQueue(add);
        Call<Object> calld = fuelQueueService.getDieQueue(add);

        Call<Object> call1 = fuelQueueService.getCarQueue(add);
        Call<Object> call2 = fuelQueueService.getBikeQueue(add);
        Call<Object> call3 = fuelQueueService.getLorryQueue(add);
        Call<Object> call4 = fuelQueueService.getVanQueue(add);

        name.setText(add);
        dieW.setClickable(false);
        petW.setClickable(false);

        petW.setChecked(pet);
        dieW.setChecked(dies);

        if(petlit == 0){
            petrolLit.setText("");
            petrolAT.setText(parivaltime);
        }else if(petlit > 0){
            petrolLit.setText(petlit +"L remaining");
            petrolAT.setText(null);
        }

        if(dielit == 0){
            dieselLit.setText("");
            dieselAT.setText(darivaltime);
        }else if(dielit > 0){
            dieselLit.setText(dielit +"L remaining");
            dieselAT.setText(null);
        }

        //get petrol Queue
        callp.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                petQueue.setText(Math.round((Double) data)+" in Queue");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get diesel Queue
        calld.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                DieQueue.setText(Math.round((Double) data)+" in Queue");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get car Queue
        call1.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                car.setText("Car: " +Math.round((Double) data));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get bike Queue
        call2.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                bike.setText("Bike: "+Math.round((Double) data));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get lorry Queue
        call3.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                lorry.setText("Lorry: "+Math.round((Double) data));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get van Queue
        call4.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                van.setText("Van: "+Math.round((Double) data));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinPetQueue.class);
                i.putExtra("stationName", add);
                i.putExtra("petrolL", petlit);
                i.putExtra("dieselL", dielit);
                i.putExtra("petrolAT", parivaltime);
                i.putExtra("dieselAT", darivaltime);
                i.putExtra("petrol", pet);
                i.putExtra("diesel", dies);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
            }
        });
    }
}