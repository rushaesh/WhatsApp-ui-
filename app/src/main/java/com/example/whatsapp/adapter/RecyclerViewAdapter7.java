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
import com.example.whatsapp.activity.Email_address;
import com.example.whatsapp.activity.Help;
import com.example.whatsapp.activity.Notifications;
import com.example.whatsapp.activity.Privacy;
import com.example.whatsapp.activity.Security_notifications;
import com.example.whatsapp.activity.Storage;
import com.example.whatsapp.activity.Two_step_verification;
import com.example.whatsapp.activity.change_number;
import com.example.whatsapp.activity.chats;
import com.example.whatsapp.activity.favourites;
import com.example.whatsapp.activity.passkay;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;
import com.example.whatsapp.modalclass.CourseModel4;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter7 extends RecyclerView.Adapter<RecyclerViewAdapter7.RecyclerViewHolder>{
    private ArrayList<CourseModel4> courseDataArrayList;
    private Context mcontext;
    private AdManager adManager;
    private int clickCount = 0;



    public RecyclerViewAdapter7(ArrayList<CourseModel4> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.adManager = AdManager.getInstance(mcontext);
        
    }
    public void filterList(ArrayList<CourseModel4> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        courseDataArrayList = filterlist;
        // below line is to notify our adapter
        // as change in rex+   cycler view data.
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter7.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accont, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        CourseModel4 recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getTitle());
        holder.courseIV.setImageResource(recyclerData.getImgid());
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        // Set the data to textview and imageview.
//        CourseModel recyclerData = courseDataArrayList.get(position);
//        holder.courseTV.setText(recyclerData.getTitle());
//        holder.courseIV.setImageResource(recyclerData.getImgid());
//
////        switch((position+1)) {
////            case 1:
////                holder.cardview.setCardBackgroundColor(Color.parseColor("#fec5bb"));
////                break;
////            case 2:
////                holder.cardview.setCardBackgroundColor(Color.parseColor("#fcd5ce"));
////                break;
////            case 3:
////                holder.cardview.setCardBackgroundColor(Color.parseColor("#fae1dd"));
////                break;
////            case 4:
////                holder.cardview.setCardBackgroundColor(Color.parseColor("#f8edeb"));
////                break;
////
////        }
//    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV,textView1,textView2;
        private ImageView courseIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.mess1);
            courseIV = itemView.findViewById(R.id.profile_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickCount++; // Increment click count

                        // Check if the click count is divisible by 3 (i.e., every 3rd click)
                        if (clickCount % 2 == 0) {
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
        adManager.showRewardedAd((Activity) mcontext, new AdManager.AdListener1() {
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
                intent = new Intent(mcontext, Security_notifications.class);
                break;
            case 1:
                intent = new Intent(mcontext, passkay.class);
                break;
            case 2:
                intent = new Intent(mcontext, Email_address.class);
                break;
            case 3:
                intent = new Intent(mcontext, Two_step_verification.class);
                break;
            case 4:
                intent = new Intent(mcontext, change_number.class);
                break;
            case 5:
                intent = new Intent(mcontext, App_language.class);
                break;
            case 6:
                intent = new Intent(mcontext, Storage.class);
                break;
            case 7:
                // For position 7, open the BottomSheetDialog
                intent = new Intent(mcontext, Help.class);
                return;

            default:
                return;
        }
        mcontext.startActivity(intent);
    }
}
