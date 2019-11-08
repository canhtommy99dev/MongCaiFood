package com.alexmedia.mongcaifood.ui.thongbao;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexmedia.mongcaifood.R;
import com.alexmedia.mongcaifood.ui.tab1khampha.Tab1KhamPhaFragment;
import com.alexmedia.mongcaifood.ui.tab2thongbao.Tab2ThongBaoFragment;
import com.google.android.material.tabs.TabLayout;

public class ThongBaoFragment extends Fragment {

    public static ThongBaoFragment newInstance() {
        return new ThongBaoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.thong_bao_fragment, container, false);
        getActivity().setTitle("Thông Báo");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyTabPagerAdapter tabPagerAdapter = new MyTabPagerAdapter(getFragmentManager());
        ViewPager viewPager = getView().findViewById(R.id.viewPager1);
        TabLayout tabLayout = getView().findViewById(R.id.tabsdieuche);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    static class MyTabPagerAdapter extends FragmentPagerAdapter{

        public MyTabPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0){
                fragment = new  Tab1KhamPhaFragment();
            }else if (position == 1){
                fragment = new Tab2ThongBaoFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0){
                title = "Khám Phá";
            }
            else if (position == 1){
                title = "Thông Báo";
            }
            return title;
        }
    }
}
