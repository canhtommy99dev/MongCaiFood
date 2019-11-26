package com.alexmedia.mongcaifood.ui.tab1khampha;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexmedia.mongcaifood.Activity.ChoMongCaiActivity;
import com.alexmedia.mongcaifood.R;

public class Tab1KhamPhaFragment extends Fragment {

    private Tab1KhamPhaViewModel mViewModel;
    TextView chomongcai;

    public static Tab1KhamPhaFragment newInstance() {
        return new Tab1KhamPhaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_kham_pha_fragment,container,false);
        chomongcai = view.findViewById(R.id.txtClickChoMongCai);
        chomongcai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChoMongCaiActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Tab1KhamPhaViewModel.class);
        // TODO: Use the ViewModel
    }

}
