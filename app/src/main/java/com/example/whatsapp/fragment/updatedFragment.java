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

import com.example.whatsapp.modalclass.CourseModel3;
import com.example.whatsapp.R;
import com.example.whatsapp.adapter.RecyclerViewAdapter3;

import java.util.ArrayList;

public class updatedFragment extends Fragment {
    private ArrayList<CourseModel3> recyclerDataArrayList1;
    private ImageView btnCapture;
    private ImageView pop2;
    public static final String EXTRA_INFO = "default";
    private ImageView imgCapture;
    private static final int Image_Capture_Code = 1;
    public updatedFragment(){
        // require a empty public constructor
    }

    public static Fragment newInstance() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_updated, container, false);
        btnCapture = (ImageView) rootView.findViewById(R.id.camera);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt, Image_Capture_Code);
            }
        });
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.idCourseRV);

        // 2. set layoutManger

        recyclerDataArrayList1=new ArrayList<CourseModel3>();

        // added data to array list

        recyclerDataArrayList1.add(new CourseModel3("aayushi vastaparea", R.drawable.people__1_,"1:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("Amit shah", R.drawable.man__1_,"9:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("Rohit vastapara", R.drawable.man,"4:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("meet vastapra", R.drawable.bo1,"1:40 AM"));
        recyclerDataArrayList1.add(new CourseModel3("bhuri 29.12", R.drawable.g3,"8:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("tanisha vastapara", R.drawable.g2,"5:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("darshan vastapra", R.drawable.man__3_,"9:30 AM"));
        recyclerDataArrayList1.add(new CourseModel3("Yug vastapra", R.drawable.man__2_,"2:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("aarti vastapara", R.drawable.people,"11:00 AM1"));
        recyclerDataArrayList1.add(new CourseModel3("bansi vastapra", R.drawable.g1,"12:00 AM"));
        recyclerDataArrayList1.add(new CourseModel3("Nad vastapara", R.drawable.man__4_,"5:00 AM"));

        RecyclerViewAdapter3 itemAdapter = new RecyclerViewAdapter3(recyclerDataArrayList1);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
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
