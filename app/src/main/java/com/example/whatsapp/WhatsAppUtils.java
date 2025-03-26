package com.example.whatsapp;

import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.widget.Toast;

public class WhatsAppUtils {

    /**
     * Initiates a WhatsApp call.
     * @param context The context of the calling activity or fragment.
     * @param phoneNumber The phone number to call. It should include the country code (e.g., "+14155552671").
     */
    public static void openWhatsAppChat(Context context, String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/" + phoneNumber));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "WhatsApp is not installed.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error opening WhatsApp chat.", Toast.LENGTH_SHORT).show();
        }
    }
}
