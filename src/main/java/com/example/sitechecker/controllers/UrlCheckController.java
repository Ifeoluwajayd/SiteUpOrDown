package com.example.sitechecker.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_UP = "Site is up!";
    private final String SITE_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMesssage = "";
        try {
            URL urlObj =  new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // returnMesssage = SITE_UP;
            int responseCodeCheck = connection.getResponseCode() / 100;
            if ( responseCodeCheck < 2 || responseCodeCheck >= 4) {
                returnMesssage = SITE_DOWN;
            } else {
                returnMesssage = SITE_UP;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            returnMesssage = INCORRECT_URL;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            returnMesssage = SITE_DOWN;
        }
        return returnMesssage;
    }

}
