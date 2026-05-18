package com.data_management;

import java.io.IOException;

/**
 * Starts the WebSocket data reader.
 * It connects to the simulator and stores real-time patient data.
 */
public class WebSocketReaderMain {

    /**
     * Starts the reader.
     *
     * @param args optional first argument for the WebSocket URI
     * @throws IOException if the reader cannot connect
     */
    public static void main(String[] args) throws IOException {
        String uri = "ws://localhost:8080";

        if (args.length > 0) {
            uri = args[0];
        }

        DataStorage storage = DataStorage.getInstance();
        WebSocketDataReader reader = new WebSocketDataReader(uri);

        reader.readData(storage);

        System.out.println("WebSocket reader started on " + uri);
    }
}
