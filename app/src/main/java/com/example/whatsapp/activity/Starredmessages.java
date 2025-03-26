package com.example.whatsapp.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Starredmessages extends AppCompatActivity {

    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_starredmessages);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);

    }
}