package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    Intent intent;
    public static final String ID = "id";
    public static final String TENCH = "tench";
    public static final String ADDRESS = "diachi";
    public static final String TIMEOPENEND = "time";
    public static final String SODIENTHOAI = "thoigian";
    public static final String SHIPTINHTRANG = "tinhtrangship";
    public static final String FACEBOOK_CH = "facebook";
    public static final String DANHMUC = "tench";
    public static final String IMAGE = "image";

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
        intent = getIntent();
        lvDoUong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListDanhSach listDanhSach = array12.get(position);
                Intent intent = new Intent(getApplicationContext(), InfomationFoody.class);
                intent.putExtra(ID, listDanhSach.getId());
                intent.putExtra(TENCH, listDanhSach.getTench());
                intent.putExtra(ADDRESS, listDanhSach.getDiachi());
                intent.putExtra(TIMEOPENEND, listDanhSach.getThoigian());
                intent.putExtra(SODIENTHOAI, listDanhSach.getSodt());
                intent.putExtra(SHIPTINHTRANG, listDanhSach.getTinhtrangship());
                intent.putExtra(FACEBOOK_CH, listDanhSach.getFacebook());
                intent.putExtra(IMAGE, listDanhSach.getImage());
                startActivity(intent);
            }
        });

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
