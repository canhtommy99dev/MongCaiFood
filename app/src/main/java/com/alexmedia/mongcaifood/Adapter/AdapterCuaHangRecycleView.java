package com.alexmedia.mongcaifood.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexmedia.mongcaifood.Activity.InfomationFoody;
import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCuaHangRecycleView extends RecyclerView.Adapter<AdapterCuaHangRecycleView.SingleItemRowHolder>{

    private ArrayList<ListDanhSach> itemList;
    private Activity activity;
    int layout;
    public static final String ID = "id";
    public static final String TENCH = "tench";
    public static final String ADDRESS = "diachi";
    public static final String TIMEOPENEND = "time";
    public static final String SODIENTHOAI = "thoigian";
    public static final String SHIPTINHTRANG = "tinhtrangship";
    public static final String FACEBOOK_CH = "facebook";
    public static final String IMAGE = "image";

    public AdapterCuaHangRecycleView( Activity activity,ArrayList<ListDanhSach> itemList, int layout) {
        this.itemList = itemList;
        this.activity = activity;
        this.layout = layout;
    }

    @NonNull
    @Override
    public SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null);
        SingleItemRowHolder my = new SingleItemRowHolder(view);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemRowHolder holder, final int position) {
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(),"fonts/robotolight.ttf");
        holder.txtTenCuaHang.setTypeface(typeface);
        holder.txtDiaChi.setTypeface(typeface);
        ListDanhSach listDanhSach = itemList.get(position);
        holder.txtTenCuaHang.setTag(position);
        holder.txtTenCuaHang.setText(listDanhSach.getTench());
        holder.txtDiaChi.setText(listDanhSach.getDiachi());
        Glide.with(activity).load(listDanhSach.getImage()).centerCrop().into(holder.imageCuaHang);
        holder.imageCuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, InfomationFoody.class);
                intent.putExtra(ID,itemList.get(position).getId());
                intent.putExtra(TENCH,itemList.get(position).getTench());
                intent.putExtra(ID,itemList.get(position).getId());
                intent.putExtra(TENCH,itemList.get(position).getTench());
                intent.putExtra(ADDRESS,itemList.get(position).getDiachi());
                intent.putExtra(TIMEOPENEND,itemList.get(position).getThoigian());
                intent.putExtra(SODIENTHOAI,itemList.get(position).getSodt());
                intent.putExtra(SHIPTINHTRANG,itemList.get(position).getTinhtrangship());
                intent.putExtra(FACEBOOK_CH,itemList.get(position).getFacebook());
                intent.putExtra(IMAGE,itemList.get(position).getImage());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size():0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView txtTenCuaHang,txtDiaChi;
        protected ImageView imageCuaHang;
        public SingleItemRowHolder(View view) {
            super(view);
            this.imageCuaHang = view.findViewById(R.id.imageHinhAnhCuaHang);
            this.txtTenCuaHang = view.findViewById(R.id.txtTenCuaHang);
            this.txtDiaChi = view.findViewById(R.id.txtDiaChiCuaHang);
        }

    }
}
