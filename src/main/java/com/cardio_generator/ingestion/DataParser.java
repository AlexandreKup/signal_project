package com.cardio_generator.ingestion;

import com.data_management.DataStorage;

/**
 * Responsible for parsing incoming raw data into standardized system objects.
 */
public class DataParser {
    
    /**
     * Parses raw string data and hands it off to the storage system.
     * @param rawData The raw string received from the listener.
     * @param storage The DataStorage instance to save the parsed data.
     */
    public void parseAndStore(String rawData, DataStorage storage) {
        // Logic to parse JSON/CSV and call storage.addPatientData()
        // This keeps the rest of the system unaware of how data arrives.
        System.out.println("Parsing raw data: " + rawData);
    }
}