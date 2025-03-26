package com.example.whatsapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.modalclass.CourseModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<CourseModel> courseDataArrayList;
    private Context mContext;

    // Constructor to initialize data and context
    public RecyclerViewAdapter(ArrayList<CourseModel> recyclerDataArrayList, Context mContext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mContext = mContext;
    }

    // Method to filter the list
    public void filterList(ArrayList<CourseModel> filterList) {
        courseDataArrayList = filterList;
        notifyDataSetChanged();
    }

    // Inflate the layout for each item
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.massage, parent, false);
        return new RecyclerViewHolder(view);
    }

    // Bind data to the views in each item
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        CourseModel recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getTitle());
        holder.textView1.setText(recyclerData.getTitle1());
        holder.textView2.setText(recyclerData.getTitle2());
        holder.courseIV.setImageResource(recyclerData.getImgid());
    }

    // Return the size of the data list
    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }

    // ViewHolder class to hold the views
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV, textView1, textView2;
        private CircleImageView courseIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.mess1);
            textView1 = itemView.findViewById(R.id.messa1);
            textView2 = itemView.findViewById(R.id.mess2);
            courseIV = itemView.findViewById(R.id.profile_image);

            // Set click listener on the profile image to show AlertDialog
            courseIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        showProfileDialog(position);
                    }
                }
            });
        }

        private void showProfileDialog(int position) {
            View alertCustomDialog = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet_layout, null);

            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);


            alert.setView(alertCustomDialog);

            AlertDialog dialog = alert.create();

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(true);

            ImageView profileImageView = alertCustomDialog.findViewById(R.id.profile_image_popup);
            TextView userName = alertCustomDialog.findViewById(R.id.user_name);


            if (position == 0) {
                profileImageView.setImageResource(R.drawable.man__1);
                userName.setText("Amit shah");
            } else if (position == 1) {
                profileImageView.setImageResource(R.drawable.people__1_);
                userName.setText("aayushi vastaparea");
            }
            else if (position == 2) {
                profileImageView.setImageResource(R.drawable.man__3_);
                userName.setText("darshan vastaparea");
            }
            else if (position == 3) {
                profileImageView.setImageResource(R.drawable.man__2_);
                userName.setText("Yug vastaparea");
            }
            else if (position == 4) {
                profileImageView.setImageResource(R.drawable.people);
                userName.setText("aarti vastaparea");
            }
            else if (position == 5) {
                profileImageView.setImageResource(R.drawable.g1);
                userName.setText("bansi vastaparea");
            }
            else if (position == 6) {
                profileImageView.setImageResource(R.drawable.bo1);
                userName.setText("meet vastaparea");
            }
            else if (position == 7) {
                profileImageView.setImageResource(R.drawable.g3);
                userName.setText("bhuri 29.12");
            }
            else if (position == 8) {
                profileImageView.setImageResource(R.drawable.g2);
                userName.setText("tanisha vastaparea");
            }
            else if (position == 9) {
                profileImageView.setImageResource(R.drawable.man__4_);
                userName.setText("Nad vastaparea");
            }
            else if (position == 10) {
                profileImageView.setImageResource(R.drawable.man);
                userName.setText("Rohit vastaparea");
            }

// Show the dialog
            dialog.show();

        }
    }
}
