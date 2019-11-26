package com.alexmedia.mongcaifood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alexmedia.mongcaifood.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageFullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        PhotoView photoView = (PhotoView) findViewById(R.id.screenfullhd);
    }
}
