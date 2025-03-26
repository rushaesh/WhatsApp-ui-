package com.example.whatsapp.activity;

import static com.example.whatsapp.adapter.DummyDataGenerator.generateStatuses;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.devlomi.circularstatusview.CircularStatusView;
import com.example.whatsapp.R;
import com.example.whatsapp.adapter.Adapterviewpager;
import com.example.whatsapp.adapter.StatusAdapter;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.fragment.callsFragment;
import com.example.whatsapp.fragment.chatrFragment;
import com.example.whatsapp.fragment.communityFragment;
import com.example.whatsapp.fragment.updatedFragment;
import com.example.whatsapp.modalclass.Status;
import com.example.whatsapp.modalclass.UserStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    BottomNavigationView bottomNavigationView;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    ViewPager2 viewPager2;
    ArrayList<Fragment> Fragmentsarraylist = new ArrayList<>();
    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(1024, 1024);
        FrameLayout bannerAdContainer = findViewById(R.id.bannerAdContainer);


        adManager = AdManager.getInstance(this);

        // Load and display banner ad
        adManager.loadBannerAd(bannerAdContainer);

        // Load other ads

        // Initialize Mobile Ads SDK

        RecyclerView recyclerView = findViewById(R.id.rv);
        final List<UserStatus> userStatusList = generateStatuses();
        final StatusAdapter adapter = new StatusAdapter(userStatusList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnStatusClickListener(new StatusAdapter.OnStatusClickListener() {
            @Override
            public void onStatusClick(CircularStatusView circularStatusView, int pos) {
                UserStatus userStatus = userStatusList.get(pos);
                if (!userStatus.areAllSeen()) {
                    for (int i = 0; i < userStatus.getStatusList().size(); i++) {
                        Status status = userStatus.getStatusList().get(i);
                        if (!status.isSeen()) {
                            //update view
                            circularStatusView.setPortionColorForIndex(i, Color.GRAY);
                            //update adapter to prevent changes when scrolling
                            status.setSeen(true);
                            break;
                        }
                    }
                }
            }
        });

        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager2 = findViewById(R.id.flFragment);
        Fragmentsarraylist.add(new chatrFragment());
        Fragmentsarraylist.add(new updatedFragment());
        Fragmentsarraylist.add(new communityFragment());
        Fragmentsarraylist.add(new callsFragment());
        Adapterviewpager adapterviewpager = new Adapterviewpager(this, Fragmentsarraylist);
        viewPager2.setAdapter(adapterviewpager);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.Chat);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.Updates);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.Communities);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.Calls);
                        break;

                }

                super.onPageSelected(position);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.Chat) {
                    viewPager2.setCurrentItem(0);
                } else if (itemId == R.id.Updates) {
                    viewPager2.setCurrentItem(1);
                } else if (itemId == R.id.Communities) {
                    viewPager2.setCurrentItem(2);
                } else if (itemId == R.id.Calls) {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            setupCamera();
        }

//
        //    bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        bottomNavigationView
//                .setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.Chat);
    }

    private void startZoomAnimation(View view) {
        // Scale up (zoom in)
        ObjectAnimator scaleXIn = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f);
        ObjectAnimator scaleYIn = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);

        // Scale down (zoom out)
        ObjectAnimator scaleXOut = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f);
        ObjectAnimator scaleYOut = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f);

        // Set the duration for each animation
        scaleXIn.setDuration(1000);
        scaleYIn.setDuration(1000);
        scaleXOut.setDuration(1000);
        scaleYOut.setDuration(1000);

        // Play the zoom-in and zoom-out animations sequentially
        AnimatorSet zoomInSet = new AnimatorSet();
        zoomInSet.playTogether(scaleXIn, scaleYIn);

        AnimatorSet zoomOutSet = new AnimatorSet();
        zoomOutSet.playTogether(scaleXOut, scaleYOut);

        AnimatorSet zoomAnimatorSet = new AnimatorSet();
        zoomAnimatorSet.playSequentially(zoomInSet, zoomOutSet);

        zoomAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Restart the animation
                zoomAnimatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Do nothing
            }
        });

        // Start the animation
        zoomAnimatorSet.start();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupCamera();
            } else {
                // Permission denied, handle as needed
            }
        }
    }

    private void setupCamera() {
        // Initialize camera and set up preview
    }

//    public boolean
//    onNavigationItemSelected(@NonNull MenuItem item) {
//
//        Fragment selectedFragment = null;
//        int itemId = item.getItemId();
//        if (itemId == R.id.Updates) {
//            selectedFragment = new updatedFragment();
//        } else if (itemId == R.id.Calls) {
//            selectedFragment = new callsFragment();
//        } else if (itemId == R.id.Communities) {
//            selectedFragment = new communityFragment();
//        } else if (itemId == R.id.Chat) {
//            selectedFragment = new chatrFragment();
//        }
//
//        if (selectedFragment != null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
//        }
//        return true;
//
//    }


//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }

}
