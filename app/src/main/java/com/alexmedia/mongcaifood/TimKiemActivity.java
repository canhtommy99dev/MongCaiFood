package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    ImageView btnBack,clickSearch;
    ListView lvCuaHang;
    DatabaseReference dataBaiDang;
    AdapterTimKiemCuaHang adapterTimKiemCuaHang;
    ProgressBar progBar;
    List<ListDanhSach> listDanhSach;
    EditText timCK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        btnBack = findViewById(R.id.btnBacl2);

        lvCuaHang = findViewById(R.id.lvTimKiem);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBaiDang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progBar = findViewById(R.id.progLoadMa);
        listDanhSach = new ArrayList<>();
        adapterTimKiemCuaHang = new AdapterTimKiemCuaHang(TimKiemActivity.this,listDanhSach);
        lvCuaHang.setAdapter(adapterTimKiemCuaHang);
        dataBaiDang.addListenerForSingleValueEvent(valueEventListener);
//        Query  query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
//                .orderByChild("danhmuc")
//                .equalTo("Ăn Sáng");
//        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            listDanhSach.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                listDanhSach.add(listDanhSach1);
                adapterTimKiemCuaHang.notifyDataSetChanged();
                progBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
