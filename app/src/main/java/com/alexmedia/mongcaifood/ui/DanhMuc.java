package com.alexmedia.mongcaifood.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alexmedia.mongcaifood.R;
import com.alexmedia.mongcaifood.ui.ui.danhmuc.DanhMucFragment;

public class DanhMuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_muc_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DanhMucFragment.newInstance())
                    .commitNow();
        }
    }
}
