package com.example.whatsapp.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.whatsapp.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdManager {

    private static AdManager instance;

    private InterstitialAd interstitialAd;
    private RewardedAd rewardedAd;
    private AppOpenAd appOpenAd;
    private NativeAd nativeAd;
    private Context context;

    private AdManager(Context context) {
        this.context = context;
    }

    // Singleton method to get the instance of AdManager
    public static AdManager getInstance(Context context) {
        if (instance == null) {
            instance = new AdManager(context);
        }
        return instance;
    }

    // ---------------------- INTERSTITIAL AD ---------------------- //
    public void loadInterstitialAd() {
        InterstitialAd.load((Activity) context, "ca-app-pub-3940256099942544/1033173712", // Replace with your ad unit ID
                new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                        Log.d("AdManager", "Interstitial Ad Loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        Log.e("AdManager", "Interstitial Ad Failed to Load: " + error.getMessage());
                    }
                });
    }

    public void showInterstitialAd(Activity activity, AdListener1 adListener) {
        if (interstitialAd != null) {
            interstitialAd.show(activity);

            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("AdManager", "Interstitial ad dismissed.");
                    interstitialAd = null; // Invalidate the ad
                    loadInterstitialAd(); // Reload a new ad
                    if (adListener != null) adListener.onAdClosed();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("AdManager", "Interstitial ad failed to show: " + adError.getMessage());
                    interstitialAd = null; // Invalidate the ad
                    loadInterstitialAd(); // Reload a new ad
                    if (adListener != null) adListener.onAdFailedToShow();
                }
            });
        } else {
            Log.e("AdManager", "Interstitial Ad not ready.");
            if (adListener != null) adListener.onAdFailedToShow();
        }
    }

    // AdListener Interface
    public interface AdListener1 {
        void onAdClosed();
        void onAdFailedToShow();
    }

    // ---------------------- REWARDED AD ---------------------- //
    public void loadRewardedAd() {
        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917", // Replace with your ad unit ID
                new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        Log.d("AdManager", "Rewarded Ad Loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        Log.e("AdManager", "Rewarded Ad Failed to Load: " + error.getMessage());
                    }
                });
    }

    public void showRewardedAd(Activity activity, AdListener1 adListener) {
        if (rewardedAd != null) {
            rewardedAd.show(activity, rewardItem -> {
                Log.d("AdManager", "User rewarded with: " + rewardItem.getAmount() + " " + rewardItem.getType());
            });

            rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("AdManager", "Rewarded ad dismissed.");
                    rewardedAd = null; // Invalidate the ad
                    loadRewardedAd(); // Reload a new ad
                    if (adListener != null) adListener.onAdClosed();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e("AdManager", "Rewarded ad failed to show: " + adError.getMessage());
                    rewardedAd = null; // Invalidate the ad
                    loadRewardedAd(); // Reload a new ad
                    if (adListener != null) adListener.onAdFailedToShow();
                }
            });
        } else {
            Log.e("AdManager", "Rewarded Ad not ready.");
            if (adListener != null) adListener.onAdFailedToShow();
        }
    }


    // ---------------------- APP OPEN AD ---------------------- //
    public void loadAppOpenAd() {
        AppOpenAd.load(context, "ca-app-pub-3940256099942544/3419835294", // Replace with your ad unit ID
                new AdRequest.Builder().build(), AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AppOpenAd ad) {
                        appOpenAd = ad;
                        Log.d("AdManager", "App Open Ad Loaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        Log.e("AdManager", "App Open Ad Failed to Load: " + error.getMessage());
                    }
                });
    }

    public void showAppOpenAd(Activity activity) {
        if (appOpenAd != null) {
            appOpenAd.show(activity);
        } else {
            Log.e("AdManager", "App Open Ad not ready");
        }
    }

    // ---------------------- NATIVE AD ---------------------- //


    public void loadNativeAd(Context context, String adUnitId, FrameLayout adContainer) {
        AdLoader adLoader = new AdLoader.Builder(context, adUnitId)
                .forNativeAd(ad -> {
                    if (nativeAd != null) {
                        nativeAd.destroy();
                    }
                    nativeAd = ad;

                    NativeAdView adView = (NativeAdView) LayoutInflater.from(context)
                            .inflate(R.layout.native_ad_layout, null);
                    populateNativeAdView(nativeAd, adView);
                    adContainer.removeAllViews();
                    adContainer.addView(adView);
                })
                .withAdListener(new com.google.android.gms.ads.AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the error
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Bind the ad's headline.
        TextView headlineView = adView.findViewById(R.id.ad_headline);
        headlineView.setText(nativeAd.getHeadline());
        adView.setHeadlineView(headlineView);

        // Bind the media view.
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        mediaView.setMediaContent(nativeAd.getMediaContent());
        adView.setMediaView(mediaView);

        // Bind the ad's body if available.
        if (nativeAd.getBody() != null) {
            TextView bodyView = adView.findViewById(R.id.ad_body);
            bodyView.setText(nativeAd.getBody());
            bodyView.setVisibility(View.VISIBLE);
            adView.setBodyView(bodyView);
        } else {
            adView.findViewById(R.id.ad_body).setVisibility(View.GONE);
        }

        // Bind the call-to-action button if available.
        if (nativeAd.getCallToAction() != null) {
            Button callToActionView = adView.findViewById(R.id.ad_call_to_action);
            callToActionView.setText(nativeAd.getCallToAction());
            callToActionView.setVisibility(View.VISIBLE);
            adView.setCallToActionView(callToActionView);
        } else {
            adView.findViewById(R.id.ad_call_to_action).setVisibility(View.GONE);
        }

        // Bind other assets (if any), e.g., icon, advertiser, etc.

        // Set the native ad object to the NativeAdView.
        adView.setNativeAd(nativeAd);
    }


    public void destroyNativeAd() {
        if (nativeAd != null) {
            nativeAd.destroy();
        }
    }

    // ---------------------- BANNER AD ---------------------- //
    public void loadBannerAd(FrameLayout adContainer) {
        AdView adView = new AdView(context);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);

        adContainer.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}
