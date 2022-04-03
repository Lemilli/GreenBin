package com.example.misterbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misterbin.R;
import com.example.misterbin.model.LocationData;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    Context context;
    List<LocationData> locationDataList;

    public LocationAdapter(Context context, List<LocationData> locationDataList) {
        this.context = context;
        this.locationDataList = locationDataList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.location_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.locationName.setText(locationDataList.get(position).getLocationName());
        holder.stateName.setText(locationDataList.get(position).getStateName());
        holder.distanceName.setText(locationDataList.get(position).getDistanceName());
        holder.locationImage.setImageResource(locationDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return locationDataList.size();
    }

    public static final class LocationViewHolder extends RecyclerView.ViewHolder{

        ImageView locationImage;
        TextView locationName, stateName, distanceName;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            locationImage = itemView.findViewById(R.id.location_image);
            locationName = itemView.findViewById(R.id.location_name);
            stateName = itemView.findViewById(R.id.state_name);
            distanceName = itemView.findViewById(R.id.distance_name);
        }
    }

}
