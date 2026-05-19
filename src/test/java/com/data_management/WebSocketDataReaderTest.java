package com.data_management;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /**
     * Checks that a correct WebSocket message is stored.
     */
    @Test
    public void testValidMessageIsStored() {
        DataStorage storage = DataStorage.getInstance();
        WebSocketDataReader reader = new WebSocketDataReader("ws://localhost:8080");

        reader.parseMessage("100,1700000000000,HeartRate,85", storage);

        assertEquals(1, storage.getRecords(100).size());
        assertEquals("HeartRate", storage.getRecords(100).get(0).getRecordType());
        assertEquals(85.0, storage.getRecords(100).get(0).getMeasurementValue());
    }

    /**
     * Checks that an invalid WebSocket message does not crash the system.
     */
    @Test
    public void testInvalidMessageDoesNotCrash() {
        DataStorage storage = DataStorage.getInstance();
        WebSocketDataReader reader = new WebSocketDataReader("ws://localhost:8080");

        assertDoesNotThrow(() -> {
            reader.parseMessage("bad,message", storage);
            reader.parseMessage("abc,1700000000000,HeartRate,85", storage);
        });
    }
}