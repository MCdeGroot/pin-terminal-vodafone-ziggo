import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockHandler {
    private WireMockServer wireMockServer;

    // Create a WireMockServer instance to simulate a mock server.
    public WireMockHandler() {
        WireMockServer wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080
        wireMockServer.start(); // Start the mock server.
    }

    // Stop the wireMockServer when its no longer needed
    public void stopWireMockServer(){
        wireMockServer.stop();
    }

    private void configureWireMockStubs() {

        configureFor("localhost", wireMockServer.port());

        // Wiremock 1: PIN terminal successfully activated
        stubFor(post(urlEqualTo("/activate"))
                .withRequestBody(equalToJson("{\"customerId\": \"12345\", \"macAddress\": \"AA:BB:CC:DD:EE:FF\"}"))
                .willReturn(aResponse().withStatus(201)));

        // Wiremock 2: PIN terminal not registered
        stubFor(post(urlEqualTo("/activate"))
                .withRequestBody(equalToJson("{\"customerId\": \"12345\", \"macAddress\": \"AA:BB:CC:DD:EE:AA\"}"))
                .willReturn(aResponse().withStatus(404)));

        // Wiremock 3: Conflict - PIN terminal already attached to a different customer
        stubFor(post(urlEqualTo("/activate"))
                .withRequestBody(equalToJson("{\"customerId\": \"11111\", \"macAddress\": \"AA:BB:CC:DD:EE:FF\"}"))
                .willReturn(aResponse().withStatus(409)));

    }



}
