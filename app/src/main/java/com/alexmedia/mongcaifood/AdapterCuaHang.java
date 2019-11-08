package com.alexmedia.mongcaifood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterCuaHang extends BaseAdapter {

    Context context;
    int mLayout;
    List<ListDanhSach> listDanhSaches;

    public AdapterCuaHang(Context context, int mLayout, List<ListDanhSach> listDanhSaches) {
        this.context = context;
        this.mLayout = mLayout;
        this.listDanhSaches = listDanhSaches;
    }

    @Override
    public int getCount() {
        return listDanhSaches.size();
    }

    @Override
    public Object getItem(int position) {
        return listDanhSaches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imgCuaHang;
        TextView ttxtench,ttxtDiahi,ttxtFacebook;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = convertView;
        ViewHolder holder = new ViewHolder();
        if (rowview == null){
            rowview = inflater.inflate(mLayout,null);
            holder.imgCuaHang = rowview.findViewById(R.id.imgHienThiCuaHang);
            holder.ttxtench = rowview.findViewById(R.id.txtTenCH);
            holder.ttxtDiahi = rowview.findViewById(R.id.txtDiaChi);
            holder.ttxtFacebook = rowview.findViewById(R.id.txtFB);
            rowview.setTag(holder);
        }else {
            holder = (ViewHolder) rowview.getTag();
        }
        holder.ttxtench.setText(listDanhSaches.get(position).tench);
        holder.ttxtDiahi.setText(listDanhSaches.get(position).diachi);
        holder.ttxtFacebook.setText(listDanhSaches.get(position).facebook);
        Glide.with(context).load(listDanhSaches.get(position).image).centerCrop().into(holder.imgCuaHang);
        Animation  animation = AnimationUtils.loadAnimation(context,R.anim.bouncein);
        rowview.startAnimation(animation);
        return rowview;
    }
}
