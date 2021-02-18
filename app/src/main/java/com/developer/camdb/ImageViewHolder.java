package com.developer.camdb;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView location,latitude,longitude;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cardImage);
        location = itemView.findViewById(R.id.cardLocation);
        latitude = itemView.findViewById(R.id.cardLatitude);
        longitude = itemView.findViewById(R.id.cardLongitude);
    }


}
