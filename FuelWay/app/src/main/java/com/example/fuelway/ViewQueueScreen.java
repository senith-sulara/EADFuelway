/**
 * View Queue when user join to queue
 *
 */
package com.example.fuelway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelway.Api.ApiQueue;
import com.example.fuelway.Service.FuelQueueService;
import com.example.fuelway.model.FuelQueueModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQueueScreen extends AppCompatActivity {
    TextView fuelstation, petQue, dieQue, bike, car, lorry,van, petrolL, dieselL,petrolAT,dieselAT;
    Button leaveBtn;
    Switch petS, dieS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_queue_screen);

        fuelstation=findViewById(R.id.textView5v);
        petQue=findViewById(R.id.textView18v);
        dieQue=findViewById(R.id.textView18dv);
        bike=findViewById(R.id.textView26v);
        car=findViewById(R.id.textView27v);
        lorry=findViewById(R.id.textView28v);
        van=findViewById(R.id.textView7v);
        petrolL=findViewById(R.id.textView17v);
        dieselL=findViewById(R.id.textView17dv);
        leaveBtn = findViewById(R.id.button3v);
        petS = findViewById(R.id.switch5v);
        dieS = findViewById(R.id.switch6v);
        petrolAT= findViewById(R.id.textView22v);
        dieselAT= findViewById(R.id.textView22dv);

        String stationName = getIntent().getStringExtra("stationName");
        String parivalTime = getIntent().getStringExtra("parivalTime");
        String darivalTime = getIntent().getStringExtra("darivalTime");
        int petlit = getIntent().getIntExtra("pliters", 0);
        int dielit = getIntent().getIntExtra("dliters", 0);
        int userMobile = getIntent().getIntExtra("userMobile", 0);
        boolean pet = getIntent().getBooleanExtra("petrol", false);
        boolean dies = getIntent().getBooleanExtra("diesel", false);


        FuelQueueModel fuelQueueModel = new FuelQueueModel();
        FuelQueueService fuelQueueService = ApiQueue.getRetrofit().create(FuelQueueService.class);
        Call<Object> call = fuelQueueService.getAllQueue(stationName);
        Call<Object> callp = fuelQueueService.getPetQueue(stationName);
        Call<Object> calld = fuelQueueService.getDieQueue(stationName);

        Call<Object> call1 = fuelQueueService.getCarQueue(stationName);
        Call<Object> call2 = fuelQueueService.getBikeQueue(stationName);
        Call<Object> call3 = fuelQueueService.getLorryQueue(stationName);
        Call<Object> call4 = fuelQueueService.getVanQueue(stationName);
        fuelQueueModel.setJoined(Boolean.parseBoolean("false"));
        fuelstation.setText(stationName);
        dieS.setClickable(false);
        petS.setClickable(false);

        petS.setChecked(pet);
        dieS.setChecked(dies);
        if(petlit == 0){
            petrolL.setText("");
            petrolAT.setText(parivalTime);
        }else if(petlit > 0){
            petrolL.setText(petlit +"L remaining");
            petrolAT.setText(null);
        }

        if(dielit == 0){
            dieselL.setText("");
            dieselAT.setText(darivalTime);
        }else if(dielit > 0){
            dieselL.setText(dielit +"L remaining");
            dieselAT.setText(null);
        }

        //get petrol queue
        callp.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                petQue.setText(Math.round((Double) data)+" in Queue");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get diesel queue
        calld.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                dieQue.setText(Math.round((Double) data) +" in Queue");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        //get car queue
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

        //get bike queue
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

        //get lorry queue
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

        //get van queue
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

        //Leave Queue method
        leaveBtn.setOnClickListener(new View.OnClickListener() {
            //Update Queue When User Leave
            Call<FuelQueueModel> fuelQueueModelCall = fuelQueueService.updateQueue(userMobile, fuelQueueModel);

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new  AlertDialog.Builder(view.getContext());
                builder.setTitle("Caution");
                builder.setMessage("Are You sure to Leave");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Stay on Queue", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fuelQueueModelCall.enqueue(new Callback<FuelQueueModel>() {
                            @Override
                            public void onResponse(Call<FuelQueueModel> call, Response<FuelQueueModel> response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(ViewQueueScreen.this, UserScreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "You Left From the Queue", Toast.LENGTH_SHORT).show();
                                    dialogInterface.cancel();
                                }
                            }

                            @Override
                            public void onFailure(Call<FuelQueueModel> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });
                builder.show();

            }
        });
    }
}