package com.cardio_generator.ingestion;

import com.data_management.DataStorage;

/**
 * Concrete implementation of DataListener for TCP streams[cite: 1].
 */
public class TCPDataListener implements DataListener {
    private int port;
    private DataStorage storage;
    private DataParser parser;

    public TCPDataListener(int port, DataStorage storage, DataParser parser) {
        this.port = port;
        this.storage = storage;
        this.parser = parser;
    }

    @Override
    public void listen() {
        // Logic to establish TCP connection and receive data stream[cite: 1]
        System.out.println("Listening for TCP data on port " + port + "...");
        
        // Example: When data is received, it is sent to the parser
        // String rawData = someSocket.read();
        // parser.parseAndStore(rawData, storage);
    }
}