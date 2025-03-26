package com.example.whatsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;

public class chat extends AppCompatActivity {
    TextView textView, textview2, me, text, textView3, textView4;
    ImageView imageView;
    int imagevalue;
    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setFlags(1024, 1024);
        text = (TextView) findViewById(R.id.text);
        textView3 = (TextView) findViewById(R.id.text3);
        imageView = (ImageView) findViewById(R.id.img1);
        Bundle bundle = getIntent().getExtras();
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);

        //        Intent intent = getIntent();
        //        String s1 = getIntent().getStringExtra("message1");
        String s2 = getIntent().getStringExtra("mess2");
        String s3 = getIntent().getStringExtra("mess3");
        text.setText(s2);
        textView3.setText(s3);
        //        massage.setText(s1);
        if (bundle != null) {

            imagevalue = bundle.getInt("image");

        }
        imageView.setImageResource(imagevalue);
    }

    public void back2(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}