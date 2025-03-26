package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Privacy extends AppCompatActivity {

    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_privacy);
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

    public void lastseen(View view) {
        Intent intent = new Intent(this, lastseen.class);
        startActivity(intent);
    }

    public void profilephoto(View view) {
        Intent intent = new Intent(this, lastseen.class);
        String s = "Profile photo";
        String s1 = "Who can see my Profile photo";
        intent.putExtra("title", s);
        intent.putExtra("title1", s1);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(this, lastseen.class);
        String s = "About";
        String s1 = "Who can see my About";
        intent.putExtra("title", s);
        intent.putExtra("title1", s1);
        startActivity(intent);
    }

    public void Advanced(View view) {
        Intent intent = new Intent(this, Advanced.class);
        startActivity(intent);
    }

    public void status(View view) {
        Intent intent = new Intent(this, status_privacy.class);
        startActivity(intent);
    }

    public void defulttimer(View view) {
        Intent intent = new Intent(this, defulttimer.class);
        startActivity(intent);

    }

    public void grops(View view) {
        Intent intent = new Intent(this, grops.class);
        startActivity(intent);

    }

    public void live_location(View view) {
        Intent intent = new Intent(this, live_location.class);
        startActivity(intent);
    }

    public void chat_lock(View view) {
        Intent intent = new Intent(this, Chat_lock.class);
        startActivity(intent);

    }
}