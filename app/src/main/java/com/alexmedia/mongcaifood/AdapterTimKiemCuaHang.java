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

public class AdapterTimKiemCuaHang extends ArrayAdapter<ListDanhSach> {
    Activity activity;
    List<ListDanhSach> modelImage;

    public AdapterTimKiemCuaHang(Activity activity, List<ListDanhSach> listDanhSaches) {
        super(activity, R.layout.adapter_timkiem, listDanhSaches);
        this.activity = activity;
        this.modelImage = listDanhSaches;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View listItems = inflater.inflate(R.layout.adapter_timkiem, null, true);
        TextView txtTenCh1 = listItems.findViewById(R.id.txtTenCH1);
        TextView txtDiaChi1 = listItems.findViewById(R.id.txtDiaChi1);
        TextView txtFacebook = listItems.findViewById(R.id.txtFB1);
        TextView txtSodt = listItems.findViewById(R.id.txtsdtGoi);
        ImageView imgAnhMc = listItems.findViewById(R.id.imgCuaHangDoLen);
        ListDanhSach listDanhSach = modelImage.get(position);
        txtTenCh1.setText(listDanhSach.tench);
        txtDiaChi1.setText(listDanhSach.diachi);
        txtFacebook.setText(listDanhSach.facebook);
        txtSodt.setText(listDanhSach.sodt);
        Glide.with(activity).load(listDanhSach.image).centerCrop().into(imgAnhMc);
        return listItems;
    }
}
