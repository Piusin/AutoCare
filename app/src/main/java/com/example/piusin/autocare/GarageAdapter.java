package com.example.piusin.autocare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piusin on 3/30/2018.
 */

public class GarageAdapter extends RecyclerView.Adapter<GarageAdapter.GarageViewHolder> {
    private Context mCtx;
    private List<GarageDataProvider> garageDataProviderList;
    GarageDataProvider garageDataProvider;

    public GarageAdapter(Context mCtx, List<GarageDataProvider> garageDataProviderList) {
        this.mCtx = mCtx;
        this.garageDataProviderList = garageDataProviderList;
    }

    @Override
    public GarageAdapter.GarageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.garage_item, null);
        return new GarageAdapter.GarageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GarageAdapter.GarageViewHolder holder, final int position){
        garageDataProvider = garageDataProviderList.get(position);
        Picasso.with(mCtx)
                .load(garageDataProvider.getGarageImage())
                .into(holder.garageImage);
        holder.garageName.setText(garageDataProvider.getGarageName() + " in ");
        holder.garageLocation.setText(garageDataProvider.getGarageLocation());
        holder.garageOpenTime.setText(garageDataProvider.getGarageOpenTime() + " - ");
        holder.garageCloseTime.setText(garageDataProvider.getGarageCloseTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, GarageDescription.class);
                Bundle bundle = new Bundle();
                bundle.clear();
                bundle.putString("garageId", garageDataProviderList.get(position).getGarageId());
                intent.putExtras(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return garageDataProviderList.size();
    }

    class GarageViewHolder extends RecyclerView.ViewHolder{
        ImageView garageImage;
        TextView garageName;
        TextView garageLocation;
        TextView garageOpenTime;
        TextView garageCloseTime;
        CardView cardView;

        public GarageViewHolder(View itemView) {
            super(itemView);
            garageImage = itemView.findViewById(R.id.garage_image);
            garageName = itemView.findViewById(R.id.garage_name);
            garageLocation = itemView.findViewById(R.id.garage_location);
            garageOpenTime = itemView.findViewById(R.id.garage_openTime);
            garageCloseTime = itemView.findViewById(R.id.garage_closeTime);
            cardView = itemView.findViewById(R.id.cardview_garages);
        }
    }

    public void setFilter(ArrayList<GarageDataProvider> newList){
        garageDataProviderList = new ArrayList<>();
        garageDataProviderList.addAll(newList);
        notifyDataSetChanged();
    }
}
