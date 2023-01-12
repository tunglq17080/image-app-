package com.example.addimageapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    public CustomViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
    }
}
