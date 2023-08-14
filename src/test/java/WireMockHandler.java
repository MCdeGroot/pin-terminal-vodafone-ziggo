import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockHandler {

    // Create a WireMockServer instance to simulate a mock server.
    private final WireMockServer wireMockServer = new WireMockServer();

    // Method to start the server
    public WireMockHandler() {
        wireMockServer.start();
    }

    public void configureWireMockStubs() {

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

    // Stop the wireMockServer when its no longer needed
    public void stopWireMockServer(){
        wireMockServer.stop();
    }

}
