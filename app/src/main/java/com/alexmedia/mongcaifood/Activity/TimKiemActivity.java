package com.alexmedia.mongcaifood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.alexmedia.mongcaifood.Adapter.AdapterTimKiemCuaHang;
import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    ImageView btnBack,clickSearch;
    ListView lvCuaHang;
    DatabaseReference dataBaiDang;
    AdapterTimKiemCuaHang adapterTimKiemCuaHang;
    ProgressBar progBar;
    List<ListDanhSach> listDanhSach;
    SearchView searchViewTK;
    TextView view1;
    EditText timCK;
    String tenchtimkiem;
    public static final String ID = "id";
    public static final String TENCH = "tench";
    public static final String ADDRESS = "diachi";
    public static final String TIMEOPENEND = "time";
    public static final String SODIENTHOAI = "thoigian";
    public static final String SHIPTINHTRANG = "tinhtrangship";
    public static final String FACEBOOK_CH = "facebook";
    public static final String IMAGE = "image";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tk_application);
        btnBack = findViewById(R.id.btnBacl2);
        searchViewTK = findViewById(R.id.simpleSearchView);
        lvCuaHang = findViewById(R.id.lvTimKiem);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBaiDang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        progBar = findViewById(R.id.progLoadMa);
        listDanhSach = new ArrayList<>();
        adapterTimKiemCuaHang = new AdapterTimKiemCuaHang(TimKiemActivity.this,listDanhSach);
        lvCuaHang.setAdapter(adapterTimKiemCuaHang);
        dataBaiDang.addListenerForSingleValueEvent(valueEventListener);
        intent = getIntent();
        view1 = findViewById(R.id.cmm);
        tenchtimkiem = intent.getStringExtra(Camerakit.TENCH);
        lvCuaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListDanhSach listDanhSach1 = listDanhSach.get(position);
                Intent intent = new Intent(getApplicationContext(), InfomationFoody.class);
                String transitionName = getString(R.string.transition);

                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(TimKiemActivity.this,
                                view,   // Starting view
                                transitionName    // The String
                        );
                intent.putExtra(ID, listDanhSach1.getId());
                intent.putExtra(TENCH, listDanhSach1.getTench());
                intent.putExtra(ADDRESS, listDanhSach1.getDiachi());
                intent.putExtra(TIMEOPENEND, listDanhSach1.getThoigian());
                intent.putExtra(SODIENTHOAI, listDanhSach1.getSodt());
                intent.putExtra(SHIPTINHTRANG, listDanhSach1.getTinhtrangship());
                intent.putExtra(FACEBOOK_CH, listDanhSach1.getFacebook());
                intent.putExtra(IMAGE, listDanhSach1.getImage());
                ActivityCompat.startActivity(TimKiemActivity.this,intent,options.toBundle());
            }
        });
        view1.setText(tenchtimkiem);
        adapterTimKiemCuaHang.getFilter().filter(tenchtimkiem);
        searchViewTK.clearFocus();
        searchViewTK.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTimKiemCuaHang.getFilter().filter(newText);
                return true;
            }
        });
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            listDanhSach.clear();
            for (DataSnapshot mc:dataSnapshot.getChildren()){
                ListDanhSach listDanhSach1 = mc.getValue(ListDanhSach.class);
                listDanhSach.add(listDanhSach1);
                adapterTimKiemCuaHang.notifyDataSetChanged();
                progBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
