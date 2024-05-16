package com.hakim.datauploder.internet;


import com.hakim.datauploder.util.DataUploaderLogger;
import com.hakim.datauploder.util.FileUtility;
import com.hakim.datauploder.util.WebUtility;

import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.logging.Level;

public class BasicWebScraper {
    public InputStream getInputStreamResponse(String url, Map<String, Object> headers) {
        try (HttpClient client = WebClient.getINSTANCE()) {
            DataUploaderLogger.log(Level.INFO, STR."Calling api \{url}");

            var request = WebUtility.buildGetRequest(url, headers);
            HttpResponse<InputStream> body = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            return body.body();
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, STR."Exception occurred in sendGetRequest(): cause \{ex.getMessage()}");
            return null;
        }
    }

    public byte[] getByteArrayResponse(String url, Map<String, Object> headers) {
        try (HttpClient client = WebClient.getINSTANCE()) {
            DataUploaderLogger.log(Level.INFO, STR."Calling api \{url}");

            var request = WebUtility.buildGetRequest(url, headers);
            HttpResponse<byte[]> body = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            return body.body();
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, STR."Exception occurred in sendGetRequest(): cause \{ex.getMessage()}");
            return null;
        }
    }

    public String getStringResponse(String url, Map<String, Object> headers) {
        try (HttpClient client = WebClient.getINSTANCE()) {
            DataUploaderLogger.log(Level.INFO, STR."Calling api \{url}");

            var request = WebUtility.buildGetRequest(url, headers);
            HttpResponse<String> body = client.send(request, HttpResponse.BodyHandlers.ofString());
            return body.body();
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, STR."Exception occurred in sendGetRequest(): cause \{ex.getMessage()}");
            return "";
        }
    }

    public boolean readAndWriteToFile(String url, Map<String, Object> headers, String filePath) {
        try {
            byte[] inputStream = getByteArrayResponse(url, headers);
            if (inputStream.length == 0) {
                DataUploaderLogger.log(Level.INFO, "Could not read data.");
                return false;
            }

            FileUtility.createDirectory(filePath);
            FileUtility.write(filePath, inputStream);

            return true;
        } catch (Exception ex) {
            DataUploaderLogger.log(Level.INFO, STR."Error occurred: cause \{ex.getMessage()}");
            return false;
        }
    }
}
