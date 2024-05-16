package com.hakim.datauploder.internet;

import java.net.http.HttpClient;

public abstract class WebClient {
    private static final HttpClient INSTANCE = HttpClient.newHttpClient();

    public WebClient() {
        throw new RuntimeException("Please call getINSTANCE method.");
    }

    public static HttpClient getINSTANCE() {
        return INSTANCE;
    }
}
