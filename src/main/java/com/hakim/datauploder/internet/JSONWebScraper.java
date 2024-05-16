package com.hakim.datauploder.internet;

import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.util.DataUploaderLogger;
import com.hakim.datauploder.util.JsonUtility;
import com.hakim.datauploder.util.WebUtility;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.logging.Level;

public class JSONWebScraper {
    public JsonNode getJsonResponse(String url, Map<String, Object> headers) {
        try (HttpClient client = WebClient.getINSTANCE()) {
            DataUploaderLogger.log(Level.INFO, STR."Calling api \{url}");

            var request = WebUtility.buildGetRequest(url, headers);
            HttpResponse<String> body = client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonUtility.getJsonObject(body.body());
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.SEVERE, STR."Exception occurred in sendGetRequest(): cause \{ex.getMessage()}");
            return null;
        }
    }
}
