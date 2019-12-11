package com.alexmedia.mongcaifood;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexmedia.mongcaifood.Activity.AboutAccount;
import com.alexmedia.mongcaifood.Activity.Camerakit;
import com.alexmedia.mongcaifood.Activity.TimKiemActivity;
import com.alexmedia.mongcaifood.Activity.TkActivityApplication;
import com.alexmedia.mongcaifood.ui.home.HomeFragment;
import com.alexmedia.mongcaifood.ui.thongbao.ThongBaoFragment;
import com.alexmedia.mongcaifood.ui.ui.danhmuc.DanhMucFragment;
import com.alexmedia.mongcaifood.ui.ui.zalo.InstagramLoginandexit;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    ImageView btnQRCode,btnSeacrh;
    TextView fontc;
    CircleImageView imgAvater;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private static final String IMAGE = "Image";
    Intent intent;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/robotolight.ttf");
        if (checkNetwork()){

        }else if (!checkNetwork())
        {
            createDialog();
        }
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navView, navController);
        loadFragmemt(new HomeFragment());
        navView.setOnNavigationItemSelectedListener(this);
        imgAvater = findViewById(R.id.btn_avatar);
        imgAvater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutAccount.class));
            }
        });
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        intent = getIntent();
        btnQRCode = findViewById(R.id.camerakit);
        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Camerakit.class));
            }
        });
        btnSeacrh = findViewById(R.id.btnsearch);
        btnSeacrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TkActivityApplication.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_dashboard:
                fragment = new DanhMucFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new ThongBaoFragment();
                break;
            case R.id.account_zalo:
                fragment = new InstagramLoginandexit();
                break;
        }
        return loadFragmemt(fragment);
    }

    private boolean loadFragmemt(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit()
            ;
            return true;
        }
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult ggresult) {
                    handleSignInResult(ggresult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(imgAvater);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
            intent.putExtra(IMAGE,account.getPhotoUrl());

        }else{
            gotoMainActivity();
            finish();
        }
    }
    private void gotoMainActivity(){
        Intent intent=new Intent(this,LoginWithApp.class);
        startActivity(intent);
    }
    public void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogerror, null);
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
        Button btnExitConnect = alertLayout.findViewById(R.id.btnExit);
        btnExitConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        alert.setView(alertLayout);
        alert.setCancelable(false);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private boolean checkNetwork(){
        boolean wifiConnect = false;
        boolean mobileConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()){
            wifiConnect = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnect){
                wifiConnect = true;
            }else if (mobileConnected){
                mobileConnected = true;
            }
        }
        return wifiConnect||mobileConnected;
    }
}
