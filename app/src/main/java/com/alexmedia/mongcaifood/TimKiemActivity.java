package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    ImageView btnBack;
    SearchView searchView;
    ListView lvCuaHang;
    DatabaseReference dataBaiDang;
    AdapterTimKiemCuaHang adapterTimKiemCuaHang;
    ProgressBar progBar;
    List<ListDanhSach> listDanhSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        btnBack = findViewById(R.id.btnBacl2);
        searchView = findViewById(R.id.tkm);
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
        dataBaiDang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDanhSach.clear();
                for (DataSnapshot mc:dataSnapshot.getChildren()){
                    ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                    listDanhSach.add(listDanhSach1);
                    adapterTimKiemCuaHang = new AdapterTimKiemCuaHang(TimKiemActivity.this,listDanhSach);
                    lvCuaHang.setAdapter(adapterTimKiemCuaHang);
                    adapterTimKiemCuaHang.notifyDataSetChanged();
                    progBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
