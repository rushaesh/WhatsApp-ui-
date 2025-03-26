package com.example.whatsapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter6;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private ArrayList<CourseModel> recyclerDataArrayList1;
    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";
    AdManager adManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.idCourseRV);
        adManager = AdManager.getInstance(this);
        adManager.loadInterstitialAd();
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
        recyclerDataArrayList1 = new ArrayList<CourseModel>();

        // added data to arra   y list
        recyclerDataArrayList1.add(new CourseModel("Account ", R.drawable.accont, "Security notifications,change number", ""));
        recyclerDataArrayList1.add(new CourseModel("Privacy ", R.drawable.privacy, "Block contacts,disappearing messages", ""));
        recyclerDataArrayList1.add(new CourseModel("Avatar ", R.drawable.avatar, "Create,edit,profile photo", ""));
        recyclerDataArrayList1.add(new CourseModel("Favourites ", R.drawable.favourites, "Add,reorder,remove", ""));
        recyclerDataArrayList1.add(new CourseModel("Chats ", R.drawable.message_4654, "Theme,wallpapers,chat history", ""));
        recyclerDataArrayList1.add(new CourseModel("Notifications ", R.drawable.notifications, "message,group & call tones", ""));
        recyclerDataArrayList1.add(new CourseModel("Storage and data ", R.drawable.help, "Network usage,auto-download", ""));
        recyclerDataArrayList1.add(new CourseModel("App language ", R.drawable.app_language, "English (device's language)", ""));
        recyclerDataArrayList1.add(new CourseModel("Help ", R.drawable.help, "Help centre,contact us,privacy policy", ""));
        RecyclerViewAdapter6 itemAdapter = new RecyclerViewAdapter6(recyclerDataArrayList1, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

    }

    public void update(View view) {
        startActivity(new android.content.Intent(this, Update.class));

    }

    public void insta(View view) {
        openInstagram();
    }

    private void openInstagram() {
        Intent likeIng = new Intent(Intent.ACTION_VIEW);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=instagram&c=apps&hl=en-IN")));
        }

    }

    public void facebook(View view) {
        startActivity(getOpenFacebookIntent());
    }
    public Intent getOpenFacebookIntent() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/42623597411506"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/appetizerandroid"));
        }
    }

    public void back5(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
