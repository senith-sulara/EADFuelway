/*
*
* this  is Station owner adapter
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

import com.example.fuelway.QueueScreen;
import com.example.fuelway.R;
import com.example.fuelway.UpdateStationScreen;
import com.example.fuelway.model.FuelModel;

import java.util.List;

public class FuelUserAdapter extends RecyclerView.Adapter<FuelUserAdapter.fuelUserVH>{

    List<FuelModel> stationList;
    Context context;


    public FuelUserAdapter(Context context, List<FuelModel> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    public void setData(List<FuelModel> stationList) {
        this.stationList = stationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuelUserAdapter.fuelUserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FuelUserAdapter.fuelUserVH(LayoutInflater.from(context).inflate(R.layout.categorycard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FuelUserAdapter.fuelUserVH holder, int position) {
        FuelModel fuelModel = stationList.get(position);

        String fuelStationName = fuelModel.getFuelStationName();
        String stationAddress = fuelModel.getStationLocationURL();

        holder.fuelStationName.setText(fuelStationName);
        holder.stationAddress.setText(stationAddress);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QueueScreen.class);
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
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class fuelUserVH extends RecyclerView.ViewHolder {
        TextView fuelStationName, stationAddress;
        LinearLayout view;
        public fuelUserVH(@NonNull View itemView) {
            super(itemView);
            fuelStationName = itemView.findViewById(R.id.txtcard);
            stationAddress = itemView.findViewById(R.id.txtadd);
            view = itemView.findViewById(R.id.lilay);
        }
    }
}
