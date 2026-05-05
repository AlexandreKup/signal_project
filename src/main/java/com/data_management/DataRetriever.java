package com.data_management;

import java.util.List;

/**
 * DataRetriever handles queries by medical staff for historical patient data.
 * This supports trend analysis and medical review of stored vitals.
 */
public class DataRetriever {

    private DataStorage storage;

    public DataRetriever(DataStorage storage) {
        this.storage = storage;
    }

    /**
     * Retrieves a list of patient records filtered by a specific time range.
     * 
     * @param patientId The unique identifier for the patient.
     * @param startTime The beginning of the time range (milliseconds).
     * @param endTime The end of the time range (milliseconds).
     * @return A list of PatientRecord objects matching the criteria.
     */
    public List<PatientRecord> retrieveHistory(int patientId, long startTime, long endTime) {
    // 1. We change 'String' to 'int' to match DataStorage
    // 2. We call the storage method you just fixed
    List<PatientRecord> allRecords = storage.getRecords(patientId, startTime, endTime);
    
    if (allRecords == null || allRecords.isEmpty()) {
        return new java.util.ArrayList<>(); // Return empty list instead of null for better safety
    }

    return allRecords; 
    }
}
