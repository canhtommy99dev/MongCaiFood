package com.alexmedia.mongcaifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alexmedia.mongcaifood.ui.tab1khampha.Tab1KhamPhaFragment;

public class Tab1KhamPha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_kham_pha_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Tab1KhamPhaFragment.newInstance())
                    .commitNow();
        }
    }
}
