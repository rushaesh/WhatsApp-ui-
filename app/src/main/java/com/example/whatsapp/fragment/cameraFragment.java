package com.example.whatsapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.whatsapp.R;
import com.example.whatsapp.activity.CameraPreview;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class cameraFragment extends Fragment {



    public cameraFragment() {
        // Required empty public constructor
    }
    private Camera mCamera;
    private CameraPreview mPreview;
    private Button mTakePictureButton;
    private Button mSwitchCameraButton;
    private boolean isFrontCamera = false;
    private static final int REQUEST_CAMERA_PERMISSION = 1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            setupCamera(view);
        }

        return view;
    }
    private void setupCamera(View view) {
        mCamera = getCameraInstance(isFrontCamera);

        if (mCamera == null) {
            // Handle the case where the camera is not available
            return;
        }

        mPreview = new CameraPreview(getActivity(), mCamera);
        SurfaceView surfaceView = view.findViewById(R.id.camera_preview);

        if (surfaceView != null) {
            surfaceView.getHolder().addCallback(mPreview);
        } else {
            // Handle the case where the SurfaceView is not found
        }

        mTakePictureButton = view.findViewById(R.id.take_picture_button);
        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCamera != null) {
                    mCamera.takePicture(null, null, mPicture);
                }
            }
        });

        mSwitchCameraButton = view.findViewById(R.id.switch_camera_button);
        mSwitchCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCamera();
            }
        });

    }

    private Camera getCameraInstance(boolean frontCamera) {
        Camera camera = null;
        try {
            int cameraId = frontCamera ? findFrontFacingCamera() : findBackFacingCamera();
            camera = Camera.open(cameraId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return camera;
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private void switchCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        isFrontCamera = !isFrontCamera;
        setupCamera(getView());
    }

    private final android.hardware.Camera.PictureCallback mPicture = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    };

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + System.currentTimeMillis() + ".jpg");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupCamera(getView());
            } else {
                // Handle permission denial
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

}