package org.example;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivatorPinTerminal {

    public ActivatorPinTerminal() {
    }

    //add logger to the project
    private static final Logger logger = LoggerFactory.getLogger(ActivatorPinTerminal.class);

    public static String activatePinTerminal(String customerId, String macAddress) {

        // Create JSON request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("customerId", customerId);
        requestBody.put("macAddress", macAddress);

        // Create and configure HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/activate");

        try {
            // Set request body
            StringEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // Execute the POST request
            HttpResponse httpResponse = httpClient.execute(httpPost);

            //Log the response
            logger.info("Response Status code: " +httpResponse.getStatusLine().getStatusCode()+ " ");

            // Process the response and return message based on responses
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                return "ACTIVE";
            } else if (statusCode == 404) {
                return "INACTIVE";
            } else if (statusCode == 409) {
                return "CONFLICT";
            } else {
                return "UNKNOWN";
            }
        } catch (Exception e) {
            //log error
            logger.error("An error occurred activating the PIN Terminal", e);
            return "ERROR";
        }
    }
}
