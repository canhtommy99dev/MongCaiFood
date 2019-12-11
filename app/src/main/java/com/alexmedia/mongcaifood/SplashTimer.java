package com.alexmedia.mongcaifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class SplashTimer extends AppCompatActivity {
    TextView tv1;
    Animation al,a2,a3,a4;
    ImageView imgIntro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_timer);
        al = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bouncein);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginWithApp.class));
                finish();
            }
        },5000);
        imgIntro = findViewById(R.id.imgIntro);
        imgIntro.startAnimation(al);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }
}
