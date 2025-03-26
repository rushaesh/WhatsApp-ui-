package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class halecenter extends AppCompatActivity {

    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_halecenter);
        getWindow().setFlags(1024, 1024);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
    }

    public void getStarted(View view) {
        Intent intent = new Intent(this, getStarted.class);
        startActivity(intent);
    }

    public void Connect_with_Businesses(View view) {
        Intent intent = new Intent(this, Connect_with_Businesses.class);
        startActivity(intent);
    }

    public void Chats(View view) {
        Intent intent = new Intent(this, Chats_.class);
        startActivity(intent);
    }

    public void helpcenter(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void privacy_(View view) {
        Intent intent = new Intent(this, privacy_safty.class);
        startActivity(intent);
    }

    public void Communities(View view) {
        Intent intent = new Intent(this, Communities.class);
        startActivity(intent);

    }

    public void calls(View view) {
        Intent intent = new Intent(this, voicecalls.class);
        startActivity(intent);

    }

    public void privacy__(View view) {
        Intent intent = new Intent(this, privacy_safty.class);
        startActivity(intent);
    }
}