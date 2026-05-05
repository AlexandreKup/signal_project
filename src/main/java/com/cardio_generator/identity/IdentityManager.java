package com.cardio_generator.identity;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the collection of hospital patients and handles identity anomalies.
 */
public class IdentityManager {
    private Map<Integer, HospitalPatient> hospitalDatabase = new HashMap<>();

    public void registerPatient(HospitalPatient patient) {
        hospitalDatabase.put(patient.getPatientId(), patient);
    }

    public HospitalPatient getPatient(int id) {
        return hospitalDatabase.get(id);
    }

    public void handleMismatch(int id) {
        // Requirement: Handle mismatches or anomalies
        System.err.println("SECURITY ALERT: Incoming data for unknown Patient ID: " + id);
    }
}