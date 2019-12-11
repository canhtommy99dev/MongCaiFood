package com.alexmedia.mongcaifood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexmedia.mongcaifood.Adapter.AdapterComment;
import com.alexmedia.mongcaifood.Adapter.AdapterCuaHangRecycleView;
import com.alexmedia.mongcaifood.Adapter.AdapterImageFull;
import com.alexmedia.mongcaifood.Model.ModelComment;
import com.alexmedia.mongcaifood.Model.ModelInfoCuaHang;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InfomationFoody extends AppCompatActivity {

    String id,tench1,diachi1,timeopen,sodt1,fb1,ship1,image1;
    TextView txtdiachi,txtTimer,txtSdt,txtfb,ship;
    ImageView imgInfo;
    Intent intent;
    DatabaseReference databaseAnhDoDuLieuImage,databaseComment;
    List<ModelInfoCuaHang> mc1;
    List<ModelComment> comments;
    AdapterImageFull infomation;
    AdapterComment adapterComment;
    Button btncomment;
    RecyclerView recyclerView,recyclerView1;
    public static final String ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_foody);
        intent = getIntent();
        databaseAnhDoDuLieuImage = FirebaseDatabase.getInstance().getReference("ImageAlbum").child(intent.getStringExtra(AdapterCuaHangRecycleView.ID));
        databaseComment = FirebaseDatabase.getInstance().getReference("CommentBaiViet").child(intent.getStringExtra(AdapterCuaHangRecycleView.ID));
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
        comments = new ArrayList<>();
        ///
        imgInfo = findViewById(R.id.iv_detail);
        txtdiachi = findViewById(R.id.txtaddress);
        txtdiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+diachi1);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        txtTimer = findViewById(R.id.txttimeopenclose);
        txtSdt = findViewById(R.id.txtphonenumber);
        txtfb = findViewById(R.id.txtFacebook);
        ship = findViewById(R.id.txtShipDoAn);
        ///
        id = intent.getStringExtra(AdapterCuaHangRecycleView.ID);
        tench1 = intent.getStringExtra(AdapterCuaHangRecycleView.TENCH);
        diachi1 = intent.getStringExtra(AdapterCuaHangRecycleView.ADDRESS);
        timeopen = intent.getStringExtra(AdapterCuaHangRecycleView.TIMEOPENEND);
        sodt1 = intent.getStringExtra(AdapterCuaHangRecycleView.SODIENTHOAI);
        fb1 = intent.getStringExtra(AdapterCuaHangRecycleView.FACEBOOK_CH);
        ship1 = intent.getStringExtra(AdapterCuaHangRecycleView.SHIPTINHTRANG);
        image1 = intent.getStringExtra(AdapterCuaHangRecycleView.IMAGE);
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
        btncomment = findViewById(R.id.btnComment);
        btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CommentActivity.class);
                intent.putExtra(ID,id);
                startActivity(intent);

            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        infomation = new AdapterImageFull(getApplicationContext(),mc1);
        recyclerView.setAdapter(infomation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterComment = new AdapterComment(InfomationFoody.this,comments);
        recyclerView1 = findViewById(R.id.lvcommon);
        recyclerView1.setAdapter(adapterComment);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseComment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comments.clear();
                for (DataSnapshot lc1x:dataSnapshot.getChildren()){
                    ModelComment modelComment = lc1x.getValue(ModelComment.class);
                    comments.add(modelComment);
                    adapterComment.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseAnhDoDuLieuImage.addValueEventListener(new ValueEventListener() {
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
        });
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

}
