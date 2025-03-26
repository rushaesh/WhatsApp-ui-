package com.example.whatsapp.activity;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.provider.Settings;
import com.example.whatsapp.R;

import java.util.ArrayList;


public class RunTimePermission {
private Context context;
private RunTimePermissionListener listener;

public RunTimePermission(Context context) {
    this.context = context;
}

public void requestPermission(String[] permissions, RunTimePermissionListener listener) {
    this.listener = listener;
    // Check which permissions are not granted
    boolean allPermissionsGranted = true;
    for (String permission : permissions) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            allPermissionsGranted = false;
            break;
        }
    }

    if (allPermissionsGranted) {
        listener.permissionGranted();
    } else {
        ActivityCompat.requestPermissions((Activity) context, permissions, 1);
    }
}

public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == 1) {
        boolean allPermissionsGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }
        if (allPermissionsGranted) {
            listener.permissionGranted();
        } else {
            listener.permissionDenied();
        }
    }
}

public interface RunTimePermissionListener {
    void permissionGranted();
    void permissionDenied();
}
}