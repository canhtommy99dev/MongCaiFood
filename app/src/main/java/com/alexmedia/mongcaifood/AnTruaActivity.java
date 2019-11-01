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

public class AnTruaActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvAnTrua;
    DatabaseReference databaseAnTrua;
    AdapterAntrua adapterAnTrua;
    ProgressBar progxuly;
    List<ListDanhSach> array1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_trua);
        btnQuayLai = findViewById(R.id.btnBac23);
        lvAnTrua = findViewById(R.id.lvAnTrua66);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseAnTrua = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly = findViewById(R.id.progLoadMa66);
        array1 = new ArrayList<>();
        adapterAnTrua = new AdapterAntrua(AnTruaActivity.this, array1);
        lvAnTrua.setAdapter(adapterAnTrua);
        databaseAnTrua.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Ăn Trưa");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array1.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array1.add(listDanhSach1);
                adapterAnTrua.notifyDataSetChanged();
                progxuly.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
