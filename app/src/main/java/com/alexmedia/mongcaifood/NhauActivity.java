package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NhauActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvNhau;
    DatabaseReference databaseNhau;
    AdapterNhau adapterNhau;
    ProgressBar progxuly6;
    List<ListDanhSach> array6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhau);
        btnQuayLai = findViewById(R.id.btnBac6);
        lvNhau = findViewById(R.id.lvNhau);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseNhau = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly6 = findViewById(R.id.progLoadMa6);
        array6 = new ArrayList<>();
        adapterNhau = new AdapterNhau(NhauActivity.this, array6);
        lvNhau.setAdapter(adapterNhau);
        databaseNhau.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Nhậu");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array6.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array6.add(listDanhSach1);
                adapterNhau.notifyDataSetChanged();
                progxuly6.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
