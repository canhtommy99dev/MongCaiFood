package com.alexmedia.mongcaifood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexmedia.mongcaifood.Activity.ImageFullScreen;
import com.alexmedia.mongcaifood.Model.ModelInfoCuaHang;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;
import java.util.List;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AdapterImageFull extends RecyclerView.Adapter<AdapterImageFull.ViewHolder> {
    Context context;
    List<ModelInfoCuaHang>modelInfoCuaHangs;
    public static final String ID = "id";
    public static final String IMAGE66 = "Image";

    public AdapterImageFull(Context context, List<ModelInfoCuaHang> modelInfoCuaHangs) {
        this.context = context;
        this.modelInfoCuaHangs = modelInfoCuaHangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_information_cuahang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(modelInfoCuaHangs.get(position).getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageFullScreen.class);
                intent.putExtra(ID,modelInfoCuaHangs.get(position).getId());
                intent.putExtra(IMAGE66,modelInfoCuaHangs.get(position).getImage());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelInfoCuaHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageInfo);
        }
    }
}
