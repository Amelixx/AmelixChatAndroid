package com.example.amelixxyz;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notifications {
    public static void send(String title, String text, PendingIntent intent, int notificationId) {
        Context context = MainActivity.getAppContext();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(intent)

                // Hardcoded atm, don't understand enough of them to make them part of the function yet
                .setSmallIcon(android.R.drawable.sym_call_outgoing)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(notificationId, builder.build());
    }

    public static PendingIntent createIntent() {
        // Builds an intent that literally does nothing I have no idea how intents work
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(MainActivity.getAppContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }
}
