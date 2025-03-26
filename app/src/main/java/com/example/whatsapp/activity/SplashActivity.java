package com.example.whatsapp.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.NotificationReceiver;
import com.example.whatsapp.R;
import com.example.whatsapp.ads.AdManager;


public class SplashActivity extends AppCompatActivity {

    private AdManager adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize AdManager
        adManager = AdManager.getInstance(this);

        // Preload Ads
        FrameLayout bannerAdContainer = findViewById(R.id.banner_ad_container);
        FrameLayout nativeAdContainer = findViewById(R.id.native_ad_container);

        adManager.loadBannerAd(bannerAdContainer); // Load Banner Ad
        adManager.loadInterstitialAd(); // Load Interstitial Ad
        adManager.loadRewardedAd(); // Load Rewarded Ad
        adManager.loadNativeAd(this, "ca-app-pub-3940256099942544/2247696110", nativeAdContainer);
        adManager.loadAppOpenAd(); // Preload App Open Ad

        // Notification Scheduling
        EditText editTextMinutes = findViewById(R.id.editTextMinutes);
        Button buttonSetNotification = findViewById(R.id.buttonSetNotification);

        buttonSetNotification.setOnClickListener(v -> {
            String minutesInput = editTextMinutes.getText().toString();

            if (!minutesInput.isEmpty()) {
                try {
                    int minutes = Integer.parseInt(minutesInput);
                    if (minutes <= 0) {
                        Toast.makeText(this, "Please enter a positive number.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    scheduleNotification(this, minutes * 60 * 1000); // Convert minutes to milliseconds
                    Toast.makeText(this, "Notification set for " + minutes + " minute(s).", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


    private void scheduleNotification(Context context, long delayMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Check exact alarm permissions for Android 12 (API 31) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                Toast.makeText(this, "Permission required to schedule exact alarms.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(intent);
                return; // Exit to avoid proceeding without permissions
            }
        }

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Schedule the notification
        long triggerTime = System.currentTimeMillis() + delayMillis;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        adManager.destroyNativeAd();
    }

    public void button(View view) {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
