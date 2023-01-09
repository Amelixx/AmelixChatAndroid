package com.Amelix.AmelixChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private static Context context;

    private WebView webView;
    private PersistentData data;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new ViewModel the first time the onCreate method is called.
        // After the first time this view model stays persistent.
        this.data = new ViewModelProvider(this).get(PersistentData.class);
        if (data.currentUrl == null) data.currentUrl = "https://amelix.xyz";

        MainActivity.context = getApplicationContext();

        this.webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl(data.currentUrl);

        createNotificationChannel();

        // Add a JavaScript interface to allow the javascript on the page to interact with the android app
        webView.addJavascriptInterface(new WebAppInterface(), "Android");


        // Get the current firebase token for push notifications

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().contains("amelix.xyz")) {
                data.currentUrl = url;
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", "All Notifications", importance);
            channel.setDescription("Encompasses all notifications. Wait, why are you reading this?");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history we can go back to
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        // If there is no history or it wasn't the back button, just default to the default android system behaviour
        return super.onKeyDown(keyCode, event);
    }
}
