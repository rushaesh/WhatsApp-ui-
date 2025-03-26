package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter;
import com.example.whatsapp.adapter.RecyclerViewAdapter4;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Set;

public class Newgroup extends AppCompatActivity {

    private ArrayList<CourseModel> recyclerDataArrayList1;
    RecyclerViewAdapter4 itemAdapter;
    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newgroup);
        adManager = AdManager.getInstance(this);
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        adManager.loadBannerAd(bannerAdContainer);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.idCourseRV);

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
        recyclerDataArrayList1.add(new CourseModel("aayushi vastaparea", R.drawable.people__1_, "Hey there! I am using WhatsApp", ""));
        recyclerDataArrayList1.add(new CourseModel("darshan vastapra", R.drawable.man__3_, "Darshan vastapra", ""));
        recyclerDataArrayList1.add(new CourseModel("Yug vastapra", R.drawable.man__2_, "Can't talk, WhatsApp", ""));
        itemAdapter = new RecyclerViewAdapter4(recyclerDataArrayList1);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);
    }

    public void grop(View view) {
        Set<Integer> selectedItems = itemAdapter.getSelectedItems();
        if (selectedItems.isEmpty()) {
            Toast.makeText(Newgroup.this, "At least 1 contact should be selected", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Newgroup.this, crategrop.class);
            startActivity(intent);
        }
    }
}