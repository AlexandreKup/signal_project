package com.cardio_generator.identity;

/**
 * Matches incoming data IDs to hospital records.
 */
public class PatientIdentifier {
    private IdentityManager identityManager;

    public PatientIdentifier(IdentityManager manager) {
        this.identityManager = manager;
    }

    /**
     * Validates if an incoming ID exists in the hospital database.
     */
    public HospitalPatient validateAndMatch(int incomingId) {
        HospitalPatient patient = identityManager.getPatient(incomingId);
        
        if (patient == null) {
            identityManager.handleMismatch(incomingId); // Requirement: Handle anomalies
            return null;
        }
        
        return patient;
    }
}