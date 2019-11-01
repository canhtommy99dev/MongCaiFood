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

public class LauNuongActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvLauNuong;
    DatabaseReference databaseLauNuonh;
    AdapterLauNuong adapterAnToi;
    ProgressBar progxuly7;
    List<ListDanhSach> array7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lau_nuong);
        btnQuayLai = findViewById(R.id.btnBac7);
        lvLauNuong = findViewById(R.id.lvLauNuong);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseLauNuonh = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly7 = findViewById(R.id.progLoadMa7);
        array7 = new ArrayList<>();
        adapterAnToi = new AdapterLauNuong(LauNuongActivity.this, array7);
        lvLauNuong.setAdapter(adapterAnToi);
        databaseLauNuonh.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Lẩu Nướng");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array7.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array7.add(listDanhSach1);
                adapterAnToi.notifyDataSetChanged();
                progxuly7.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
