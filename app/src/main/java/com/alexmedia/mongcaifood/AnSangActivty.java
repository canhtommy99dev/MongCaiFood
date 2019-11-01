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

public class AnSangActivty extends AppCompatActivity {

    ImageView btnBack1;
    ListView lvAnSang;
    DatabaseReference databaseAnSang;
    AdapterAnSang adapterAnSang;
    ProgressBar prog1;
    List<ListDanhSach> listDanhSaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_sang_activty);
        btnBack1 = findViewById(R.id.btnBacl23);
        lvAnSang = findViewById(R.id.lvAnSang122);
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseAnSang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        prog1 = findViewById(R.id.progLoadMa144);
        listDanhSaches = new ArrayList<>();
        adapterAnSang = new AdapterAnSang(AnSangActivty.this, listDanhSaches);
        lvAnSang.setAdapter(adapterAnSang);
        databaseAnSang.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Ăn Sáng");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            listDanhSaches.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                ListDanhSach lc1 = snapshot.getValue(ListDanhSach.class);
                listDanhSaches.add(lc1);
                adapterAnSang.notifyDataSetChanged();
                prog1.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
