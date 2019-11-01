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

public class AnVatActivty extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvAnVat;
    DatabaseReference databaseAnVat;
    AdapterAnVat adapterAnVat;
    ProgressBar progxuly5;
    List<ListDanhSach> array5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_vat_activty);
        btnQuayLai = findViewById(R.id.btnBac5);
        lvAnVat = findViewById(R.id.lvAnVat);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseAnVat = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly5 = findViewById(R.id.progLoadMa5);
        array5 = new ArrayList<>();
        adapterAnVat = new AdapterAnVat(AnVatActivty.this, array5);
        lvAnVat.setAdapter(adapterAnVat);
        databaseAnVat.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Ăn Vặt");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array5.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array5.add(listDanhSach1);
                adapterAnVat.notifyDataSetChanged();
                progxuly5.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
