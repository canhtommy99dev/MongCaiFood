package com.alexmedia.mongcaifood.Adapter;

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

import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class AdapterAnSang extends ArrayAdapter<ListDanhSach> {

    Activity activity;
    List<ListDanhSach> listDanhSaches;

    public AdapterAnSang(Activity activity,List<ListDanhSach> listDanhSaches) {
        super(activity, R.layout.adapter_ansang,listDanhSaches);
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
        View listItems = inflater.inflate(R.layout.adapter_ansang, null, true);
        TextView txtTenCh1 = listItems.findViewById(R.id.txtTenCH12);
        TextView txtDiaChi1 = listItems.findViewById(R.id.txtDiaChi12);
        TextView txtFacebook = listItems.findViewById(R.id.txtFB12);
        TextView txtSodt = listItems.findViewById(R.id.txtsdtGoi1);
        ImageView imgAnhMc = listItems.findViewById(R.id.imgCuaHangDoLen1);
        ListDanhSach listDanhSach = listDanhSaches.get(position);
        txtTenCh1.setText(listDanhSach.getTench());
        txtDiaChi1.setText(listDanhSach.getDiachi());
        txtFacebook.setText(listDanhSach.getFacebook());
        txtSodt.setText(listDanhSach.getSodt());
        Glide.with(activity).load(listDanhSach.getImage()).centerCrop().into(imgAnhMc);
        Animation animation = AnimationUtils.loadAnimation(activity,R.anim.bouncein);
        listItems.startAnimation(animation);
        return listItems;
    }
}
