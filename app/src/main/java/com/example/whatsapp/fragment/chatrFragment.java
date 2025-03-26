package com.example.whatsapp.fragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.activity.Linkeddevices;
import com.example.whatsapp.activity.Newbroadcast;
import com.example.whatsapp.activity.Newgroup;
import com.example.whatsapp.activity.Payments;
import com.example.whatsapp.activity.Settings;
import com.example.whatsapp.activity.Starredmessages;
import com.example.whatsapp.activity.WhatsappCameraActivity;
import com.example.whatsapp.activity.camera;
import com.example.whatsapp.activity.contact;
import com.example.whatsapp.activity.qr_code;
import com.example.whatsapp.adapter.RecyclerViewAdapter;
import com.example.whatsapp.ads.AdManager;
import com.example.whatsapp.modalclass.CourseModel;

import java.util.ArrayList;

public class chatrFragment extends Fragment {
    private RecyclerView recyclerView3;
    private ArrayList<CourseModel> recyclerDataArrayList1;
    private RecyclerViewAdapter adapter2;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private ImageView btnCapture, conatact, camera1;
    public static final String EXTRA_INFO = "default";
    private ImageView imgCapture, popup_1;
    public static final int PERMISSION_REQUEST_CODE = 100;
    private static final int Image_Capture_Code = 1;
    private AdManager adManager;

    public chatrFragment() {


        // require a empty public constructor
    }

