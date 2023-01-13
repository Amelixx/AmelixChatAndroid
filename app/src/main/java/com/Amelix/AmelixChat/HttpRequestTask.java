package com.Amelix.AmelixChat;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Integer, Response> {
    @Override
    protected Response doInBackground(String... params) {
        try {
            URL url = new URL(params[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(params[0]);
            connection.setDoOutput(true);

            DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
            outStream.writeBytes(params[2]);
            outStream.flush();
            outStream.close();

            // Read input stream
            InputStream inputStream = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            connection.disconnect();
            return new Response(connection.getResponseCode(), content.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
