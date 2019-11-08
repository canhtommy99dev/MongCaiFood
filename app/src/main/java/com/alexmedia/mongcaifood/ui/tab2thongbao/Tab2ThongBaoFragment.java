package com.alexmedia.mongcaifood.ui.tab2thongbao;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexmedia.mongcaifood.R;

public class Tab2ThongBaoFragment extends Fragment {

    private Tab2ThongBaoViewModel mViewModel;

    public static Tab2ThongBaoFragment newInstance() {
        return new Tab2ThongBaoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2_thong_bao_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Tab2ThongBaoViewModel.class);
        // TODO: Use the ViewModel
    }

}
