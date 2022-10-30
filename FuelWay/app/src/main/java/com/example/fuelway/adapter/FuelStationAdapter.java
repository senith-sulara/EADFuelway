/*
* Fuel Station Adapter
* Get Fuel station data into recyclerView
*
*/

package com.example.fuelway.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelway.UpdateStationScreen;
import com.example.fuelway.R;
import com.example.fuelway.model.FuelModel;

import java.util.List;

public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationAdapter.fuelStationViewHolder>{

    List<FuelModel> stationList;
    Context context;


    public FuelStationAdapter(Context context, List<FuelModel> stationList) {
        this.context = context;
        this.stationList = stationList;
    }
//set data to adapter
    public void setData(List<FuelModel> stationList) {
        this.stationList = stationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public fuelStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FuelStationAdapter.fuelStationViewHolder(LayoutInflater.from(context).inflate(R.layout.categorycard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull fuelStationViewHolder holder, int position) {
        FuelModel fuelModel = stationList.get(position);

        String fuelStationName = fuelModel.getFuelStationName();
        String stationAddress = fuelModel.getStationLocationURL();

        holder.fuelStationName.setText(fuelStationName);
        holder.stationAddress.setText(stationAddress);

//Layout click for view more data
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStationScreen.class);
                intent.putExtra("_id",fuelModel.get_id());
                intent.putExtra("fuelStationName", fuelModel.getFuelStationName());
                intent.putExtra("ownerNIC", fuelModel.getOwnerNIC());
                intent.putExtra("stationAddress", fuelModel.getStationLocationURL());
                intent.putExtra("petrol", fuelModel.isPetrol());
                intent.putExtra("diesel", fuelModel.isDiesel());
                intent.putExtra("petrolAT", fuelModel.getPetrolAT());
                intent.putExtra("dieselAT", fuelModel.getDieselAT());
                intent.putExtra("petrolL", fuelModel.getPetrolL());
                intent.putExtra("dieselL", fuelModel.getDieselL());
                intent.putExtra("petrolFT", fuelModel.getPetrolFT());
                intent.putExtra("dieselFT", fuelModel.getDieselFT());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);
            }
        });
    }
//size of the data list
    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class fuelStationViewHolder extends RecyclerView.ViewHolder {

        TextView fuelStationName, stationAddress;
        LinearLayout view;
        public fuelStationViewHolder(@NonNull View itemView) {
            super(itemView);
            fuelStationName = itemView.findViewById(R.id.txtcard);
            stationAddress = itemView.findViewById(R.id.txtadd);
            view = itemView.findViewById(R.id.lilay);
        }
    }
}
