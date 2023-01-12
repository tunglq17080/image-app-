package com.example.addimageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    // khai báo, khởi tạo list image
    private List<Image> imageList = new ArrayList<>();


    public ImageAdapter(Context context) {
        this.context = context;
    }

    // set data adapter
    public void setData(List<Image> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        Image image = imageList.get(position);

        // load image using Glide
        Glide.with(context)
                .load(image.getUrl()).placeholder(R.drawable.error)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (imageList != null)
            return imageList.size();
        else return 0;
    }

}


