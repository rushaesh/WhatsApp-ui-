package com.example.whatsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;

public class Advanced extends AppCompatActivity {
        private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced);
        getWindow().setFlags(1024, 1024);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
    }

    public void onback4(View view) {
        onBackPressed();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}