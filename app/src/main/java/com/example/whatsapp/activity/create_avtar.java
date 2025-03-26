package com.example.whatsapp.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public class create_avtar extends AppCompatActivity {
    private ImageView avatarImage;
    private RadioGroup rgAvatarOptions, rgBackgroundColor;
    private TextView btnEditAvatar;
    private LinearLayout linner;
    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_avtar);
        getWindow().setFlags(1024,1024);
        avatarImage = findViewById(R.id.avatarImage);
        linner = findViewById(R.id.linner);
        rgAvatarOptions = findViewById(R.id.rgAvatarOptions);
        rgBackgroundColor = findViewById(R.id.rgBackgroundColor);
        btnEditAvatar = findViewById(R.id.btnEditAvatar);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);

        rgAvatarOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbAvatar1) {
                    avatarImage.setImageResource(R.drawable.avtar1);
                } else if (checkedId == R.id.rbAvatar2) {
                    avatarImage.setImageResource(R.drawable.avtar2);
                }
                else if (checkedId == R.id.rbAvatar3) {
                    avatarImage.setImageResource(R.drawable.avtar3);
                }
                else if (checkedId == R.id.rbAvatar4) {
                    avatarImage.setImageResource(R.drawable.avtar4);
                }
                else if (checkedId == R.id.rbAvatar5) {
                    avatarImage.setImageResource(R.drawable.avtar5);
                }
                else if (checkedId == R.id.rbAvatar6) {
                    avatarImage.setImageResource(R.drawable.avtar6);
                }
                else if (checkedId == R.id.rbAvatar7) {
                    avatarImage.setImageResource(R.drawable.avtar7);
                }
                else if (checkedId == R.id.rbAvatar8) {
                    avatarImage.setImageResource(R.drawable.avtar8);
                }
                // Add more else if conditions as needed
            }
        });
//        for (int i = 0; i < rgAvatarOptions.getChildCount(); i++) {
//            if (rgAvatarOptions.getChildAt(i) instanceof RadioButton) {
//                RadioButton radioButton = (RadioButton) rgAvatarOptions.getChildAt(i);
//
//                // Apply the background tint list (color selector) to each RadioButton
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    radioButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.drawable.radio_button_sec));
//                }
//            }
//        }
        rgBackgroundColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbColor1) {
                    // Set a drawable as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background1));
                } else if (checkedId == R.id.rbColor2) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background2));
                }
                else if (checkedId == R.id.rbColor3) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background3));
                }
                else if (checkedId == R.id.rbColor4) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background4));
                }
                else if (checkedId == R.id.rbColor5) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background5));
                }
                else if (checkedId == R.id.rbColor6) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background6));
                }
                else if (checkedId == R.id.rbColor7) {
                    // Set a color as the background
                    linner.setBackground(ContextCompat.getDrawable(create_avtar.this, R.drawable.circle_background7));
                }
                // Add more else if conditions as needed for other colors or drawables
            }
        });

        btnEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit avatar action
            }
        });
    }

    public void onBackPressed(View view) {
        finish();
    }

    public void backavtar(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}