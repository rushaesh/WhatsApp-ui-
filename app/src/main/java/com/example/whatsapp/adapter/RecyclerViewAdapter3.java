package com.example.whatsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.modalclass.CourseModel3;

import java.util.ArrayList;

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.RecyclerViewHolder>{
    private ArrayList<CourseModel3> courseDataArrayList;
    private Context mcontext;



    public RecyclerViewAdapter3(ArrayList<CourseModel3> recyclerDataArrayList) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }
    public void filterList(ArrayList<CourseModel3> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        courseDataArrayList = filterlist;
        // below line is to notify our adapter
        // as change in rex+   cycler view data.
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter3.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.updates, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        CourseModel3 recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getTitle());
        holder.textView1.setText(recyclerData.getTitle1());
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

        private TextView courseTV,textView1;
        private ImageView courseIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.mess1);
            textView1 = itemView.findViewById(R.id.messa1);
            courseIV = itemView.findViewById(R.id.profile_image);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position =getAdapterPosition();
//                    Intent intent = null;
//                    if(position==0) {
//                        String s = "Active 9h ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.man__1_);
//                    }
//                    else if(position==1) {
//                        String s = "Active 3h ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.people__1_);
//                    }
//                    else if(position==2) {
//                        String s = "Active 2h ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.man__3_);
//                    }
//                    else if(position==3) {
//                        String s = "Active now";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.man__2_);
//                    }
//                    else if(position==4) {
//                        String s = "Active 3h ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.people);
//                    }
//
//                    else if(position==5) {
//                        String s = "aarti_vastapara";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.g1);
//                    }
//                    else if(position==6) {
//                        String s = "Active 3h ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.bo1);
//                    }
//                    else if(position==7) {
//                        String s = "meet_vastapara";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.g3);
//                    }
//
//                    else if(position==8) {
//                        String s = "Active few ago";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.g2);
//                    }
//                    else if(position==9) {
//                        String s = "Active few days";
//                        intent = new Intent(mcontext, instagrammessagepage2.class);
//                        intent.putExtra("mess2", courseTV.getText().toString());
//                        intent.putExtra("mess3", textView1.getText().toString());
//                        intent.putExtra("mess1", s);
//                        intent.putExtra("image", R.drawable.g2);
//                    }
//
//                    mcontext.startActivity(intent);
//
//                }
//            });
        }
    }
}
