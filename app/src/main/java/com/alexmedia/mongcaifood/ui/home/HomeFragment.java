package com.alexmedia.mongcaifood.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexmedia.mongcaifood.Adapter.AdapterCuaHangRecycleView;
import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    FlipperLayout fli;
    DatabaseReference docuahang;
    ArrayList<ListDanhSach> cCHMC;
    ProgressBar br;
    Context context;
    Intent intent;
    String image;
    TextView textViewDS;
    AdapterCuaHangRecycleView adapterCuaHangRecycleView;
    RecyclerView recyclerViewCuaHang;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        fli =view.findViewById(R.id.flizzz);
        getActivity().setTitle("Trang Chủ");

        String url[] =new  String[]{
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag1.jpg?alt=media&token=612841dc-63a5-4cc2-928d-362045b8d4fb",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag2.jpg?alt=media&token=d6cfa81f-a199-40b6-933d-3ecfdfcce0cd",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag3.jpg?alt=media&token=0279deda-2491-471c-af91-cd5a140b5884",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag4.jpg?alt=media&token=ce9a12c7-e344-4439-b067-aa29e6c7f629",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag5.jpg?alt=media&token=5e14d856-3cdd-4f19-891a-15bf3d13d3c8",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag6.jpg?alt=media&token=06c62604-d739-41fa-a074-ec093c4bce5d"
        };
        intent = getActivity().getIntent();
        int num_of_pages = 6;
        for (int i = 0; i < num_of_pages; i++) {
            FlipperView v = new FlipperView(getContext());
            v.setImageUrl(url[i])
                    .setImageScaleType(ImageView.ScaleType.FIT_XY) //You can use any ScaleType
                    .setDescription("")
                    .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {

                        }
                    });
            fli.setScrollTimeInSec(6); //setting up scroll time, by default it's 3 seconds
            fli.getScrollTimeInSec(); //returns the scroll time toi sec
            //returns the current position of pager
            fli.addFlipperView(v);
            this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }
        textViewDS = view.findViewById(R.id.txtDanhSachCuaHang);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/robotolight.ttf");
        textViewDS.setTypeface(typeface);
        br = view.findViewById(R.id.pro1116);

        docuahang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        recyclerViewCuaHang = view.findViewById(R.id.recyclerViewDanhSachCuaHang);
        cCHMC = new ArrayList<>();
        recyclerViewCuaHang.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapterCuaHangRecycleView = new AdapterCuaHangRecycleView(getActivity(),cCHMC,R.layout.adapter_cua_hang_recycleview);
        docuahang.addValueEventListener(valueEventListener);
        recyclerViewCuaHang.setAdapter(adapterCuaHangRecycleView);
        return view;

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            cCHMC.clear();
            for (DataSnapshot list:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach = list.getValue(ListDanhSach.class);
                cCHMC.add(listDanhSach);
                br.setVisibility(View.INVISIBLE);
                Collections.reverse(cCHMC);
                adapterCuaHangRecycleView.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
