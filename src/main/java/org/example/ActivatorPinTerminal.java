package org.example;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivatorPinTerminal {

    public ActivatorPinTerminal() {
    }

    //add logger to the project
    protected static final Logger logger = LogManager.getLogger();

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

            // Process the response and return message based on responses
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            //Log the response
            logger.info("Response Status code: " + statusCode);
            if (statusCode == 201) {
                logger.info("Response Status code: 201, the service has been successfully activated");
                return "ACTIVE";
            } else if (statusCode == 404) {
                logger.info("Response Status code: 404, the service could not be activated because it is not registered");
                return "INACTIVE";
            } else if (statusCode == 409) {
                logger.info("Response Status code: 409, the service could not be activated due to a conflict with the existing customer");
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
