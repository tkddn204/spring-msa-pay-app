package net.rightpair.common;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@Component
public class CommonHttpClient {
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public HttpResponse<String> sendGetRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendPostRequest(String url, String body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String fakeRequest() {
        return fakeRequest(false);
    }

    public static String fakeRequest(boolean isFail) {
        try {
            // 0.5 ~ 1.5초
            if (isFail) throw new InterruptedException();
            Thread.sleep(new Random().nextInt(10) * 100 + 500);
        } catch (InterruptedException e) {
            if (isFail) {
                return "400 bad request";
            }
            return "500 Internal Server Error";
        }
        return "200 ok";
    }
}
