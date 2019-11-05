package com.alexmedia.mongcaifood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexmedia.mongcaifood.ui.home.HomeFragment;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InfomationFoody extends AppCompatActivity {

    String tench1,diachi1,timeopen,sodt1,fb1,ship1,image1;
    TextView txtdiachi,txtTimer,txtSdt,txtfb,ship;
    ImageView imgInfo;
    Intent intent;
    DatabaseReference databaseAnhDoDuLieuImage;
    List<ModelInfoCuaHang> mc1;
    ListView recyclerView1;
    AdapterCuaHangInfomation infomation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_foody);
        intent = getIntent();
        databaseAnhDoDuLieuImage = FirebaseDatabase.getInstance().getReference("ImageAlbum").child(intent.getStringExtra(HomeFragment.ID));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mc1 = new ArrayList<>();
        ///
        imgInfo = findViewById(R.id.iv_detail);
        txtdiachi = findViewById(R.id.txtaddress);
        txtTimer = findViewById(R.id.txttimeopenclose);
        txtSdt = findViewById(R.id.txtphonenumber);
        txtfb = findViewById(R.id.txtFacebook);
        ship = findViewById(R.id.txtShipDoAn);
        ///

        tench1 = intent.getStringExtra(HomeFragment.TENCH);
        diachi1 = intent.getStringExtra(HomeFragment.ADDRESS);
        timeopen = intent.getStringExtra(HomeFragment.TIMEOPENEND);
        sodt1 = intent.getStringExtra(HomeFragment.SODIENTHOAI);
        fb1 = intent.getStringExtra(HomeFragment.FACEBOOK_CH);
        ship1 = intent.getStringExtra(HomeFragment.SHIPTINHTRANG);
        image1 = intent.getStringExtra(HomeFragment.IMAGE);
        ///
        Glide.with(this).load(image1).centerCrop().into(imgInfo);
        setTitle(tench1);
        txtdiachi.setText("Địa chỉ: "+diachi1);
        txtTimer.setText(timeopen);
        txtSdt.setText("SĐT: "+sodt1);
        txtfb.setText("Facebook: "+fb1);
        ship.setText("Ship: "+ship1);
        txtSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",sodt1,null));
                startActivity(intent);
            }
        });
        txtfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+fb1));
                startActivity(intent1);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        infomation = new AdapterCuaHangInfomation(this,mc1);
        recyclerView.setAdapter(infomation);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseAnhDoDuLieuImage.addValueEventListener(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            mc1.clear();
            for (DataSnapshot lsc1:dataSnapshot.getChildren()){
                ModelInfoCuaHang modelInfoCuaHang = lsc1.getValue(ModelInfoCuaHang.class);
                mc1.add(modelInfoCuaHang);
                infomation.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
