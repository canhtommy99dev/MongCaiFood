package com.alexmedia.mongcaifood;

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
        setContentView(R.layout.activity_an_trua);
        btnQuayLai = findViewById(R.id.btnBac23);
        lvAnTrua = findViewById(R.id.lvAnTrua66);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtAnSang = findViewById(R.id.txtAnTrua);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/uvnsaigon.ttf");
        txtAnSang.setTypeface(typeface);
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
        intent = getIntent();
        lvAnTrua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListDanhSach listDanhSach = array1.get(position);
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
