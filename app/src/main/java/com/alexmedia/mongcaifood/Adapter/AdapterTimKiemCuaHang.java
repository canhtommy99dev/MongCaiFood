package com.alexmedia.mongcaifood.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterTimKiemCuaHang extends ArrayAdapter<ListDanhSach> {
    Activity activity;
    List<ListDanhSach> modelImage;
    private Filter chfilter;

    public AdapterTimKiemCuaHang(Activity activity, List<ListDanhSach> listDanhSaches) {
        super(activity, R.layout.adapter_timkiem, listDanhSaches);
        this.activity = activity;
        this.modelImage = listDanhSaches;
    }

    @Override
    public int getCount() {
        return modelImage.size();
    }
    @Override
    public ListDanhSach getItem(int position) {
        return modelImage.get(position);
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
        txtTenCh1.setText(listDanhSach.getTench());
        txtDiaChi1.setText(listDanhSach.getDiachi());
        txtFacebook.setText(listDanhSach.getFacebook());
        txtSodt.setText(listDanhSach.getSodt());
        Glide.with(activity).load(listDanhSach.getImage()).centerCrop().into(imgAnhMc);
        Animation animation = AnimationUtils.loadAnimation(activity,R.anim.bouncein);
        listItems.startAnimation(animation);
        return listItems;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return tench;
    }

    private Filter tench = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<ListDanhSach> listDanhSaches = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                listDanhSaches.addAll(modelImage);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ListDanhSach itemtench:modelImage){
                    if (itemtench.getTench().toLowerCase().contains(filterPattern)){
                        listDanhSaches.add(itemtench);
                    }
                }
            }
            results.values = listDanhSaches;
            results.count = listDanhSaches.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((ListDanhSach) resultValue).getTench();
        }
    };
}
