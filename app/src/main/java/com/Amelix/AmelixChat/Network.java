package com.Amelix.AmelixChat;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/** Helper functions relating to sending HTTP requests. */
public class Network {
    public static Response request(String method, String requestUrl, String write, String authToken) throws IOException, ExecutionException, InterruptedException {
        return new HttpRequestTask().execute(method, requestUrl, write, authToken).get();
    }
}