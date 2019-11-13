package com.alexmedia.mongcaifood.KhamPha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alexmedia.mongcaifood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class ChoMongCaiActivity extends AppCompatActivity {

    private WebView wv,wwyoutube;
    FloatingActionButton btnInfoCho;
    ImageView btnBack;
    ProgressBar ptobar,ptomc;


    String html = "<iframe src=\"https://www.google.com/maps/embed?pb=!4v1573199848678!6m8!1m7!1sCAoSLEFGMVFpcE1feHBuN3YyRzlfQkRjVmREQi1ZM0p5UVBjdUh0dU9pZFpfT2Fw!2m2!1d21.5308136!2d107.9682187!3f0.9981516173673413!4f0.35733156810410094!5f0.7820865974627469\" width=\"100%\" height=\"100%\" frameborder=\"0\" style=\"border:0;\" allowfullscreen=\"true\"></iframe>";
    String youtube = "<iframe width=\"300\" height=\"200\" src=\"https://www.youtube.com/embed/jKAA-JotlOA\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cho_mong_cai);
        ptobar = findViewById(R.id.prologapplication);
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
        alert.setTitle("Chợ Móng Cái Khám Phá");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        wwyoutube = alertLayout.findViewById(R.id.youtubePlayerView);
        ptomc = alertLayout.findViewById(R.id.probarandroid);
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

