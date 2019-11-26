package com.alexmedia.mongcaifood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexmedia.mongcaifood.R;
import com.alexmedia.mongcaifood.ui.thongbao.ThongBaoFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChoMongCaiActivity extends AppCompatActivity {

    private WebView wv,wwyoutube;
    FloatingActionButton btnInfoCho;
    ImageView btnBack,imageClo;
    ProgressBar ptobar,ptomc;
    String html,youtube,baiviet,tendulich,image;
    Intent intent;
    TextView txtGioithieu;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cho_mong_cai);
        ptobar = findViewById(R.id.prologapplication);
        intent = getIntent();
        html = intent.getStringExtra(ThongBaoFragment.STREETVIEW);
        youtube = intent.getStringExtra(ThongBaoFragment.YOUTUBELINK);
        baiviet = intent.getStringExtra(ThongBaoFragment.BAIVIETGIOITHIEU);
        tendulich = intent.getStringExtra(ThongBaoFragment.TENDULICH);
        image = intent.getStringExtra(ThongBaoFragment.IMAGEDULICH);
        wv = (WebView)findViewById(R.id.webView1);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadDataWithBaseURL("", html , "text/html",  "UTF-8", "");
        wv.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && ptobar.getVisibility() == ProgressBar.GONE){
                    ptobar.setVisibility(ProgressBar.VISIBLE);
                }

                ptobar.setProgress(progress);
                if(progress == 100) {
                    ptobar.setVisibility(ProgressBar.GONE);
                }
            }
        });
        btnInfoCho = findViewById(R.id.fab);
        btnInfoCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        btnBack = findViewById(R.id.btnBack12322);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_chomongcai, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(tendulich);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        imageClo = alertLayout.findViewById(R.id.imageCeater);
        wwyoutube = alertLayout.findViewById(R.id.youtubePlayerView);
        ptomc = alertLayout.findViewById(R.id.probarandroid);
        txtGioithieu = alertLayout.findViewById(R.id.textGioithieu);
        txtGioithieu.setText(baiviet);
        Glide.with(this).load(image).centerCrop().into(imageClo);
        wwyoutube.getSettings().setJavaScriptEnabled(true);
        wwyoutube.loadDataWithBaseURL("",youtube,"text/html","UTF-8","");
        wwyoutube.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && ptomc.getVisibility() == ProgressBar.GONE){
                    ptomc.setVisibility(ProgressBar.VISIBLE);
                }

                ptobar.setProgress(progress);
                if(progress == 100) {
                    ptomc.setVisibility(ProgressBar.GONE);
                }
            }
        });
        alert.setPositiveButton("Đóng Lại", new DialogInterface.OnClickListener() {
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

