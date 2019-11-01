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

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterAntrua extends ArrayAdapter<ListDanhSach> {
    Activity activity;
    List<ListDanhSach> listDanhSaches;

    public AdapterAntrua(Activity activity,List<ListDanhSach> listDanhSaches) {
        super(activity,R.layout.adapter_ansang,listDanhSaches);
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
        View listItems = inflater.inflate(R.layout.adapterantrua, null, true);
        TextView txtTenCh1 = listItems.findViewById(R.id.txtTenCH2);
        TextView txtDiaChi1 = listItems.findViewById(R.id.txtDiaChi2);
        TextView txtFacebook = listItems.findViewById(R.id.txtFB2);
        TextView txtSodt = listItems.findViewById(R.id.txtsdtGoi2);
        ImageView imgAnhMc = listItems.findViewById(R.id.imgCuaHangDoLen2);
        ListDanhSach listDanhSach = listDanhSaches.get(position);
        txtTenCh1.setText(listDanhSach.tench);
        txtDiaChi1.setText(listDanhSach.diachi);
        txtFacebook.setText(listDanhSach.facebook);
        txtSodt.setText(listDanhSach.sodt);
        Glide.with(activity).load(listDanhSach.image).centerCrop().into(imgAnhMc);
        return listItems;
    }
}
