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

public class AnToiActivity extends AppCompatActivity {

    ImageView btnQuayLai;
    ListView lvAnTrua;
    DatabaseReference databaseAnToi;
    AdapterAnToi adapterAnToi;
    ProgressBar progxuly1;
    List<ListDanhSach> array12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_toi);
        btnQuayLai = findViewById(R.id.btnBac233);
        lvAnTrua = findViewById(R.id.lvAnTrua661);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseAnToi = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progxuly1 = findViewById(R.id.progLoadMa661);
        array12 = new ArrayList<>();
        adapterAnToi = new AdapterAnToi(AnToiActivity.this, array12);
        lvAnTrua.setAdapter(adapterAnToi);
        databaseAnToi.addListenerForSingleValueEvent(valueEventListener);
        Query query1 = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang")
                .orderByChild("danhmuc")
                .equalTo("Ăn Tối");
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            array12.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                array12.add(listDanhSach1);
                adapterAnToi.notifyDataSetChanged();
                progxuly1.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
