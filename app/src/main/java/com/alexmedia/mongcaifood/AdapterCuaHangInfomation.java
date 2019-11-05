package com.alexmedia.mongcaifood;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class AdapterCuaHangInfomation extends RecyclerView.Adapter<AdapterCuaHangInfomation.ViewHolder> {
    Context context;
    List<ModelInfoCuaHang> modelInfoCuaHangList;

    public AdapterCuaHangInfomation(Context context, List<ModelInfoCuaHang> modelInfoCuaHangList) {
        this.context = context;
        this.modelInfoCuaHangList = modelInfoCuaHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_information_cuahang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(modelInfoCuaHangList.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return modelInfoCuaHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageInfo);
        }
    }
}
