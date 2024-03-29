package com.alexmedia.mongcaifood.Activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexmedia.mongcaifood.Adapter.AdapterMap;
import com.alexmedia.mongcaifood.Model.ListDanhSach;
import com.alexmedia.mongcaifood.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapChiDuongMC extends FragmentActivity implements OnMapReadyCallback, LocationListener,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    ImageView imgBack,imgHDSD;
    ChildEventListener mChildevent;
    DatabaseReference daQuanLy;
    Marker marker;
    TextView txtBanDo;
    Animation floatin;
    Location location;
    private final static int MY_PERMISSIONS_FINE_LOCATION = 101;
//    SearchView searchView;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chi_duong_mc);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        txtBanDo = findViewById(R.id.txtBanDo);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/robotothin.ttf");
        txtBanDo.setTypeface(typeface);

        imgBack = findViewById(R.id.imgBack2);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        daQuanLy = FirebaseDatabase.getInstance().getReference("CuaHang/DanhSachCuaHang");
        daQuanLy.push().setValue(marker);
        mapFragment.getMapAsync(this);
        imgHDSD = findViewById(R.id.hdsd);
        imgHDSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnMarkerClickListener(this);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        daQuanLy.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dm:dataSnapshot.getChildren()){
                    ListDanhSach listDanhSach = dm.getValue(ListDanhSach.class);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(listDanhSach.getLatitude(),listDanhSach.getLongitude()))
                            .title(listDanhSach.getTench())
                            .snippet(listDanhSach.getDiachi())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    AdapterMap adapterMap = new AdapterMap(MapChiDuongMC.this);
                    mMap.setInfoWindowAdapter(adapterMap);
                    mMap.addMarker(markerOptions);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        searchView = findViewById(R.id.TFaddress);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                String location = searchView.getQuery().toString();
//                List<Address> addressList = null;
//                if (location != null || !location.equals("")){
//                    Geocoder geocoder = new Geocoder(MapChiDuongMC.this);
//                    try {
//                        addressList = geocoder.getFromLocationName(location,1);
//                    }catch (IOException ex){
//                        ex.printStackTrace();
//                    }
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            // int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_FINE_LOCATION);
            }
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Lions, tigers, and bears, oh my!", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
    public void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogsudung, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hướng dẫn sử dụng chỉ đường");
        alert.setView(alertLayout);
        alert.setCancelable(false);

        alert.setPositiveButton("Oke", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
