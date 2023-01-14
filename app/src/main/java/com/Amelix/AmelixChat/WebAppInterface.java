package com.Amelix.AmelixChat;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    public String token;

    @JavascriptInterface
    public void sendFirebaseToken(String authToken) {
        try {
            Response res = Network.request(
                    "PUT",
                    "https://api.phoenix.amelix.xyz/users/me",
                    "{\"firebaseToken\": \""+ token +"\"}",
                    authToken
            );
            if (res.statusCode == 200) {
                MainActivity.toast("Sent token to amelix.xyz!");
            }
            else {
                MainActivity.toast("Token failed to send ("+res.statusCode+")");
            }

        } catch (Exception e) {
            e.printStackTrace();
            MainActivity.toast("Token sending failed");
        }
    }
}
