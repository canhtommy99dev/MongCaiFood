package com.alexmedia.mongcaifood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alexmedia.mongcaifood.Activity.ImageFullScreen;
import com.alexmedia.mongcaifood.Model.ModelInfoCuaHang;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;

import java.util.List;


public class AdapterCuaHangInfomation extends RecyclerView.Adapter<AdapterCuaHangInfomation.ViewHolder>{
    Context context;
    List<ModelInfoCuaHang> modelInfoCuaHangList;
    public static final String ID = "id";
    public static final String IMAGE66 = "Image";

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).load(modelInfoCuaHangList.get(position).getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, modelInfoCuaHangList.get(position).id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ImageFullScreen.class);
                intent.putExtra(ID,modelInfoCuaHangList.get(position).getId());
                intent.putExtra(IMAGE66,modelInfoCuaHangList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelInfoCuaHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        RelativeLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageInfo);
            layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
