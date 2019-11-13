package com.alexmedia.mongcaifood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterNhaHang extends ArrayAdapter<ListDanhSach> {
    Activity activity;
    List<ListDanhSach> listDanhSaches;

    public AdapterNhaHang(Activity activity, List<ListDanhSach> listDanhSaches) {
        super(activity,R.layout.adapter_nhahang,listDanhSaches);
        this.activity = activity;
        this.listDanhSaches = listDanhSaches;
    }

    @Override
    public int getCount() {
        return listDanhSaches.size();
    }

    @Nullable
    @Override
    public ListDanhSach getItem(int position) {
        return listDanhSaches.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View listItems = inflater.inflate(R.layout.adapter_nhahang, null, true);
        TextView txtTenCh1 = listItems.findViewById(R.id.txtTenCH4);
        TextView txtDiaChi1 = listItems.findViewById(R.id.txtDiaChi4);
        TextView txtFacebook = listItems.findViewById(R.id.txtFB4);
        TextView txtSodt = listItems.findViewById(R.id.txtsdtGoi4);
        ImageView imgAnhMc = listItems.findViewById(R.id.imgCuaHangDoLen4);
        ListDanhSach listDanhSach = listDanhSaches.get(position);
        txtTenCh1.setText(listDanhSach.tench);
        txtDiaChi1.setText(listDanhSach.diachi);
        txtFacebook.setText(listDanhSach.facebook);
        txtSodt.setText(listDanhSach.sodt);
        Glide.with(activity).load(listDanhSach.image).centerCrop().into(imgAnhMc);
        Animation animation = AnimationUtils.loadAnimation(activity,R.anim.bouncein);
        listItems.startAnimation(animation);
        return listItems;
    }
}