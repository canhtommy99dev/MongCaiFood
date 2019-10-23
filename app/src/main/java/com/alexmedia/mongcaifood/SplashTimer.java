package com.alexmedia.mongcaifood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class SplashTimer extends AppCompatActivity {
    TextView tv1;
    DigitalClock digitalClock;
    Calendar lich;
    Animation al,a2,a3,a4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_timer);
        al = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bouncein);
        tv1 = findViewById(R.id.txtLich);
        digitalClock = findViewById(R.id.simpleDigitalClock);
        lich = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(lich.getTime());
        tv1.setText(currentDate);
        tv1.startAnimation(al);
        digitalClock.startAnimation(al);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginWithApp.class));
                finish();
            }
        },5000);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }
}
