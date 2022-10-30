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

import com.example.fuelway.model.station;

import java.util.List;

public class stationAdapter extends RecyclerView.Adapter<stationAdapter.stationViewHolder> {

    List<station> stationList;
    Context context;


    public stationAdapter(Context context, List<station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    public void setData(List<station> stationList) {
        this.stationList = stationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public stationAdapter.stationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new stationAdapter.stationViewHolder(LayoutInflater.from(context).inflate(R.layout.categorycard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull stationAdapter.stationViewHolder holder, int position) {
        station station = stationList.get(position);

        String fuelStationName = station.getFuelStationName();
        String stationAddress = station.getStationAddress();

        holder.fuelStationName.setText(fuelStationName);
        holder.stationAddress.setText(stationAddress);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStationScreen.class);
                intent.putExtra("_id",station.getId());
                intent.putExtra("fuelStationName", station.getFuelStationName());
                intent.putExtra("stationAddress", station.getStationAddress());
                intent.putExtra("isPetrolAvailable", station.isPetrolAvailable());
                intent.putExtra("isDieselAvailable", station.isDieselAvailable());
                intent.putExtra("fuelUpdatedDateTime", station.getFuelUpdatedDateTime());
                intent.putExtra("petrolLit", station.getPetrolLit());
                intent.putExtra("dieselLit", station.getDieselLit());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedUser(station station);
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class stationViewHolder extends RecyclerView.ViewHolder {
        TextView fuelStationName, stationAddress;
        LinearLayout view;

        public stationViewHolder(@NonNull View itemView) {
            super(itemView);
            fuelStationName = itemView.findViewById(R.id.txtcard);
            stationAddress = itemView.findViewById(R.id.txtadd);
            view = itemView.findViewById(R.id.lilay);
        }
    }
}
