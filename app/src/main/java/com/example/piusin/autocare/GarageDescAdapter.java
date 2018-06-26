package com.example.piusin.autocare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Piusin on 3/31/2018.
 */

public class GarageDescAdapter extends RecyclerView.Adapter<GarageDescAdapter.GarageViewHolder> {
    private Context mCtx;
    private List<GarageDescDataProvider> garageDataProviderList;
    GarageDescDataProvider garageDataProvider;

    public GarageDescAdapter(Context mCtx, List<GarageDescDataProvider> garageDataProviderList) {
        this.mCtx = mCtx;
        this.garageDataProviderList = garageDataProviderList;
    }

    @Override
    public GarageDescAdapter.GarageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.garagedesc_item, null);
        return new GarageDescAdapter.GarageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GarageDescAdapter.GarageViewHolder holder, int position){
        garageDataProvider = garageDataProviderList.get(position);

        Picasso.with(mCtx)
                .load(garageDataProvider.getGarageImage())
                .into(holder.garageImage);
       // holder.garageImage.setImageResource(garageDataProvider.getGarageImage());
        holder.garageName.setText(garageDataProvider.getGarageName());
        holder.garageLocation.setText(garageDataProvider.getGarageLocation());
        holder.garageOperationalTime.setText("Open Time: " + garageDataProvider.getGarageOpenTime() + " - " + garageDataProvider.getGarageCloseTime());
        holder.garageOwnerName.setText(garageDataProvider.getGarageOwnerName());
        holder.garageOwnerExperience.setText(garageDataProvider.getGarageOwnerExperience());
        holder.garageDescription.setText(garageDataProvider.getGarageDesc());
        holder.ownerNumber.setText(garageDataProvider.getGarageOwnerNumber());

    }



    @Override
    public int getItemCount() {
        return garageDataProviderList.size();
    }

    class GarageViewHolder extends RecyclerView.ViewHolder{

        ImageView garageImage;
        TextView garageName;
        TextView garageLocation;
        TextView garageOperationalTime;
        TextView garageOwnerName;
        TextView garageOwnerExperience;
        TextView garageDescription;
        TextView ownerNumber;
        CardView cardView;



        public GarageViewHolder(View itemView) {
            super(itemView);
            garageImage = itemView.findViewById(R.id.garageDescImage);
            garageName = itemView.findViewById(R.id.garageDesName);
            garageLocation = itemView.findViewById(R.id.garagedes_location);
            garageOperationalTime = itemView.findViewById(R.id.garage_operationalTime);
            garageOwnerName = itemView.findViewById(R.id.garageOwnername);
            garageOwnerExperience = itemView.findViewById(R.id.garageownername_experience);
            garageDescription = itemView.findViewById(R.id.garageDescription);
            ownerNumber = itemView.findViewById(R.id.ownerNumber);
            cardView = itemView.findViewById(R.id.cardview_garages);
        }

    }
}
