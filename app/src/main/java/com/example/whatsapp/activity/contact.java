package com.example.whatsapp.activity;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class contact extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private ArrayList<CourseModel> recyclerDataArrayList1;
    private AdManager adManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.idCourseRV);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);
        recyclerDataArrayList1 = new ArrayList<CourseModel>();

        // added data to array list
        recyclerDataArrayList1.add(new CourseModel("Aesha (You)", R.drawable.people, "Message yourself", ""));
        recyclerDataArrayList1.add(new CourseModel("aayushi vastaparea", R.drawable.people__1_, "Hey there! I am using WhatsApp", ""));
        recyclerDataArrayList1.add(new CourseModel("darshan vastapra", R.drawable.man__3_, "Darshan vastapra", ""));
        recyclerDataArrayList1.add(new CourseModel("Yug vastapra", R.drawable.man__2_, "Can't talk, WhatsApp", ""));
        recyclerDataArrayList1.add(new CourseModel("aarti vastapara", R.drawable.people, "Hey there! I am using WhatsApp", ""));
        recyclerDataArrayList1.add(new CourseModel("bansi vastapra", R.drawable.g1, "Available", ""));
        recyclerDataArrayList1.add(new CourseModel("meet vastapra", R.drawable.bo1, "Always be happy", ""));
        recyclerDataArrayList1.add(new CourseModel("bhuri 29.12", R.drawable.g3, "Be happy", ""));
        recyclerDataArrayList1.add(new CourseModel("tanisha vastapara", R.drawable.g2, "Har Har Mahadev", ""));
        recyclerDataArrayList1.add(new CourseModel("Nad vastapara", R.drawable.man__4_, "Thank you god.....", ""));
        recyclerDataArrayList1.add(new CourseModel("Rohit vastapara", R.drawable.man, "Selenophile", ""));
        RecyclerViewAdapter itemAdapter = new RecyclerViewAdapter(recyclerDataArrayList1,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

    }
}