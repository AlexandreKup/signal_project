package com.cardio_generator.identity;

/**
 * Represents a patient record in the hospital's internal database.
 */
public class HospitalPatient {
    private int patientId;
    private String name;
    private String medicalHistory;
    // personalized threshold as required by the project context
    private int heartRateThreshold = 130; 

    public HospitalPatient(int patientId, String name, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.medicalHistory = medicalHistory;
    }

    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getHeartRateThreshold() { return heartRateThreshold; }
}
