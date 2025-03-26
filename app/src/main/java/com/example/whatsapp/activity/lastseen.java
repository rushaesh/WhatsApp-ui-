package com.example.whatsapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

public class lastseen extends AppCompatActivity {

    private AdManager adManager;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(1024,1024);
        setContentView(R.layout.activity_lastseen);
        TextView textView = findViewById(R.id.last_seen_);
        TextView textView1 = findViewById(R.id.last_seen_title);
        LinearLayout linearLayout = findViewById(R.id.linearLayout1 );


        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);



        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("title");
            String text1 = intent.getStringExtra("title1");

            if (text != null && !text.isEmpty()) {
                // If text is present, set it to TextView and hide the LinearLayout
                textView.setText(text);
                textView1.setText(text1);
                linearLayout.setVisibility(View.GONE);
            } else {
                // If text is absent, show the LinearLayout
                linearLayout.setVisibility(View.VISIBLE);
            }
        } else {
            // If intent is null, show the LinearLayout by default
            linearLayout.setVisibility(View.VISIBLE);
        }

        RadioGroup radioGroup = findViewById(R.id.last_seen_group);

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
        RadioGroup radioGroup1 = findViewById(R.id.online_status_group);

        // Iterate over each RadioButton in the RadioGroup
        for (int i = 0; i < radioGroup1.getChildCount(); i++) {
            if (radioGroup1.getChildAt(i) instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioGroup1.getChildAt(i);

                // Apply the button tint list (color selector) to each RadioButton
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    radioButton.setButtonTintList(ContextCompat.getColorStateList(this, R.drawable.radio_button_selector));
                }
            }
        }
    }

    public void backinlastseen(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}