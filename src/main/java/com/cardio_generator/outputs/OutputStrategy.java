package com.cardio_generator.outputs;

/**
 * Interface for output strategies.
 * It define how data is send (console, file, network).
 */
public interface OutputStrategy {

    /**
     * Output data for a patient.
     *
     * @param patientId id of patient
     * @param timestamp time of data
     * @param label type of data
     * @param data value of data
     */
    
    void output(int patientId, long timestamp, String label, String data);
}
