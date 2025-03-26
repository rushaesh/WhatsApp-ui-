package com.example.whatsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.activity.Account;
import com.example.whatsapp.activity.App_language;
import com.example.whatsapp.activity.Avatar;
import com.example.whatsapp.activity.Help;
import com.example.whatsapp.activity.Notifications;
import com.example.whatsapp.activity.Privacy;
import com.example.whatsapp.activity.Storage;
import com.example.whatsapp.activity.chat;
import com.example.whatsapp.activity.chats;
import com.example.whatsapp.activity.favourites;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class RecyclerViewAdapter6 extends RecyclerView.Adapter<RecyclerViewAdapter6.RecyclerViewHolder> {

    private ArrayList<CourseModel> courseDataArrayList;
    private Context mContext;
    private AdManager adManager;
    private int clickCount = 0;

    public RecyclerViewAdapter6(ArrayList<CourseModel> recyclerDataArrayList, Context mContext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mContext = mContext;
        this.adManager = AdManager.getInstance(mContext); // Initialize AdManager

    }

    public void filterList(ArrayList<CourseModel> filterlist) {
        courseDataArrayList = filterlist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sa, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        CourseModel recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getTitle());
        holder.textView1.setText(recyclerData.getTitle1());
        holder.textView2.setText(recyclerData.getTitle2());
        holder.courseIV.setImageResource(recyclerData.getImgid());
    }

    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV, textView1, textView2;
        private ImageView courseIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.mess1);
            textView1 = itemView.findViewById(R.id.messa1);
            textView2 = itemView.findViewById(R.id.mess2);
            courseIV = itemView.findViewById(R.id.profile_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickCount++; // Increment click count

                        // Check if the click count is divisible by 3 (i.e., every 3rd click)
                        if (clickCount % 3 == 0) {
                            showAdBeforeNavigating(position); // Show ad for every 3rd click
                        } else {
                            navigateToActivity(position); // Directly navigate for other clicks
                        }
                    }
                }
            });
        }
    }

    private void showAdBeforeNavigating(int position) {
        // Show the ad before navigating to the activity
        adManager.showInterstitialAd((Activity) mContext, new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                // Ad closed, now navigate to the desired activity
                navigateToActivity(position);
            }

            @Override
            public void onAdFailedToShow() {
                // If the ad fails to show, navigate anyway
                navigateToActivity(position);
            }
        });
    }
    private void navigateToActivity(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(mContext, Account.class);
                break;
            case 1:
                intent = new Intent(mContext, Privacy.class);
                break;
            case 2:
                intent = new Intent(mContext, Avatar.class);
                break;
            case 3:
                intent = new Intent(mContext, favourites.class);
                break;
            case 4:
                intent = new Intent(mContext, chats.class);
                break;
            case 5:
                intent = new Intent(mContext, Notifications.class);
                break;
            case 6:
                intent = new Intent(mContext, Storage.class);
                break;
            case 7:
                // For position 7, open the BottomSheetDialog
                openBottomSheetDialog();
                return;
            case 8:
            case 9:
            case 10:
                intent = new Intent(mContext, Help.class);
                break;
            default:
                return;
        }
        mContext.startActivity(intent);
    }

    private void openBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        View bottomSheetView = LayoutInflater.from(mContext)
                .inflate(R.layout.language, null);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
        ImageView title = bottomSheetView.findViewById(R.id.titleTextView);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}
