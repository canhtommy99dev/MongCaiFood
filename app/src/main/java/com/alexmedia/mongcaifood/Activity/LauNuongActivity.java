package com.alexmedia.mongcaifood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexmedia.mongcaifood.Adapter.AdapterLauNuong;
import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
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
    TextView txtAnSang;

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
        txtAnSang = findViewById(R.id.txtLauNuong);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/robotothin.ttf");
        txtAnSang.setTypeface(typeface);
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
        intent = getIntent();
        lvLauNuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListDanhSach listDanhSach = array7.get(position);
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
