package com.alexmedia.mongcaifood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLauNuong extends ArrayAdapter<ListDanhSach> {
    Activity activity;
    List<ListDanhSach> listDanhSaches;

    public AdapterLauNuong(Activity activity,List<ListDanhSach> listDanhSaches) {
        super(activity,R.layout.adapter_launuong,listDanhSaches);
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
        View listItems = inflater.inflate(R.layout.adapter_launuong, null, true);
        TextView txtTenCh2 = listItems.findViewById(R.id.txtTenCH7);
        TextView txtDiaChi2 = listItems.findViewById(R.id.txtDiaChi7);
        TextView txtFacebook2= listItems.findViewById(R.id.txtFB7);
        TextView txtSodt2 = listItems.findViewById(R.id.txtsdtGoi7);
        ImageView imgAnhMc = listItems.findViewById(R.id.imgCuaHangDoLen7);
        ListDanhSach listDanhSach = listDanhSaches.get(position);
        txtTenCh2.setText(listDanhSach.tench);
        txtDiaChi2.setText(listDanhSach.diachi);
        txtFacebook2.setText(listDanhSach.facebook);
        txtSodt2.setText(listDanhSach.sodt);
        Glide.with(activity).load(listDanhSach.image).centerCrop().into(imgAnhMc);
        return listItems;
    }
}
