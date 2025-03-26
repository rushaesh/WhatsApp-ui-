package com.example.whatsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whatsapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class communityFragment extends Fragment {
    private ImageView btnCapture;
    public static final String EXTRA_INFO = "default";
    private ImageView imgCapture,manu;
    private static final int Image_Capture_Code = 1;
    public communityFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        btnCapture = (ImageView) rootView.findViewById(R.id.camera);
        manu = (ImageView) rootView.findViewById(R.id.manu);
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt, Image_Capture_Code);
            }
        });
        manu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan!!");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
        return rootView;
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == Image_Capture_Code) {
//            if (resultCode == RESULT_OK) {
//                Bitmap bp = (Bitmap) data.getExtras().get("data");
//                imgCapture.setImageBitmap(bp);
//            } else if (resultCode == RESULT_CANCELED) {
////            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.opationmanu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_settings) {
            // Code to be executed when the settings menu item is clicked
            Toast.makeText(getActivity(), "Calls Icon Click", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // Handling other menu items by calling the superclass method
            return super.onOptionsItemSelected(item);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Parse QR code result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Handle cancellation
                // Example: Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // Handle scan result
                String qrContent = result.getContents();
                // Example: Toast.makeText(getContext(), "Scanned: " + qrContent, Toast.LENGTH_LONG).show();

                // Here you can process the scanned QR code content as needed
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
