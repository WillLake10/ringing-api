package com.willlake.ringingapi.performances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PerformancesRequester {
    private static final Logger log = LoggerFactory.getLogger(PerformancesRequester.class);

    public String getPerformance(String id){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(
                URI.create("https://bb.ringingworld.co.uk/view.php?id=" + id))
                .header("Accept", " application/xml")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(response.body());
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("Http request for " + id + " failed", e);
            e.printStackTrace();
        }
        return null;
    }

}
