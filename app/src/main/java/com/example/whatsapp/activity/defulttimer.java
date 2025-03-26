package com.example.whatsapp.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class defulttimer extends AppCompatActivity {

    private AdManager adManager;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_defulttimer);
        getWindow().setFlags(1024,1024);
        RadioGroup radioGroup = findViewById(R.id.last_seen_group);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
        // Iterate over each RadioButton in the RadioGroup
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (radioGroup.getChildAt(i) instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

                // Apply the button tint list (color selector) to each RadioButton
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    radioButton.setButtonTintList(ContextCompat.getColorStateList(this, R.drawable.radio_button_selector));
                }
            }
        }
    }

    public void backindefulttimer(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}