    public static Fragment newInstance() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cheat, container, false);
        adManager = AdManager.getInstance(requireContext());
        imgCapture = (ImageView) rootView.findViewById(R.id.capturedImage);
        conatact = (ImageView) rootView.findViewById(R.id.contact);
        btnCapture = (ImageView) rootView.findViewById(R.id.qrcode);
        camera1 = (ImageView) rootView.findViewById(R.id.camera1);
        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), camera.class);
                Log.d("TAG", "onClick: camera");
                getActivity().startActivity(intent);
            }


        });
        ImageView popup_1 = (ImageView) rootView.findViewById(R.id.popup_1);
        popup_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), qr_code.class);
                getActivity().startActivity(intent);
            }
        });
        conatact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), contact.class);
                getActivity().startActivity(intent);
            }


        });
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.idCourseRV);

        // 2. set layoutManger

        recyclerDataArrayList1 = new ArrayList<CourseModel>();

        // added data to array list
        recyclerDataArrayList1.add(new CourseModel("Amit shah", R.drawable.man__1_, "Good morning", "9:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("aayushi vastaparea", R.drawable.people__1_, "Seen Monday", "1:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("darshan vastapra", R.drawable.man__3_, "Liked a message", "9:30 AM"));
        recyclerDataArrayList1.add(new CourseModel("Yug vastapra", R.drawable.man__2_, "Seen last week", "2:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("aarti vastapara", R.drawable.people, "Good night", "11:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("bansi vastapra", R.drawable.g1, "Byy", "12:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("meet vastapra", R.drawable.bo1, "How are you", "1:40 AM"));
        recyclerDataArrayList1.add(new CourseModel("bhuri 29.12", R.drawable.g3, "Liked a message", "8:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("tanisha vastapara", R.drawable.g2, "byy", "5:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("Nad vastapara", R.drawable.man__4_, "Thank you", "5:00 AM"));
        recyclerDataArrayList1.add(new CourseModel("Rohit vastapara", R.drawable.man, "Good", "4:00 AM"));
        RecyclerViewAdapter itemAdapter = new RecyclerViewAdapter(recyclerDataArrayList1, getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);

        return rootView;
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(getContext(), anchor);
        popupMenu.getMenuInflater().inflate(R.menu.pop1, popupMenu.getMenu());

        // Apply custom style
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true); // Optional: if you want icons to appear in the menu
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Newgroup) {
                    // Handle Action 1
                    handleNewgroup();
                    return true;
                } else if (item.getItemId() == R.id.Newbroadcast) {
                    // Handle Action 2
                    handleNewbroadcast();
                    return true;
                } else if (item.getItemId() == R.id.Linkeddevices) {
                    // Handle Action 2
                    handleLinkeddevices();
                    return true;
                } else if (item.getItemId() == R.id.Starredmessages) {
                    // Handle Action 2
                    handleStarredmessages();
                    return true;
                } else if (item.getItemId() == R.id.Payments) {
                    // Handle Action 2
                    handlePayments();
                    return true;
                } else if (item.getItemId() == R.id.Settings) {
                    // Handle Action 2
                    handleSettings();
                    return true;
                } else {
                    // Optional: Handle other cases or return false
                    return false;
                }
            }
        });
        popupMenu.show();
    }

        private void handleSettings() {
            // Show the Interstitial Ad
            adManager.showInterstitialAd(getActivity(), new AdManager.AdListener1() {
                @Override
                public void onAdClosed() {
                    // When the ad is dismissed, proceed to open the Settings activity
                    Log.d("AdManager", "Interstitial ad closed.");
                    openSettingsActivity();
                }

                @Override
                public void onAdFailedToShow() {
                    // If the ad fails to show, log the error and still open the Settings activity
                    Log.e("AdManager", "Interstitial ad failed to show.");
                    openSettingsActivity();
                }
            });
        }

        private void openSettingsActivity() {
            // Start the Settings activity after ad interaction (whether ad showed or failed)
            Intent intent = new Intent(getContext(), Settings.class);
            startActivity(intent);
        }

    private void handlePayments() {
        adManager.showRewardedAd(getActivity(), new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                Log.d("AdManager", "Rewarded ad closed.");
                handlePaymentsactivity();
            }

            @Override
            public void onAdFailedToShow() {
                // If the ad fails to show, log the error and still open the Newgroup activity
                Log.e("AdManager", "Rewarded ad failed to show.");
                handlePaymentsactivity();
            }
        });
    }

    private void handlePaymentsactivity() {
        Intent intent = new Intent(getContext(), Payments.class);
        startActivity(intent);
    }

    private void handleStarredmessages() {
        adManager.showInterstitialAd(getActivity(), new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                Log.d("AdManager", "showInterstitialAd ad closed.");
                handleStarredmessagesactivity();
            }

            @Override
            public void onAdFailedToShow() {
                Log.e("AdManager", "showInterstitialAd ad failed to show.");
                handleStarredmessagesactivity();
            }
        });
    }

    private void handleStarredmessagesactivity() {
        Intent intent = new Intent(getContext(), Starredmessages.class);
        startActivity(intent);
    }

    private void handleLinkeddevices() {
        adManager.showRewardedAd(getActivity(), new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                // When the ad is dismissed, proceed to open the Newgroup activity
                Log.d("AdManager", "Rewarded ad closed.");
                handleLinkeddevicesactivity();
            }

            @Override
            public void onAdFailedToShow() {
                // If the ad fails to show, log the error and still open the Newgroup activity
                Log.e("AdManager", "Rewarded ad failed to show.");
                handleLinkeddevicesactivity();
            }
        });
    }

    private void handleLinkeddevicesactivity() {
        Intent intent = new Intent(getContext(), Linkeddevices.class);
        startActivity(intent);

    }

    private void handleNewbroadcast() {

        adManager.showInterstitialAd(getActivity(), new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                Log.d("AdManager", "showInterstitialAd ad closed.");
                handleNewbroadcastactivity();
            }

            @Override
            public void onAdFailedToShow() {
                Log.e("AdManager", "showInterstitialAd ad failed to show.");
                handleNewbroadcastactivity();
            }
        });

    }

    private void handleNewbroadcastactivity() {
        Intent intent = new Intent(getContext(), Newbroadcast.class);
        startActivity(intent);
    }

    private void handleNewgroup() {
        // Show the Rewarded Ad
        adManager.showRewardedAd(getActivity(), new AdManager.AdListener1() {
            @Override
            public void onAdClosed() {
                // When the ad is dismissed, proceed to open the Newgroup activity
                Log.d("AdManager", "Rewarded ad closed.");
                openNewgroupActivity();
            }

            @Override
            public void onAdFailedToShow() {
                // If the ad fails to show, log the error and still open the Newgroup activity
                Log.e("AdManager", "Rewarded ad failed to show.");
                openNewgroupActivity();
            }
        });
    }

    private void openNewgroupActivity() {
        // Start the Newgroup activity after ad interaction (whether ad showed or failed)
        Intent intent = new Intent(getContext(), Newgroup.class);
        startActivity(intent);
    }


    //    private void requestCameraPermission() {
//        if (ContextCompat.checkSelfPermission(requireActivity(),
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
//                    Manifest.permission.CAMERA)) {
//                // Show an explanation to the user asynchronously
//                Toast.makeText(requireActivity(), "Camera permission is needed to show the camera preview.", Toast.LENGTH_SHORT).show();
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(requireActivity(),
//                        new String[]{Manifest.permission.CAMERA},
//                        CAMERA_PERMISSION_CODE);
//            }
//        } else {
//            // Permission has already been granted
//            Toast.makeText(requireActivity(), "Camera permission already granted.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == CAMERA_PERMISSION_CODE) {
//            // If request is cancelled, the result arrays are empty
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted
//                Toast.makeText(requireActivity(), "Camera permission granted.", Toast.LENGTH_SHORT).show();
//            } else {
//                // Permission denied
//                Toast.makeText(requireActivity(), "Camera permission denied.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
// This method will help to retrieve the image
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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                // Permissions granted
                startActivity(new Intent(getActivity(), WhatsappCameraActivity.class));
                getActivity().finish();
            } else {
                // Permissions denied
                getActivity().finish();
            }
        }
    }
}
