import org.example.ActivatorPinTerminal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PinTerminalTest{

    private WireMockHandler wireMockHandler;

    // start the wiremockserver on localhost 8080
    @Before
    public void setUp(){
        wireMockHandler = new WireMockHandler();
        wireMockHandler.configureWireMockStubs();
    }

    // stop the server after the tests
    @After
    public void stopServer() {
        wireMockHandler.stopWireMockServer();
    }

    @Test
    public void testPinTerminalCreate(){
        String customerId = "12345";
        String macAddress = "AA:BB:CC:DD:EE:FF";

        String result = ActivatorPinTerminal.activatePinTerminal(customerId, macAddress);

        // assert the result
        assertEquals("ACTIVE", result);
    }

    @Test
    public void testPinTerminalNotFound(){
        String customerId = "12345";
        String macAddress = "AA:BB:CC:DD:EE:AA";

        String result = ActivatorPinTerminal.activatePinTerminal(customerId, macAddress);

        // assert the result
        assertEquals("INACTIVE", result);
    }
    @Test
    public void testPinTerminalConflict(){
        String customerId = "11111";
        String macAddress = "AA:BB:CC:DD:EE:FF";
        String result = ActivatorPinTerminal.activatePinTerminal(customerId, macAddress);

        // assert the result
        assertEquals("INACTIVE", result);
    }
}