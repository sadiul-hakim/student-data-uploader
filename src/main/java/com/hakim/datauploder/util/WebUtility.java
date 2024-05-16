package com.hakim.datauploder.util;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;


public final class WebUtility {
    private WebUtility() {

    }

    private static HttpRequest.Builder getRequestBuilder(String uri, Map<String, Object> headers) throws URISyntaxException {
        var request = HttpRequest.newBuilder(new URI(uri));
        headers.forEach((key, value) -> request.header(key, value.toString()));
        request.GET();
        return request;
    }

    private static HttpRequest.Builder postRequestBuilder(String uri, Map<String, Object> headers, String body) throws URISyntaxException {
        var request = HttpRequest.newBuilder(new URI(uri));
        headers.forEach((key, value) -> request.header(key, value.toString()));
        request.POST(HttpRequest.BodyPublishers.ofString(body));
        return request;
    }

    public static HttpRequest buildGetRequest(String uri, Map<String, Object> headers) throws URISyntaxException {
        return getRequestBuilder(uri, headers).build();
    }

    public static HttpRequest buildGetRequestWithProxy(String uri, Map<String, Object> headers,
                                                       String proxyHost, String proxyPort, String username,
                                                       String password) throws URISyntaxException {
        return null;
    }

    public static HttpRequest buildGetRequestWithTimeout(String uri, Map<String, Object> headers,
                                                         long timeout) throws URISyntaxException {
        return getRequestBuilder(uri, headers).timeout(Duration.ofSeconds(timeout)).build();
    }

    public static HttpRequest buildPostRequest(String uri, Map<String, Object> headers, String body) throws URISyntaxException {
        return postRequestBuilder(uri, headers, body).build();
    }

    public static HttpRequest buildPostRequestWithProxy(String uri, Map<String, Object> headers, String body,
                                                        String proxyHost, String proxyPort, String username,
                                                        String password) {
        return null;
    }

    public static HttpRequest buildPostRequestWithTimeout(String uri, Map<String, Object> headers,
                                                          String body, int timeout) throws URISyntaxException {
        return postRequestBuilder(uri, headers, body).timeout(Duration.ofSeconds(timeout)).build();
    }

    public static HttpRequest buildPostRequestWithFile(String uri, Map<String, Object> headers, String filePath,
                                                       int timeout) throws URISyntaxException, FileNotFoundException {
        HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(uri));
        headers.forEach((key, value) -> builder.header(key, value.toString()));
        builder.POST(HttpRequest.BodyPublishers.ofFile(
                Paths.get(filePath))).timeout(Duration.ofSeconds(timeout));

        return builder.build();
    }

    private static void setProxy() {

    }
}
