package com.example.whatsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter7;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel4;

import java.util.ArrayList;

public class Account extends AppCompatActivity {
    private AdManager adManager;
    private ArrayList<CourseModel4> recyclerDataArrayList1;
    FrameLayout nativeAdContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_account);

        // Initialize RecyclerView and AdManager
        RecyclerView recyclerView = findViewById(R.id.idCourseRV);
        adManager = AdManager.getInstance(this);
      // Load the native ad
        adManager.loadRewardedAd();
        nativeAdContainer = findViewById(R.id.native_ad_container);
        adManager.loadNativeAd(this, "ca-app-pub-3940256099942544/2247696110", nativeAdContainer);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
        // Prepare the data for RecyclerView
        recyclerDataArrayList1 = new ArrayList<>();
        recyclerDataArrayList1.add(new CourseModel4("Security notifications", R.drawable.security));
        recyclerDataArrayList1.add(new CourseModel4("Passkeys", R.drawable.passkey));
        recyclerDataArrayList1.add(new CourseModel4("Email address", R.drawable.email));
        recyclerDataArrayList1.add(new CourseModel4("Two-step verification", R.drawable.three_stars_hotel_sign_svgrepo_com));
        recyclerDataArrayList1.add(new CourseModel4("Change number", R.drawable.accont));
        recyclerDataArrayList1.add(new CourseModel4("Request account info", R.drawable.request));
        recyclerDataArrayList1.add(new CourseModel4("Add account", R.drawable.add_user_2_svgrepo_com));
        recyclerDataArrayList1.add(new CourseModel4("Delete account", R.drawable.deleteaccount));

        // Set up the RecyclerView with Adapter
        RecyclerViewAdapter7 itemAdapter = new RecyclerViewAdapter7(recyclerDataArrayList1, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);
    }



    public void onback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    protected void onStart() {
        super.onStart();
        // Optionally, load the ad again if needed when the activity starts
        // Ensure ad is reloaded if required (e.g., for new sessions)
        adManager.loadNativeAd(this, "ca-app-pub-3940256099942544/2247696110", nativeAdContainer);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        adManager.destroyNativeAd();
    }
}
