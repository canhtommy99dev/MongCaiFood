package com.alexmedia.mongcaifood.ui.thongbao;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexmedia.mongcaifood.R;

public class ThongBaoFragment extends Fragment {

    private ThongBaoViewModel mViewModel;

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
        mViewModel = ViewModelProviders.of(this).get(ThongBaoViewModel.class);
        // TODO: Use the ViewModel
    }

}
