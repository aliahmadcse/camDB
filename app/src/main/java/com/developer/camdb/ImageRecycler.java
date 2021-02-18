package com.developer.camdb;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.camdb.DataModel.DataConverter;
import com.developer.camdb.DataModel.Image;

import java.util.List;

public class ImageRecycler extends RecyclerView.Adapter<ImageViewHolder> {
    List<Image> data;

    public ImageRecycler(List<Image> images){
        data = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.image_item_layout,
                parent,
                false
        );
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = data.get(position);
        holder.imageView.setImageBitmap(DataConverter.convertByteArrayToBitMap(image.getImage()));
        holder.longitude.setText(String.valueOf(image.getLongitude()));
        holder.latitude.setText(String.valueOf(image.getLatitute()));
        holder.location.setText(image.getLocation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
