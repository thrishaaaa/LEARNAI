package com.LEARNAI.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    // This points to your Python script
    private final String PYTHON_API_URL = "http://127.0.0.1:5000/chat";

    public String getGrokResponse(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the data to send to Python
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("message", userMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Send request to Python and get response
            ResponseEntity<Map> response = restTemplate.postForEntity(PYTHON_API_URL, request, Map.class);
            
            // Extract the "reply" from the Python response
            if (response.getBody() != null && response.getBody().containsKey("reply")) {
                return (String) response.getBody().get("reply");
            } else {
                return "Error: Python backend returned an empty response.";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Could not connect to the Python AI Brain. Is app.py running?";
        }
    }
}