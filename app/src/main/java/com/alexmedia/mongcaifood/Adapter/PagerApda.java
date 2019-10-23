package com.alexmedia.mongcaifood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alexmedia.mongcaifood.Model.ViewPagerModel;
import com.alexmedia.mongcaifood.R;
import com.alexmedia.mongcaifood.ui.ui.danhmuc.DanhMucFragment;

import java.util.List;

public class PagerApda extends PagerAdapter {

    List<ViewPagerModel> models;
    LayoutInflater layoutInflater;
    Context context;

    public PagerApda(List<ViewPagerModel> models, LayoutInflater layoutInflater, Context context) {
        this.models = models;
        this.layoutInflater = layoutInflater;
        this.context = context;
    }

    public PagerApda(List<ViewPagerModel> array, DanhMucFragment danhMucFragment) {
        this.models = array;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itemclick,container,false);
        ImageView img;
        TextView title;
        img = view.findViewById(R.id.imagemc);
        title = view.findViewById(R.id.titlemc);
        img.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
