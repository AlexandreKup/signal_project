package com.data_management;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Tests the WebSocketDataReader class.
 */
public class WebSocketDataReaderTest {

    /**
     * Checks that a WebSocketDataReader object can be created.
     * This test does not open a real WebSocket connection.
     */
    @Test
    public void testWebSocketDataReaderCanBeCreated() {
        assertDoesNotThrow(() -> {
            WebSocketDataReader reader = new WebSocketDataReader("ws://localhost:8080");
        });
    }
}
