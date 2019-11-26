package com.alexmedia.mongcaifood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexmedia.mongcaifood.Model.ModelDulich;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterDuLich extends BaseAdapter {

    Context context;
    List<ModelDulich> modelDuliches;
    int myLayout;

    public AdapterDuLich(Context context, List<ModelDulich> modelDuliches, int myLayout) {
        this.context = context;
        this.modelDuliches = modelDuliches;
        this.myLayout = myLayout;
    }

    @Override
    public int getCount() {
        return modelDuliches.size();
    }

    @Override
    public Object getItem(int position) {
        return modelDuliches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = convertView;
        ViewHolder holder = new ViewHolder();
        if (rowview == null){
            rowview = inflater.inflate(myLayout,null);
            holder.mg = rowview.findViewById(R.id.imgCuaHangDoLen16222);
            holder.textViewGioithieu = rowview.findViewById(R.id.txtTenDuLich);
            rowview.setTag(holder);
        }else {
            holder = (ViewHolder) rowview.getTag();
        }
        holder.textViewGioithieu.setText(modelDuliches.get(position).getNamedulich());
        Glide.with(context).load(modelDuliches.get(position).getImagedulich()).centerCrop().into(holder.mg);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.bouncein);
        rowview.startAnimation(animation);
        return rowview;
    }

    private class ViewHolder{
        ImageView mg;
        TextView textViewGioithieu;
    }
}
