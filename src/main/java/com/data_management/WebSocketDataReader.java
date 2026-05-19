package com.data_management;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Reads real-time patient data from a WebSocket server and stores it in DataStorage.
 */
public class WebSocketDataReader implements DataReader {

    private final String serverUri;
    private WebSocketClient client;

    /**
     * Creates a WebSocketDataReader connected to a specific WebSocket server.
     *
     * @param serverUri the WebSocket server URI, for example ws://localhost:8080
     */
    public WebSocketDataReader(String serverUri) {
        this.serverUri = serverUri;
    }

    /**
     * Connects to the WebSocket server and continuously receives patient data.
     *
     * Expected message format:
     * patientId,timestamp,label,data
     *
     * @param dataStorage the storage where parsed records are saved
     * @throws IOException if the WebSocket URI is invalid or connection fails
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        try {
            client = new WebSocketClient(new URI(serverUri)) {

                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to WebSocket server: " + serverUri);
                }

                @Override
                public void onMessage(String message) {
                    parseMessage(message, dataStorage);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket connection closed: " + reason);
                }

                @Override
                public void onError(Exception exception) {
                    System.err.println("WebSocket error: " + exception.getMessage());
                }
            };

            client.connect();

        } catch (URISyntaxException exception) {
            throw new IOException("Invalid WebSocket URI: " + serverUri, exception);
        }
    }

    /**
     * Closes the WebSocket connection if it is open.
     */
    public void close() {
        if (client != null && client.isOpen()) {
            client.close();
        }
    }

    /**
     * Parses one WebSocket message and stores it.
     * Invalid messages are ignored.
     *
     * @param message the message received from the WebSocket server
     * @param dataStorage the storage where valid records are saved
     */
    public void parseMessage(String message, DataStorage dataStorage) {
        String[] parts = message.split(",");

        if (parts.length != 4) {
            System.err.println("Invalid WebSocket message ignored: " + message);
            return;
        }

        try {
            int patientId = Integer.parseInt(parts[0].trim());
            long timestamp = Long.parseLong(parts[1].trim());
            String recordType = parts[2].trim();
            double measurementValue = Double.parseDouble(parts[3].trim());

            dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);

        } catch (NumberFormatException exception) {
            System.err.println("Corrupted WebSocket message ignored: " + message);
        }
    }
}