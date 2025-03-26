package com.example.whatsapp.fragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.WhatsAppUtils;
import com.example.whatsapp.modalclass.CourseModel2;
import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter2;

import java.util.ArrayList;

public class callsFragment extends Fragment {
    private ArrayList<CourseModel2> recyclerDataArrayList1;
    private ImageView btnCapture;
    public static final String EXTRA_INFO = "default";
    private ImageView imgCapture;
    private static final int Image_Capture_Code = 1;
    private static final String WHATSAPP_PHONE_NUMBER = "+14155552671";

    public callsFragment() {
        // require a empty public constructor
    }

    public static Fragment newInstance() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calls, container, false);
        btnCapture = (ImageView) rootView.findViewById(R.id.camera);
        imgCapture = (ImageView) rootView.findViewById(R.id.call);
        imgCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhatsAppUtils.openWhatsAppChat(getContext(), WHATSAPP_PHONE_NUMBER);
            }
        });
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt, Image_Capture_Code);
            }
        });
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.idCourseRV);

        // 2. set layoutManger

        recyclerDataArrayList1 = new ArrayList<CourseModel2>();

        // added data to array list
        recyclerDataArrayList1.add(new CourseModel2("Amit shah", R.drawable.man__1_, "9:00 AM", R.drawable.phone_call_icon));
        recyclerDataArrayList1.add(new CourseModel2("aayushi vastaparea", R.drawable.people__1_, "1:00 AM", R.drawable.phone_call_icon));
        recyclerDataArrayList1.add(new CourseModel2("darshan vastapra", R.drawable.man__3_, "9:30 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("Yug vastapra", R.drawable.man__2_, "2:00 AM", R.drawable.phone_call_icon));
        recyclerDataArrayList1.add(new CourseModel2("aarti vastapara", R.drawable.people, "11:00 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("bansi vastapra", R.drawable.g1, "12:00 AM", R.drawable.phone_call_icon));
        recyclerDataArrayList1.add(new CourseModel2("meet vastapra", R.drawable.bo1, "1:40 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("bhuri 29.12", R.drawable.g3, "8:00 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("tanisha vastapara", R.drawable.g2, "5:00 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("Nad vastapara", R.drawable.man__4_, "5:00 AM", R.drawable.camera_video_4));
        recyclerDataArrayList1.add(new CourseModel2("Rohit vastapara", R.drawable.man, "4:00 AM", R.drawable.camera_video_4));
        RecyclerViewAdapter2 itemAdapter = new RecyclerViewAdapter2(recyclerDataArrayList1);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

        return rootView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}
