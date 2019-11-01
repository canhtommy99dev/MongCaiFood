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

public class DoUongActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvDoUong;
    DatabaseReference databaseAnToi;
    AdapterDoUong adapterDoUong;
    ProgressBar progxuly1;
    List<ListDanhSach> array12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_uong);
        btnQuayLai = findViewById(R.id.btnBac8);
        lvDoUong = findViewById(R.id.lvDouong);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseAnToi = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly1 = findViewById(R.id.progLoadMa8);
        array12 = new ArrayList<>();
        adapterDoUong = new AdapterDoUong(DoUongActivity.this, array12);
        lvDoUong.setAdapter(adapterDoUong);
        databaseAnToi.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Đồ Uống");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array12.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array12.add(listDanhSach1);
                adapterDoUong.notifyDataSetChanged();
                progxuly1.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
