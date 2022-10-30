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

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ownerVH>{

    List<FuelModel> stationList;
    Context context;

    public OwnerAdapter(Context context, List<FuelModel> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    public void setData(List<FuelModel> stationList) {
        this.stationList = stationList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ownerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new OwnerAdapter.ownerVH(LayoutInflater.from(context).inflate(R.layout.ownercard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ownerVH holder, int position) {
        FuelModel fuelModel = stationList.get(position);

        String fuelStationName = fuelModel.getFuelStationName();
        String stationAddress = fuelModel.getStationLocationURL();

        holder.fuelStationName.setText(fuelStationName);
        holder.stationAddress.setText(stationAddress);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStationScreen.class);
                intent.putExtra("id",fuelModel.get_id());
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

    public class ownerVH extends RecyclerView.ViewHolder {
        TextView fuelStationName, stationAddress;
        LinearLayout view;
        public ownerVH(@NonNull View itemView) {
            super(itemView);
            fuelStationName = itemView.findViewById(R.id.txtcardo);
            stationAddress = itemView.findViewById(R.id.txtaddo);
            view = itemView.findViewById(R.id.lilayo);
        }
    }
}
