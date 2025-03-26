package com.example.whatsapp.activity;

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

public class Security_notifications extends AppCompatActivity {
    private AdManager adManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_security_notifications);
        getWindow().setFlags(1024,1024);
        adManager = AdManager.getInstance(this);
        FrameLayout nativeAdContainer = findViewById(R.id.native_ad_container);
        adManager.loadNativeAd(this, "ca-app-pub-3940256099942544/2247696110", nativeAdContainer);
    }

    public void backSecurity_notifications(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adManager.destroyNativeAd();
    }
}