package com.Amelix.AmelixChat;

import android.webkit.JavascriptInterface;

public class WebAppInterface {
    @JavascriptInterface
    public void sendNotification() {
        Notifications.send(
                "omg notification!",
                "omggggggggggggg",
                Notifications.createIntent(),
                1
        );
    }
}