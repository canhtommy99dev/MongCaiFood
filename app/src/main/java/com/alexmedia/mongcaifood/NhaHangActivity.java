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

public class NhaHangActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvNhaHang;
    DatabaseReference databaseNhaHang;
    AdapterNhaHang adapterNhaHang;
    ProgressBar progxuly4;
    List<ListDanhSach> array4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_hang);
        btnQuayLai = findViewById(R.id.btnBac4);
        lvNhaHang = findViewById(R.id.lvNhaHang);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseNhaHang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly4 = findViewById(R.id.progLoadMa4);
        array4 = new ArrayList<>();
        adapterNhaHang = new AdapterNhaHang(NhaHangActivity.this, array4);
        lvNhaHang.setAdapter(adapterNhaHang);
        databaseNhaHang.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Nhà Hàng");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array4.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array4.add(listDanhSach1);
                adapterNhaHang.notifyDataSetChanged();
                progxuly4.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
