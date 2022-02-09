package com.gadelev.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Service {
    public String getByUrl(String url){
        try {
            URL finalUrl = new URL(url);
            HttpURLConnection connectionGet = (HttpURLConnection) finalUrl.openConnection();
            connectionGet.setRequestMethod("GET");
            StringBuilder requestResult;
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(connectionGet.getInputStream()))) {
                requestResult = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    requestResult.append(input);
                }
            }
            connectionGet.disconnect();
            return requestResult.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
