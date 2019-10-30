package com.alexmedia.mongcaifood.ui.ui.danhmuc;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alexmedia.mongcaifood.Adapter.PagerApda;
import com.alexmedia.mongcaifood.MapChiDuongMC;
import com.alexmedia.mongcaifood.Model.ViewPagerModel;
import com.alexmedia.mongcaifood.R;

import java.util.ArrayList;
import java.util.List;

public class DanhMucFragment extends Fragment {

    private DanhMucViewModel mViewModel;

    ViewPager viewPager;
    PagerApda adapter;
    List<ViewPagerModel> array;
    Integer[] color = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button btnClick;


    public static DanhMucFragment newInstance() {
        return new DanhMucFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.danh_muc_fragment, container, false);
        array = new ArrayList<>();
        btnClick = view.findViewById(R.id.clickStart);
        array.add(new ViewPagerModel(R.drawable.ansang,"Ăn Sáng"));
        array.add(new ViewPagerModel(R.drawable.riceicon,"Ăn Trưa"));
        array.add(new ViewPagerModel(R.drawable.dinner,"Ăn Tối"));
        array.add(new ViewPagerModel(R.drawable.iconnhahang,"Nhà Hàng"));
        array.add(new ViewPagerModel(R.drawable.anvatc,"Ăn Vặt"));
        array.add(new ViewPagerModel(R.drawable.launuong,"Lẩu Nướng"));
        array.add(new ViewPagerModel(R.drawable.beer,"Nhậu"));
        array.add(new ViewPagerModel(R.drawable.icondrink,"Đồ Uống"));
        array.add(new ViewPagerModel(R.drawable.ic_map_black_24dp,"Bản Đồ"));
        adapter = new PagerApda(array,inflater,getContext());
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        Integer[] color_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color9)
        };
        color = color_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position <(adapter.getCount() -1) && position < (color.length -1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    color[position],
                                    color[position + 1]
                            )
                    );
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem()==0) {
                    Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==1) {
                    Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==2) {
                    Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==3) {
                    Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==4) {
                    Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==5) {
                    Toast.makeText(getContext(), "5", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==6) {
                    Toast.makeText(getContext(), "6", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==7) {
                    Toast.makeText(getContext(), "7", Toast.LENGTH_SHORT).show();
                }
                if (viewPager.getCurrentItem()==8) {
                    Intent intent = new Intent(getContext(), MapChiDuongMC.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DanhMucViewModel.class);
        // TODO: Use the ViewModel
    }

}
