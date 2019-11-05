package com.alexmedia.mongcaifood.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexmedia.mongcaifood.AboutAccount;
import com.alexmedia.mongcaifood.AdapterCuaHang;
import com.alexmedia.mongcaifood.InfomationFoody;
import com.alexmedia.mongcaifood.ListDanhSach;
import com.alexmedia.mongcaifood.LoginWithApp;
import com.alexmedia.mongcaifood.MainActivity;
import com.alexmedia.mongcaifood.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    FlipperLayout fli;
    CircleImageView img;
    GoogleApiClient client;
    GoogleSignInOptions options;
    DatabaseReference docuahang;
    List<ListDanhSach> cCHMC;
    ListView lvChl;
    AdapterCuaHang cuaHang;
    ProgressBar br;
    Context context;
    Intent intent;
    String image;
    public static final String ID = "id";
    public static final String TENCH = "tench";
    public static final String ADDRESS = "diachi";
    public static final String TIMEOPENEND = "time";
    public static final String SODIENTHOAI = "thoigian";
    public static final String SHIPTINHTRANG = "tinhtrangship";
    public static final String FACEBOOK_CH = "facebook";
    public static final String IMAGE = "image";
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        fli =view.findViewById(R.id.fli);
        getActivity().setTitle("Trang Chủ");
        String url[] =new  String[]{
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag1.jpg?alt=media&token=612841dc-63a5-4cc2-928d-362045b8d4fb",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag2.jpg?alt=media&token=d6cfa81f-a199-40b6-933d-3ecfdfcce0cd",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag3.jpg?alt=media&token=0279deda-2491-471c-af91-cd5a140b5884",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag4.jpg?alt=media&token=ce9a12c7-e344-4439-b067-aa29e6c7f629",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag5.jpg?alt=media&token=5e14d856-3cdd-4f19-891a-15bf3d13d3c8",
                "https://firebasestorage.googleapis.com/v0/b/amthucmongcai.appspot.com/o/imag6.jpg?alt=media&token=06c62604-d739-41fa-a074-ec093c4bce5d"
        };
        intent = getActivity().getIntent();
        int num_of_pages = 6;
        for (int i = 0; i < num_of_pages; i++) {
            FlipperView v = new FlipperView(getContext());
            v.setImageUrl(url[i])
                    .setImageScaleType(ImageView.ScaleType.FIT_XY) //You can use any ScaleType
                    .setDescription("")
                    .setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                        @Override
                        public void onFlipperClick(FlipperView flipperView) {

                        }
                    });
            fli.setScrollTimeInSec(6); //setting up scroll time, by default it's 3 seconds
            fli.getScrollTimeInSec(); //returns the scroll time toi sec
            //returns the current position of pager
            fli.addFlipperView(v);
            this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }
        br = view.findViewById(R.id.pro111);
        lvChl = view.findViewById(R.id.lvDachSachCH);
        cCHMC = new ArrayList<>();
        docuahang = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        docuahang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cCHMC.clear();
                for (DataSnapshot list:dataSnapshot.getChildren()){
                    ListDanhSach listDanhSach = list.getValue(ListDanhSach.class);
                    cCHMC.add(listDanhSach);
                    cuaHang = new AdapterCuaHang(getContext(),R.layout.adaptercuahang,cCHMC);
                    lvChl.setAdapter(cuaHang);
                    cuaHang.notifyDataSetChanged();
                    br.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lvChl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListDanhSach listDanhSach = cCHMC.get(position);
                Intent intent = new Intent(getActivity(), InfomationFoody.class);
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
        return view;
    }
}
