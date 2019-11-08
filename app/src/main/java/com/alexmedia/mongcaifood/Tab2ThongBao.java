package com.alexmedia.mongcaifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alexmedia.mongcaifood.ui.tab2thongbao.Tab2ThongBaoFragment;

public class Tab2ThongBao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_thong_bao_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Tab2ThongBaoFragment.newInstance())
                    .commitNow();
        }
    }
}
